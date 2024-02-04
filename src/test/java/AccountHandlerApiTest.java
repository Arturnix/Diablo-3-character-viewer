import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.api_handlers.BaseUrlParts;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountHandlerApiTest {
    private FetchToken testObject = new FetchToken();

    @Test
    public void correctAccountFetchedContainsProvidedBattleTag() {

        String battleTag = "Jokefish#2265";
        String token = testObject.requestToken().getAccess_token();

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(
                "https://eu.api.blizzard.com/d3/profile/Jokefish-2265/?locale=pl_PL&access_token="
                + token)
                .contains(battleTag));
    }

    @Test
    public void fetchAccountFailedMissedCredentialsNotContainDesiredChar() {

        String fetchedAccount = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi()
                 + BaseUrlParts.getBaseLocaleAndToken());

        Assertions.assertFalse(fetchedAccount.contains("#"));
    }

    @Test
    public void fetchAccountFailedMissedCredentials() {

        String fetchedAccountOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + "Jokefish-2265"
                + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token());

        String fetchedAccountNOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + BaseUrlParts.getBaseLocaleAndToken());

        Assertions.assertNotEquals(fetchedAccountOK, fetchedAccountNOK);
    }

    @Test
    public void fetchAccountFailedWrongBattleTagProvided() {

        AccountHandlerApi testAccountHandlerApi = new AccountHandlerApi();

        String fetchedAccountOK = testAccountHandlerApi.generateRequest("Jokefish-2265", testObject);
        String fetchedAccountNOK = testAccountHandlerApi.generateRequest(" ", testObject);

        Assertions.assertNotEquals(fetchedAccountOK, fetchedAccountNOK);
    }

    @Test
    public void fetchAccountFailedNullBattleTagProvided() {

        AccountHandlerApi testAccountHandlerApi = new AccountHandlerApi();
        String battleTag = null;

        String fetchedAccountOK = testAccountHandlerApi.generateRequest("Jokefish-2265", testObject);
        String fetchedAccountNOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + battleTag
                + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token());

        Assertions.assertNotEquals(fetchedAccountOK, fetchedAccountNOK);
    }

    @Test
    public void fetchAccountFailedNullBattleTagProvidedThrowsException() {

        AccountHandlerApi testAccountHandlerApi = new AccountHandlerApi();
        String battleTag = null;

        Exception exception = Assertions.assertThrows(NullPointerException.class, ()-> {
            testAccountHandlerApi.generateRequest(battleTag, testObject);
        });

        String expectedMessage = "Cannot invoke \"String.replace(char, char)\" because \"battleTag\" is null";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

}

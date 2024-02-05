import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.api_handlers.BaseUrlParts;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AccountHandlerApiTest {

    @Mock //wskazac dokladnie, niezaleznie od stanu obiektu. Nie jest rzeczywsitym obiektem, nie ma żadnego stanu. Z punktu widzenia programu to jest null
    private FetchToken testObject = new FetchToken();
    @Mock
    private AccountHandlerApi testAccountHandlerApi = new AccountHandlerApi();

    @Test
    public void correctAccountFetchedContainsProvidedBattleTag() {

        String battleTag = "Jokefish#2265";
        String urlRequest = "https://eu.api.blizzard.com/d3/profile/Jokefish-2265/?locale=pl_PL&access_token=";
        String token = testObject.requestToken().getAccess_token();

        Mockito.when(testObject.fetchAPIResourceRequest(
                urlRequest + token)).thenReturn("Jokefish#2265");

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(
                urlRequest + token).contains(battleTag));
    }

    @Test
    public void fetchAccountFailedMissedCredentialsNotContainDesiredChar() {

        Mockito.when(testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi()
                        + BaseUrlParts.getBaseLocaleAndToken()))
                .thenReturn("");

        String fetchedAccount = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi()
                 + BaseUrlParts.getBaseLocaleAndToken());

        Assertions.assertFalse(fetchedAccount.contains("#"));
    }

    @Test
    public void fetchAccountFailedMissedCredentials() {

        Mockito.when(testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + "Jokefish-2265"
                        + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token()))
                .thenReturn("Jokefish#2265");

        Mockito.when(testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + BaseUrlParts.getBaseLocaleAndToken()))
                .thenReturn("");

        String fetchedAccountOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + "Jokefish-2265"
                + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token());

        String fetchedAccountNOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + BaseUrlParts.getBaseLocaleAndToken());

        Assertions.assertNotEquals(fetchedAccountOK, fetchedAccountNOK);
    }

    @Test
    public void fetchAccountFailedWrongBattleTagProvided() {

        Mockito.when(testAccountHandlerApi.generateRequest("Jokefish-2265", testObject))
                .thenReturn("Jokefish#2265");

        Mockito.when(testAccountHandlerApi.generateRequest(" ", testObject))
                .thenReturn("");

        String fetchedAccountOK = testAccountHandlerApi.generateRequest("Jokefish-2265", testObject);
        String fetchedAccountNOK = testAccountHandlerApi.generateRequest(" ", testObject);

        Assertions.assertNotEquals(fetchedAccountOK, fetchedAccountNOK);
    }

    @Test
    public void fetchAccountFailedNullBattleTagProvided() {

        Mockito.when(testAccountHandlerApi.generateRequest("Jokefish-2265", testObject))
                .thenReturn("Jokefish#2265");

        String battleTag = null;
        Mockito.when(testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + battleTag
                + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token()))
                .thenReturn("");

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

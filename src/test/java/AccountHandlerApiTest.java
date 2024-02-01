import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.api_handlers.BaseUrlParts;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountHandlerApiTest {
    private FetchToken testObject = new FetchToken();

    @Test
    public void correctAccountFetched() {

        String battleTag = "Jokefish#2265";
        String token = testObject.requestToken().getAccess_token();

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(
                "https://eu.api.blizzard.com/d3/profile/Jokefish-2265/?locale=pl_PL&access_token="
                + token)
                .contains(battleTag));
    }

    @Test
    public void fetchAccountFailed() {

        String fetchedAccount = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi()
                 + BaseUrlParts.getBaseLocaleAndToken());

        Assertions.assertFalse(fetchedAccount.contains("#"));
    }

    @Test
    public void fetchAccountFailed2() {

        String fetchedAccountOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + "Jokefish-2265"
                + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token());

        String fetchedAccountNOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + BaseUrlParts.getBaseLocaleAndToken());

        Assertions.assertNotEquals(fetchedAccountOK, fetchedAccountNOK);
    }

    @Test
    public void fetchAccountFailed3() {

        AccountHandlerApi testAccountHandlerApi = new AccountHandlerApi();

        String fetchedAccountOK = testAccountHandlerApi.generateRequest("Jokefish-2265", testObject);
        String fetchedAccountNOK = testAccountHandlerApi.generateRequest(" ", testObject);

        Assertions.assertNotEquals(fetchedAccountOK, fetchedAccountNOK);
    }

    @Test
    public void fetchAccountFailed4() {

        AccountHandlerApi testAccountHandlerApi = new AccountHandlerApi();

        String fetchedAccountOK = testAccountHandlerApi.generateRequest("Jokefish-2265", testObject);
        String fetchedAccountNOK = testAccountHandlerApi.generateRequest("", testObject);

        Assertions.assertNotEquals(fetchedAccountOK, fetchedAccountNOK);
    }

}

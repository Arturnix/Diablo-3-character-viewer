import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountHandlerApiTest {
    private FetchToken testObject = new FetchToken();

    @Test
    public void correctAccoutFetched() {

        String battleTag = "Jokefish#2265";
        String token = testObject.requestToken().getAccess_token();

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(
                "https://eu.api.blizzard.com/d3/profile/Jokefish-2265/?locale=pl_PL&access_token="
                + token)
                .contains(battleTag));
    }
}

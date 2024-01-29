import com.diablo3CharViewer.token.FetchToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeroHandlerApiTest {

    private FetchToken testObject = new FetchToken();

    @Test
    public void correctAccountFetched() {

        String heroId = "162864678";
        String token = testObject.requestToken().getAccess_token();

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(
                        "https://eu.api.blizzard.com/d3/profile/Jokefish-2265/hero/" + heroId +
                                "?locale=pl_PL&access_token=" + token).contains("162864678"));
    }
}

import com.diablo3CharViewer.token.FetchToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemHandlerApiTest {

    private FetchToken testObject = new FetchToken();

    @Test
    public void correctAccountFetched() {

        String itemSlugAndId = "corrupted-ashbringer-Unique_Sword_2H_104_x1";
        String token = testObject.requestToken().getAccess_token();

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(
                        "https://eu.api.blizzard.com/d3/data/item/" + itemSlugAndId +
                                "?locale=pl_PL&access_token=" + token).contains(itemSlugAndId));
    }
}

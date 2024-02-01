import com.diablo3CharViewer.api_handlers.BaseUrlParts;
import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
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

    @Test
    public void fetchItemFailed() {

        String fetchedItem = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi());

        Assertions.assertFalse(fetchedItem.contains("id"));
    }

    @Test
    public void fetchItemFailed2() {

        String fetchedItemOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi() + "corrupted-ashbringer-Unique_Sword_2H_104_x1"
                    + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token());

        String fetchedItemNOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi());

        Assertions.assertFalse(fetchedItemOK == fetchedItemNOK);
    }

    @Test
    public void fetchItemFailed3() {

        ItemHandlerApi testItemHandlerApi = new ItemHandlerApi();

        String fetchedItemOK = testItemHandlerApi.generateRequest("corrupted-ashbringer-Unique_Sword_2H_104_x1", testObject);
        String fetchedItemNOK = testItemHandlerApi.generateRequest(" ", testObject);
    }

    public void fetchItemFailed4() {

        ItemHandlerApi testItemHandlerApi = new ItemHandlerApi();

        String fetchedItemOK = testItemHandlerApi.generateRequest("corrupted-ashbringer-Unique_Sword_2H_104_x1", testObject);
        String fetchedItemNOK = testItemHandlerApi.generateRequest("", testObject);
    }
}

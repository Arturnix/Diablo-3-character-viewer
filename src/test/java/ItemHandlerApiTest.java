import com.diablo3CharViewer.api_handlers.BaseUrlParts;
import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemHandlerApiTest {

    @Mock
    private FetchToken testObject = new FetchToken();
    @Mock
    private ItemHandlerApi testItemHandlerApi = new ItemHandlerApi();
    private String itemSlugAndId = "corrupted-ashbringer-Unique_Sword_2H_104_x1";

    @Test
    public void correctAccountFetchedConatinsProvidedItemId() {

        String token = Token.getAccess_token();
        String requestUrl = "https://eu.api.blizzard.com/d3/data/item/" + itemSlugAndId +
                "?locale=pl_PL&access_token=" + token;

        Mockito.when(testObject.fetchAPIResourceRequest(requestUrl)).thenReturn("corrupted-ashbringer-Unique_Sword_2H_104_x1");

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(requestUrl).contains(itemSlugAndId));
    }

    @Test
    public void fetchItemFailedMissedCredentialsNotContainDesiredField() {

        //String fetchedItem = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi());
        Mockito.when(testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi())).thenReturn("");

        Assertions.assertFalse(testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi()).contains("id"));
    }

    @Test
    public void fetchItemFailedMissedCredentials() {

        Mockito.when(testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi() + itemSlugAndId
                        + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token()))
                .thenReturn(itemSlugAndId);

        Mockito.when(testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi())).thenReturn("");

        String fetchedItemOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi() + itemSlugAndId
                    + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token());

        String fetchedItemNOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseItemApi());

        Assertions.assertNotEquals(fetchedItemOK, fetchedItemNOK);
    }

    @Test
    public void fetchItemFailedWrongItemIdProvided() {

        Mockito.when(testItemHandlerApi.generateRequest(itemSlugAndId, testObject)).thenReturn(itemSlugAndId);
        Mockito.when(testItemHandlerApi.generateRequest(" ", testObject)).thenReturn("");

        String fetchedItemOK = testItemHandlerApi.generateRequest(itemSlugAndId, testObject);
        String fetchedItemNOK = testItemHandlerApi.generateRequest(" ", testObject);

        Assertions.assertNotEquals(fetchedItemOK, fetchedItemNOK);
    }

    @Test
    public void fetchItemFailedNullItemId() {

        Mockito.when(testItemHandlerApi.generateRequest(itemSlugAndId, testObject)).thenReturn(itemSlugAndId);
        Mockito.when(testItemHandlerApi.generateRequest(null, testObject)).thenReturn(null);

        String fetchedItemOK = testItemHandlerApi.generateRequest(itemSlugAndId, testObject);
        String fetchedItemNOK = testItemHandlerApi.generateRequest(null, testObject);

        Assertions.assertNotEquals(fetchedItemOK, fetchedItemNOK);
    }
}
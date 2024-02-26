import com.diablo3CharViewer.token.FetchToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchTokenTest {

    private FetchToken testObject = new FetchToken();

    @Test
    public void correctTokenFetched() {
        String token = "EUCejbh2bft2F7hhtPl1Y0Wu8FAGW0AWJW"; //use actual token value

        Assertions.assertEquals(token, testObject.requestToken().getAccess_token());
    }

    @Test
    public void tokenNotNull() {
        Assertions.assertTrue(testObject.requestToken().getAccess_token() != null);
    }

    @Test
    public void fetchTokenThrows() {

        Exception exception = Assertions.assertThrows(RuntimeException.class, ()-> {
           testObject.fetchAPIResourceRequest("https://eu.api.blizzard.com/d3/profile/");
        });

        String expectedMessage = "Bohater o podanym battleTagu nie istanieje w swiecie Sanktuarium";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void fetchTokenFailed() {

        String expectedMessage = "Bohater o podanym battleTagu nie istanieje w swiecie Sanktuarium";
        String actualMessage = testObject.fetchAPIResourceRequest("https://eu.api.blizzard.com/d3/profile/");

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
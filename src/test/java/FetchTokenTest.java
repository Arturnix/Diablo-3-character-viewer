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
}
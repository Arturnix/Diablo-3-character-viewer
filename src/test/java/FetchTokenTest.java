import com.diablo3CharViewer.token.FetchToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchTokenTest {

    private FetchToken testObject = new FetchToken();

    @Test
    public void correctTokenFetched() {

        String token = "EU7S6RQ5lRGROtbckZEJMezVfURWyk3yit"; //use actual token value

        Assertions.assertEquals(token, testObject.requestToken().getAccess_token());
    }
}

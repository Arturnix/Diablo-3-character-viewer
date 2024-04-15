import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FetchTokenTest {
    private final FetchToken testObject = new FetchToken();

    @Test
    public void correctTokenFetched() {
        Assertions.assertEquals(Token.getAccess_token(), testObject.requestToken().getAccess_token());
    }

    @Test
    public void tokenNotNull() {
        Assertions.assertTrue(testObject.requestToken().getAccess_token() != null);
    }

    @Test
    public void fetchTokenThrows() {

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            testObject.fetchAPIResourceRequest("https://eu.api.blizzard.com/d3/profile/");
        });

        String expectedMessage = "Szukany twor nie istnieje w swiecie Sanktuarium!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
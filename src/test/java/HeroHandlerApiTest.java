import com.diablo3CharViewer.api_handlers.BaseUrlParts;
import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HeroHandlerApiTest {

    @Mock
    private FetchToken testObject;
    @Mock
    private HeroHandlerApi testHeroHandlerApi;

    @Test
    public void correctAccountFetchedContainsProvidedHeroId() {

        String heroId = "162864678";
        String token = Token.getAccess_token();
        String requestUrl = "https://eu.api.blizzard.com/d3/profile/Jokefish-2265/hero/" + heroId +
                "?locale=pl_PL&access_token=" + token;

        Mockito.when(testObject.fetchAPIResourceRequest(requestUrl)).thenReturn("162864678");

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(requestUrl).contains("162864678"));
    }

    @Test
    public void fetchHeroFailedMissedCredentialsNotContainDesiredField() {

        String requestUrl = BaseUrlParts.getBaseProfileApi() + BaseUrlParts.getBaseHeroApi()
                            + BaseUrlParts.getBaseLocaleAndToken();

        Mockito.when(testObject.fetchAPIResourceRequest(requestUrl)).thenReturn("");

        String fetchedHero = testObject.fetchAPIResourceRequest(requestUrl);

        Assertions.assertFalse(fetchedHero.contains("id"));
    }

    @Test
    public void fetchHeroFailedMissedCredentials() {

        String requestUrlOK = BaseUrlParts.getBaseProfileApi() + "Ghall-2523"
                + BaseUrlParts.getBaseHeroApi() + "162864678"
                + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token();

        String requestUrlNOK = BaseUrlParts.getBaseProfileApi()
                + BaseUrlParts.getBaseHeroApi()
                + BaseUrlParts.getBaseLocaleAndToken();

        Mockito.when(testObject.fetchAPIResourceRequest(requestUrlOK)).thenReturn("162864678");
        Mockito.when(testObject.fetchAPIResourceRequest(requestUrlNOK)).thenReturn("");

        String fetchedHeroOK = testObject.fetchAPIResourceRequest(requestUrlOK);
        String fetchedHeroNOK = testObject.fetchAPIResourceRequest(requestUrlNOK);

        Assertions.assertNotEquals(fetchedHeroOK, fetchedHeroNOK);
    }

    @Test
    public void fetchHeroFailedWrongHeroIdProvided() {

        Mockito.when(testHeroHandlerApi.generateRequest("Ghall-2523", "162864678", testObject))
                .thenReturn("162864678");
        Mockito.when(testHeroHandlerApi.generateRequest("Ghall-2523", " ", testObject))
                .thenReturn("");

        String fetchedHeroOK = testHeroHandlerApi.generateRequest("Ghall-2523", "162864678", testObject);
        String fetchedHeroNOK = testHeroHandlerApi.generateRequest("Ghall-2523", " ", testObject);

        Assertions.assertNotEquals(fetchedHeroOK, fetchedHeroNOK);
    }

    @Test
    public void fetchHeroFailedWrongBattleTagProvided() {

        Mockito.when(testHeroHandlerApi.generateRequest("Ghall-2523", "162864678", testObject))
                .thenReturn("162864678");
        Mockito.when(testHeroHandlerApi.generateRequest(" ", "162864678", testObject))
                .thenReturn("");

        String fetchedHeroOK = testHeroHandlerApi.generateRequest("Ghall-2523", "162864678", testObject);
        String fetchedHeroNOK = testHeroHandlerApi.generateRequest(" ", "162864678", testObject);

        Assertions.assertNotEquals(fetchedHeroOK, fetchedHeroNOK);
    }

    @Test
    public void fetchHeroFailedNullHeroId() {

        Mockito.when(testHeroHandlerApi.generateRequest("Ghall-2523", "162864678", testObject))
                .thenReturn("162864678");
        Mockito.when(testHeroHandlerApi.generateRequest("Ghall-2523", null, testObject))
                .thenReturn("");

        String fetchedHeroOK = testHeroHandlerApi.generateRequest("Ghall-2523", "162864678", testObject);
        String fetchedHeroNOK = testHeroHandlerApi.generateRequest("Ghall-2523", null, testObject);

        Assertions.assertNotEquals(fetchedHeroOK, fetchedHeroNOK);
    }

    @Test
    public void fetchHeroFailedNullBattleTagThorwsException() {

        HeroHandlerApi testHeroHandlerApi = new HeroHandlerApi();
        String heroId = "162864678";

        Exception exception = Assertions.assertThrows(NullPointerException.class, ()-> {
            testHeroHandlerApi.generateRequest(null, heroId, testObject);
        });

        String expectedMessage = "Cannot invoke \"String.replace(char, char)\" because \"battleTag\" is null";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}

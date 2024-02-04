import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.api_handlers.BaseUrlParts;
import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeroHandlerApiTest {

    private FetchToken testObject = new FetchToken();

    @Test
    public void correctAccountFetchedContainsProvidedHeroId() {

        String heroId = "162864678";
        String token = testObject.requestToken().getAccess_token();

        Assertions.assertTrue(testObject.fetchAPIResourceRequest(
                        "https://eu.api.blizzard.com/d3/profile/Jokefish-2265/hero/" + heroId +
                                "?locale=pl_PL&access_token=" + token).contains("162864678"));
    }

    @Test
    public void fetchHeroFailedMissedCredentialsNotContainDesiredField() {

        String fetchedHero = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + BaseUrlParts.getBaseHeroApi()
                + BaseUrlParts.getBaseLocaleAndToken());

        Assertions.assertFalse(fetchedHero.contains("id"));
    }

    @Test
    public void fetchHeroFailedMissedCredentials() {

        String fetchedHeroOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi() + "Jokefish-2265"
                + BaseUrlParts.getBaseHeroApi() + "162864678"
                + BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token());

        String fetchedHeroNOK = testObject.fetchAPIResourceRequest(BaseUrlParts.getBaseProfileApi()
                + BaseUrlParts.getBaseHeroApi()
                + BaseUrlParts.getBaseLocaleAndToken());

        Assertions.assertNotEquals(fetchedHeroOK, fetchedHeroNOK);
    }

    @Test
    public void fetchHeroFailedWrongHeroIdProvided() {

        HeroHandlerApi testHeroHandlerApi = new HeroHandlerApi();

        String fetchedHeroOK = testHeroHandlerApi.generateRequest("Jokefish-2265", "162864678", testObject);
        String fetchedHeroNOK = testHeroHandlerApi.generateRequest("Jokefish-2265", " ", testObject);

        Assertions.assertNotEquals(fetchedHeroOK, fetchedHeroNOK);
    }

    @Test
    public void fetchHeroFailedWrongBattleTagProvided() {

        HeroHandlerApi testHeroHandlerApi = new HeroHandlerApi();

        String fetchedHeroOK = testHeroHandlerApi.generateRequest("Jokefish-2265", "162864678", testObject);
        String fetchedHeroNOK = testHeroHandlerApi.generateRequest(" ", "162864678", testObject);

        Assertions.assertNotEquals(fetchedHeroOK, fetchedHeroNOK);
    }

    @Test
    public void fetchHeroFailedNullHeroId() {

        HeroHandlerApi testHeroHandlerApi = new HeroHandlerApi();

        String fetchedHeroOK = testHeroHandlerApi.generateRequest("Jokefish-2265", "162864678", testObject);
        String fetchedHeroNOK = testHeroHandlerApi.generateRequest("Jokefish-2265", null, testObject);

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

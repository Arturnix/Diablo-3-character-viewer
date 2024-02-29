import com.diablo3CharViewer.data_models.AccountDataModel;
import com.diablo3CharViewer.data_models.HeroDataModel;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.token.FetchToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AccountMapperTest {

   @Mock
    private FetchToken testFetchToken = new FetchToken();
   @Mock
    private AccountMapper testAccountMapper = new AccountMapper();
    String battleTag = "Jokefish#2265";
    List<HeroDataModel> heroes = new ArrayList<>();
    Map<String, Integer> mapKills = new HashMap<String, Integer>();
    AccountDataModel accountDataModel = new AccountDataModel("Jokefish#2265", 1111, "Phantas Magoria",
            heroes, 70, mapKills);

   @Test
    public void correctAccountFetchedToDataModel() {

       String expectedBattleTag = "Jokefish#2265";

       lenient().when(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken)).thenReturn(accountDataModel);
       Assertions.assertEquals(expectedBattleTag, testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken).getBattleTag());
    }

    @Test
    public void correctParagonLevelFetchedToDataModel() {

        int expectedParagonLevel = 1111;

        lenient().when(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken)).thenReturn(accountDataModel);
        Assertions.assertEquals(expectedParagonLevel, testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken).getParagonLevel());
    }

    @Test
    public void correctGuildNameFetchedToDataModel() {

        String expectedGuildName = "Phantas Magoria";

        lenient().when(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken)).thenReturn(accountDataModel);
        Assertions.assertEquals(expectedGuildName, testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken).getGuildName());
    }

    @Test
    public void correctHeroesListSizeFetchedToDataModel() {

       heroes.add(new HeroDataModel(1, "A", "barbarian"));
       heroes.add(new HeroDataModel(2, "B", "crusader"));

       lenient().when(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken)).thenReturn(accountDataModel);
       Assertions.assertEquals(2, heroes.size());
    }

    @Test
    public void correctHighestHardcoreLevelFetchedToDataModel() {

        lenient().when(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken)).thenReturn(accountDataModel);
        Assertions.assertEquals(70, testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken).getHighestHardcoreLevel());
    }

    @Test
    public void correctKillsFetchedToDataModel() {

        mapKills.put("elites", 1974);

        lenient().when(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken)).thenReturn(accountDataModel);
        Assertions.assertTrue(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken).getKills().containsKey("elites"));
        Assertions.assertTrue(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken).getKills().containsValue(1974));
    }

    @Test
    public void wrongBattleTagFormatProvided() {

       String providedBattleTag = "";
       String wrongBattleTagFormatWarning = "Niepoprawny format battleTag! Spróbuj ponownie.";

        Mockito.when(testAccountMapper.generateRequest(providedBattleTag, testFetchToken))
                .thenReturn("Niepoprawny format battleTag! Spróbuj ponownie.");

        Assertions.assertEquals(wrongBattleTagFormatWarning, testAccountMapper.generateRequest(providedBattleTag, testFetchToken));
    }

    @Test
    public void providedBattleTagDoesntExistThrowsException() {

        AccountMapper testAccountMapper = new AccountMapper();
        FetchToken testFetchToken = new FetchToken();

        String battleTag = "dsf#123";

        Exception exception = Assertions.assertThrows(RuntimeException.class, ()-> {
            testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken);
        });

        String expectedMessage = "Bohater o podanym battleTagu nie istnieje w swiecie Sanktuarium!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}

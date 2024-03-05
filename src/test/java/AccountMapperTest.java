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

import java.util.*;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AccountMapperTest {

    @Mock
    private FetchToken testFetchTokenMock;
    @Mock
    private AccountMapper testAccountMapperMock;
    private final FetchToken testFetchToken = new FetchToken();
    private final AccountMapper testAccountMapper = new AccountMapper();
    private final String battleTag = "Jokefish#2265";
    private final String expectedBattleTag = "Jokefish#2265";
    private final int expectedParagonLevel = 1111;
    private final String expectedGuildName = "Phantas Magoria";
    private final String battleTagAsWrongFormat = "abc123";
    private final String battleTagAsNull = null;
    private final String battleTagAsDosentExist = "dsf#123";
    private final String wrongBattleTagFormatWarning = "Niepoprawny format battleTag! Spróbuj ponownie.";
    private final ArrayList<HeroDataModel> heroes = new ArrayList<>(Arrays.asList(
            new HeroDataModel(1, "A", "barbarian"),
            new HeroDataModel(2, "B", "crusader")
    ));
    private final Map<String, Integer> mapKills = new HashMap<String, Integer>() {{
            put("elites",1974);
    }};
    private final AccountDataModel accountDataModel = new AccountDataModel("Jokefish#2265", 1111, "Phantas Magoria",
            heroes, 70, mapKills);

   @Test
    public void correctAccountFetchedToDataModel() {
       Mockito.when(testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock)).thenReturn(accountDataModel);
       Assertions.assertEquals(expectedBattleTag, testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock).getBattleTag());
    }

    @Test
    public void correctParagonLevelFetchedToDataModel() {
        Mockito.when(testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertEquals(expectedParagonLevel, testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock).getParagonLevel());
    }

    @Test
    public void correctGuildNameFetchedToDataModel() {
        Mockito.when(testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertEquals(expectedGuildName, testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock).getGuildName());
    }

    @Test
    public void correctHeroesListSizeFetchedToDataModel() {
        lenient().when(testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertEquals(2, heroes.size());
    }

    @Test
    public void correctHighestHardcoreLevelFetchedToDataModel() {
        Mockito.when(testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertEquals(70, testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock).getHighestHardcoreLevel());
    }

    @Test
    public void correctKillsFetchedToDataModel() {
        Mockito.when(testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertTrue(testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock).getKills().containsKey("elites"));
        Assertions.assertTrue(testAccountMapperMock.fetchAccountToDataModel(battleTag, testFetchTokenMock).getKills().containsValue(1974));
    }

    @Test
    public void wrongBattleTagFormatProvided() {
        Mockito.when(testAccountMapperMock.generateRequest(battleTagAsWrongFormat, testFetchTokenMock))
                .thenReturn("Niepoprawny format battleTag! Spróbuj ponownie.");

        Assertions.assertTrue(testAccountMapperMock.generateRequest(battleTagAsWrongFormat, testFetchTokenMock).contains(wrongBattleTagFormatWarning));
    }

    @Test
    public void providedBattleTagDoesntExistThrowsException() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, ()-> {
            testAccountMapper.fetchAccountToDataModel(battleTagAsDosentExist, testFetchToken);
        });

        String expectedMessage = "Gracz o podanym battleTagu nie istnieje w swiecie Sanktuarium!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void providedBattleTagIsNullThrowsException() {
        Exception exception = Assertions.assertThrows(NullPointerException.class, ()-> {
            testAccountMapper.fetchAccountToDataModel(battleTagAsNull, testFetchTokenMock);
        });

        String expectedMessage = "Cannot invoke \"String.replace(char, char)\" because \"battleTag\" is null";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}

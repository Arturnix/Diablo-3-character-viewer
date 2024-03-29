import com.diablo3CharViewer.data_models.AccountDataModel;
import com.diablo3CharViewer.data_models.HeroDataModel;
import com.diablo3CharViewer.json_mappers.AccountMapper;
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
    private AccountMapper testAccountMapperMock;
    private final AccountMapper testAccountMapper = new AccountMapper();
    private final String expectedBattleTag = "Jokefish#2265";
    private final int expectedParagonLevel = 1111;
    private final String expectedGuildName = "Phantas Magoria";
    private final ArrayList<HeroDataModel> heroes = new ArrayList<>(Arrays.asList(
            new HeroDataModel(1, "A", "barbarian", 15),
            new HeroDataModel(2, "B", "crusader", 9)
    ));
    private final Map<String, Integer> mapKills = new HashMap<String, Integer>() {{
            put("elites",1974);
    }};
    private final AccountDataModel accountDataModel = new AccountDataModel("Jokefish#2265", 1111, "Phantas Magoria",
            heroes, 70, mapKills);

   @Test
    public void correctAccountFetchedToDataModel() {
       Mockito.when(testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock)).thenReturn(accountDataModel);
       Assertions.assertEquals(expectedBattleTag, testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock).getBattleTag());
    }

    @Test
    public void correctParagonLevelFetchedToDataModel() {
        Mockito.when(testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertEquals(expectedParagonLevel, testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock).getParagonLevel());
    }

    @Test
    public void correctGuildNameFetchedToDataModel() {
        Mockito.when(testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertEquals(expectedGuildName, testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock).getGuildName());
    }

    @Test
    public void correctHeroesListSizeFetchedToDataModel() {
        lenient().when(testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertEquals(2, heroes.size());
    }

    @Test
    public void correctHighestHardcoreLevelFetchedToDataModel() {
        Mockito.when(testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertEquals(70, testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock).getHighestHardcoreLevel());
    }

    @Test
    public void correctKillsFetchedToDataModel() {
        Mockito.when(testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock)).thenReturn(accountDataModel);
        Assertions.assertTrue(testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock).getKills().containsKey("elites"));
        Assertions.assertTrue(testAccountMapperMock.fetchAccountToDataModel(ShareableDataForTests.battleTag, ShareableDataForTests.testFetchTokenMock).getKills().containsValue(1974));
    }

    @Test
    public void providedBattleTagDoesntExistThrowsException() {
        Assertions.assertThrows(RuntimeException.class, ()-> {
            testAccountMapper.fetchAccountToDataModel(ShareableDataForTests.battleTagAsDosentExist, ShareableDataForTests.testFetchToken);
        });
    }

    @Test
    public void providedBattleTagIsNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            testAccountMapper.fetchAccountToDataModel(null, ShareableDataForTests.testFetchTokenMock);
        });
    }
}

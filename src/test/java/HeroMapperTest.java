import com.diablo3CharViewer.data_models.FollowerDataModel;
import com.diablo3CharViewer.data_models.HeroDataModel;
import com.diablo3CharViewer.data_models.ItemDataModel;
import com.diablo3CharViewer.data_models.SkillDataModel;
import com.diablo3CharViewer.json_mappers.HeroMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class HeroMapperTest {

    @Mock
    private HeroMapper testHeroMapperMock;
    private HeroMapper testHeroMapper = new HeroMapper();
    private final String wrongHeroIdFormatWarning = "Niepoprawny format heroId - tylko cyfry! Spróbuj ponownie.";
    private final String heroId = "162864678";
    private final int expectedHeroId = 162864678;
    private final String heroIdAsWrongFormat = "abc123";
    private final String expectedHeroName = "Barbera";
    private final String expectedHeroClass = "barbarian";
    private final Map<String, Integer> heroKills = new HashMap<String, Integer>() {{
        put("elites", 403);
    }};
    private final List<SkillDataModel> heroSkills = new ArrayList<>(Arrays.asList(
            new SkillDataModel("active", "frenzy", "Amok", 11, "Generuje: 4 pkt. furii przy ataku."),
            new SkillDataModel("passive", "ruthless", "Bezwzglednosc", 10, "Zadajesz dodatkowe 40% obrazen przeciwnikom, ktorzy maja mniej niz 30% zycia.")
    ));
    private final List<ItemDataModel> heroItems = new ArrayList<>(Arrays.asList(
            new ItemDataModel("head", "Unique_Helm_002_p1", "Korona Leoryka"),
            new ItemDataModel("mainHand", "Unique_Mace_2H_104_x1", "Kruszyciel Dusz")
    ));
    private final List<ItemDataModel> followerTemplarItems = new ArrayList<>(Arrays.asList(
            new ItemDataModel("mainHand", "Spear_001", "Oszczep")
    ));
    private final Map<String, Integer> followerTemplarStats = new HashMap<String, Integer>() {{
        put("goldFind", 0);
    }};
    private final List<FollowerDataModel> followers = new ArrayList<>(Arrays.asList(
            new FollowerDataModel("templar", 70, followerTemplarItems, followerTemplarStats)
    ));
    private final Map<String, Integer> heroStats = new HashMap<String, Integer>() {{
        put("life", 437161);
        put("damage", 276225);
    }};
    private final HeroDataModel heroDataModel = new HeroDataModel(162864678, "Barbera", "barbarian", 70, 1111,
            false, false, false, heroKills, heroSkills, heroItems, followers, heroStats);

    @Test
    public void correctHeroFetchedToDataModel() {
        Mockito.when(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock)).thenReturn(heroDataModel);
        Assertions.assertEquals(expectedHeroId, testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getId());
        Assertions.assertEquals(expectedHeroName, testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getName());
        Assertions.assertEquals(expectedHeroClass, testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getClassHero());
    }

    @Test
    public void correctHeroKillsFetchedToDataModel() {
        Mockito.when(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock)).thenReturn(heroDataModel);
        Assertions.assertTrue(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getKills().containsKey("elites"));
        Assertions.assertTrue(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getKills().containsValue(403));
    }

    @Test
    public void correctHeroStatsFetchedToDataModel() {
        Mockito.when(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock)).thenReturn(heroDataModel);
        Assertions.assertTrue(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getStats().containsKey("life"));
        Assertions.assertTrue(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getStats().containsValue(437161));
        Assertions.assertTrue(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getStats().containsKey("damage"));
        Assertions.assertTrue(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getStats().containsValue(276225));
    }

    @Test
    public void correctHeroSkillsListSizeFetchedToDataModel() {
        Mockito.when(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock)).thenReturn(heroDataModel);
        Assertions.assertEquals(2, testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getSkills().size());
    }

    @Test
    public void correctHeroItemsListSizeFetchedToDataModel() {
        Mockito.when(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock)).thenReturn(heroDataModel);
        Assertions.assertEquals(2, testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getItems().size());
    }

    @Test
    public void correctHeroFollowersListSizeFetchedToDataModel() {
        Mockito.when(testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock)).thenReturn(heroDataModel);
        Assertions.assertEquals(1, testHeroMapperMock.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, ShareableDataForTests.testFetchTokenMock).getFollowers().size());
    }

    @Test
    public void wrongBattleTagFormatProvided() {
        Mockito.when(testHeroMapperMock.generateRequest(ShareableDataForTests.battleTagAsWrongFormat, heroId, ShareableDataForTests.testFetchTokenMock))
                .thenReturn("Niepoprawny format battleTag! Spróbuj ponownie.");

        Assertions.assertTrue(testHeroMapperMock.generateRequest(ShareableDataForTests.battleTagAsWrongFormat, heroId, ShareableDataForTests.testFetchTokenMock)
                .contains(ShareableDataForTests.wrongBattleTagFormatWarning));
    }

    @Test
    public void wrongHeroIdFormatProvided() {
        Mockito.when(testHeroMapperMock.generateRequest(ShareableDataForTests.battleTag, heroIdAsWrongFormat, ShareableDataForTests.testFetchTokenMock))
                .thenReturn("Niepoprawny format heroId - tylko cyfry! Spróbuj ponownie.");

        Assertions.assertTrue(testHeroMapperMock.generateRequest(ShareableDataForTests.battleTag, heroIdAsWrongFormat, ShareableDataForTests.testFetchTokenMock)
                .contains(wrongHeroIdFormatWarning));
    }

    @Test
    public void providedBattleTagDoesntExistThrowsException() {
        Assertions.assertThrows(RuntimeException.class, ()-> {
            testHeroMapper.fetchHeroToDataModel(ShareableDataForTests.battleTagAsDosentExist, heroId, ShareableDataForTests.testFetchToken);
        });
    }

    @Test
    public void providedBattleTagIsNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            testHeroMapper.fetchHeroToDataModel(null, heroId, ShareableDataForTests.testFetchTokenMock);
        });
    }

    @Test
    public void providedHeroIdIsNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            testHeroMapper.fetchHeroToDataModel(ShareableDataForTests.battleTag, null, ShareableDataForTests.testFetchTokenMock);
        });
    }

    @Test
    public void providedTokenNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            testHeroMapper.fetchHeroToDataModel(ShareableDataForTests.battleTag, heroId, null);
        });
    }
}

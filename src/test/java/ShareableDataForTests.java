import com.diablo3CharViewer.data_models.ItemArmorDataModel;
import com.diablo3CharViewer.data_models.ItemDataModel;
import com.diablo3CharViewer.data_models.ItemWeaponDataModel;
import com.diablo3CharViewer.token.FetchToken;
import org.mockito.Mock;

import java.util.*;

public class ShareableDataForTests {
    //public static final jest od okreslania wartosci stalych. Nie trzeba getterow i robic jako private.
    @Mock
    public static FetchToken testFetchTokenMock;
    public static final FetchToken testFetchToken = new FetchToken();
    public static final String battleTag = "Ghall#2523";
    public static final String battleTagAsDosentExist = "dsf#123";
    public static final String wrongHeroIdFormatWarning = "Niepoprawny format heroId - tylko cyfry! Spróbuj ponownie.";
    public static final String itemSlugAndIdAsDoesntExist = "";
    public static final String wrongBattleTagFormatWarning = "Niepoprawny format battleTag! Spróbuj ponownie.";
    public static final String itemSlugAndIdArmor = "veil-of-steel-p43_RetroHelm_003";
    public static final String itemSlugAndIdWeapon = "corrupted-ashbringer-Unique_Sword_2H_104_x1";
    public static final List<String> itemBodyPartSlotsArmor = new ArrayList<>(
            Arrays.asList("head")
    );
    public static final List<String> itemBodyPartSlotsWeapon = new ArrayList<>(
            Arrays.asList("right-hand","left-hand","follower-left-hand")
    );
    public static final Map<String, List<String>> attributesArmor = new HashMap<>() {{
    }};
    public static final Map<String, List<String>> attributesWeapon = new HashMap<>() {{
        put("primary", List.of(""));
        put("secondary", List.of(""));
    }};
    public static final ItemDataModel itemArmorDataModel = new ItemArmorDataModel(
            ShareableDataForTests.itemBodyPartSlotsArmor,
            "p43_RetroHelm_003",
            "Stalowy Calun",
            0,
            ShareableDataForTests.attributesArmor,
            "21 - 24"
    );
    public static final ItemDataModel itemWeaponDataModel = new ItemWeaponDataModel(
            ShareableDataForTests.itemBodyPartSlotsWeapon,
            "Unique_Sword_2H_104_x1",
            "Spaczony Spopielacz",
            70,
            ShareableDataForTests.attributesWeapon,
            "1137",
            "1702"
    );
}

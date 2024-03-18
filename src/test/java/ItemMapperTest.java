import com.diablo3CharViewer.data_models.ItemArmorDataModel;
import com.diablo3CharViewer.data_models.ItemDataModel;
import com.diablo3CharViewer.data_models.ItemWeaponDataModel;
import com.diablo3CharViewer.json_mappers.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ItemMapperTest {
    @Mock
    private ItemMapper testItemMapperMock;
    private ItemMapper itemMapper = new ItemMapper();
    private final List<String> itemBodyPartSlotsArmor = new ArrayList<>(
            Arrays.asList("head")
    );
    private final List<String> itemBodyPartSlotsWeapon = new ArrayList<>(
            Arrays.asList("right-hand","left-hand","follower-left-hand")
    );
    private final Map<String, List<String>> attributesArmor = new HashMap<>() {{
    }};
    private final Map<String, List<String>> attributesWeapon = new HashMap<>() {{
        put("primary", List.of(""));
        put("secondary", List.of(""));
    }};
    private final String itemSlugAndIdArmor = "veil-of-steel-p43_RetroHelm_003";
    private final String itemSlugAndIdWeapon = "corrupted-ashbringer-Unique_Sword_2H_104_x1";
    private final ItemDataModel itemArmorDataModel = new ItemArmorDataModel(
            itemBodyPartSlotsArmor,
            "p43_RetroHelm_003",
            "Stalowy Calun",
            0,
            attributesArmor,
            "21 - 24"
    );
    private final ItemDataModel itemWeaponDataModel = new ItemWeaponDataModel(
            itemBodyPartSlotsWeapon,
            "Unique_Sword_2H_104_x1",
            "Spaczony Spopielacz",
            70,
            attributesWeapon,
            "1137",
            "1702"
    );

    @Test
    public void correctItemTypeFetchedToDataModel() {
        Mockito.when(testItemMapperMock.fetchItemToDataModel(itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock)).thenReturn(itemArmorDataModel);
        Mockito.when(testItemMapperMock.fetchItemToDataModel(itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock)).thenReturn(itemWeaponDataModel);

        Assertions.assertTrue(testItemMapperMock.fetchItemToDataModel(itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock).getClass().getName().contains("ItemArmorDataModel"));
        Assertions.assertTrue(testItemMapperMock.fetchItemToDataModel(itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock).getClass().getName().contains("ItemWeaponDataModel"));
    }

    @Test
    public void providedItemSlugAndIdDoesntExistThrowsException() {
        Assertions.assertThrows(RuntimeException.class, ()-> {
            itemMapper.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdAsDoesntExist, ShareableDataForTests.testFetchTokenMock);
        });
    }

    @Test
    public void providedItemSlugAndIdNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemMapper.fetchItemToDataModel(null, ShareableDataForTests.testFetchTokenMock);
        });
    }

    @Test
    public void providedTokenNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemMapper.fetchItemToDataModel(itemSlugAndIdArmor, null);
        });
    }
}

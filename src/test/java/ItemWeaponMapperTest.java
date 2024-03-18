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
public class ItemWeaponMapperTest {
    @Mock
    private ItemMapper testItemWeaponMapperMock;
    private ItemMapper itemWeaponMapper = new ItemMapper();
    private final List<String> itemBodyPartSlots = new ArrayList<>(
            Arrays.asList("right-hand","left-hand","follower-left-hand")
    );
    private final String expectedItemId = "Unique_Sword_2H_104_x1";
    private final String expectedItemName = "Spaczony Spopielacz";
    private final Map<String, List<String>> attributes = new HashMap<>() {{
        put("primary", List.of(""));
        put("secondary", List.of(""));
    }};
    private final ItemDataModel itemDataModel = new ItemWeaponDataModel(
            itemBodyPartSlots,
            "Unique_Sword_2H_104_x1",
            "Spaczony Spopielacz",
            70,
            attributes,
            "1137",
            "1702"
    );
    private final String itemSlugAndId = "corrupted-ashbringer-Unique_Sword_2H_104_x1";

    @Test
    public void correctItemFetchedToDataModel() {
        Mockito.when(testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock)).thenReturn(itemDataModel);
        Assertions.assertEquals(expectedItemId,testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getId());
        Assertions.assertEquals(expectedItemName,testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getName());
    }

    @Test
    public void correctItemBodyPartSlotsListFetchedToDataModel() {
        Mockito.when(testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock)).thenReturn(itemDataModel);
        Assertions.assertTrue(testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getItemBodyPartSlots().contains("right-hand"));
        Assertions.assertTrue(testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getItemBodyPartSlots().contains("left-hand"));
        Assertions.assertTrue(testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getItemBodyPartSlots().contains("follower-left-hand"));
    }

    @Test
    public void correctItemTypeFetchedToDataModel() {
        Mockito.when(testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock)).thenReturn(itemDataModel);
        Assertions.assertTrue(testItemWeaponMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getClass().getName().contains("ItemWeaponDataModel"));
    }

    @Test
    public void providedItemSlugAndIdDoesntExistThrowsException() {
        Assertions.assertThrows(RuntimeException.class, ()-> {
            itemWeaponMapper.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdAsDoesntExist, ShareableDataForTests.testFetchTokenMock);
        });
    }

    @Test
    public void providedItemSlugAndIdNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemWeaponMapper.fetchItemToDataModel(null, ShareableDataForTests.testFetchTokenMock);
        });
    }

    @Test
    public void providedTokenNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemWeaponMapper.fetchItemToDataModel(itemSlugAndId, null);
        });
    }
}

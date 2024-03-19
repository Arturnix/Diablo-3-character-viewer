import com.diablo3CharViewer.json_mappers.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemWeaponMapperTest {
    @Mock
    private ItemMapper testItemWeaponMapperMock;
    private ItemMapper itemWeaponMapper = new ItemMapper();
    private final String expectedItemId = "Unique_Sword_2H_104_x1";
    private final String expectedItemName = "Spaczony Spopielacz";

    @Test
    public void correctItemFetchedToDataModel() {
        Mockito.when(testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock)).thenReturn(ShareableDataForTests.itemWeaponDataModel);
        Assertions.assertEquals(expectedItemId,testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock).getId());
        Assertions.assertEquals(expectedItemName,testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock).getName());
    }

    @Test
    public void correctItemBodyPartSlotsListFetchedToDataModel() {
        Mockito.when(testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock)).thenReturn(ShareableDataForTests.itemWeaponDataModel);
        Assertions.assertTrue(testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock).getItemBodyPartSlots().contains("right-hand"));
        Assertions.assertTrue(testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock).getItemBodyPartSlots().contains("left-hand"));
        Assertions.assertTrue(testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock).getItemBodyPartSlots().contains("follower-left-hand"));
    }

    @Test
    public void correctItemTypeFetchedToDataModel() {
        Mockito.when(testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock)).thenReturn(ShareableDataForTests.itemWeaponDataModel);
        Assertions.assertTrue(testItemWeaponMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock).getClass().getName().contains("ItemWeaponDataModel"));
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
            itemWeaponMapper.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, null);
        });
    }
}

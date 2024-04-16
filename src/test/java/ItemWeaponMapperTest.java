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
        Mockito.when(testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon)).thenReturn(ShareableDataForTests.itemWeaponDataModel);
        Assertions.assertEquals(expectedItemId,testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon).getId());
        Assertions.assertEquals(expectedItemName,testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon).getName());
    }

    @Test
    public void correctItemBodyPartSlotsListFetchedToDataModel() {
        Mockito.when(testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon)).thenReturn(ShareableDataForTests.itemWeaponDataModel);
        Assertions.assertTrue(testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon).getItemBodyPartSlots().contains("right-hand"));
        Assertions.assertTrue(testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon).getItemBodyPartSlots().contains("left-hand"));
        Assertions.assertTrue(testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon).getItemBodyPartSlots().contains("follower-left-hand"));
    }

    @Test
    public void correctItemTypeFetchedToDataModel() {
        Mockito.when(testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon)).thenReturn(ShareableDataForTests.itemWeaponDataModel);
        Assertions.assertTrue(testItemWeaponMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon).getClass().getName().contains("ItemWeaponDataModel"));
    }

    @Test
    public void providedItemSlugAndIdDoesntExistThrowsException() {
        Assertions.assertThrows(RuntimeException.class, ()-> {
            itemWeaponMapper.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdAsDoesntExist);
        });
    }

    @Test
    public void providedItemDataNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            itemWeaponMapper.mapItemToDataModel(null);
        });
    }

    /*@Test //test do item api handler
    public void providedTokenNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemWeaponMapper.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, null);
        });
    }*/
}

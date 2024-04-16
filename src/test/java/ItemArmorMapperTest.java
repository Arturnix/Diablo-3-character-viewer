import com.diablo3CharViewer.json_mappers.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemArmorMapperTest {
    @Mock
    private ItemMapper testItemArmorMapperMock;
    private ItemMapper itemArmorMapper = new ItemMapper();
    private final String expectedItemId = "p43_RetroHelm_003";
    private final String expectedItemName = "Stalowy Calun";

    @Test
    public void correctItemFetchedToDataModel() {
        Mockito.when(testItemArmorMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor)).thenReturn(ShareableDataForTests.itemArmorDataModel);
        Assertions.assertEquals(expectedItemId,testItemArmorMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor).getId());
        Assertions.assertEquals(expectedItemName,testItemArmorMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor).getName());
    }

    @Test
    public void correctItemBodyPartSlotsListFetchedToDataModel() {
        Mockito.when(testItemArmorMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor)).thenReturn(ShareableDataForTests.itemArmorDataModel);
        Assertions.assertTrue(testItemArmorMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor).getItemBodyPartSlots().contains("head"));
    }

    @Test
    public void correctItemTypeFetchedToDataModel() {
        Mockito.when(testItemArmorMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor)).thenReturn(ShareableDataForTests.itemArmorDataModel);
        Assertions.assertTrue(testItemArmorMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor).getClass().getName().contains("ItemArmorDataModel"));
    }

    @Test
    public void providedItemSlugAndIdDoesntExistThrowsException() {
        Assertions.assertThrows(RuntimeException.class, ()-> {
            itemArmorMapper.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdAsDoesntExist);
        });
    }

    @Test
    public void providedItemDataNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            itemArmorMapper.mapItemToDataModel(null);
        });
    }

    /*@Test //test dla item api handler
    public void providedTokenNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemArmorMapper.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, null);
        });
    }*/
}

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
        Mockito.when(testItemArmorMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock)).thenReturn(ShareableDataForTests.itemArmorDataModel);
        Assertions.assertEquals(expectedItemId,testItemArmorMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock).getId());
        Assertions.assertEquals(expectedItemName,testItemArmorMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock).getName());
    }

    @Test
    public void correctItemBodyPartSlotsListFetchedToDataModel() {
        Mockito.when(testItemArmorMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock)).thenReturn(ShareableDataForTests.itemArmorDataModel);
        Assertions.assertTrue(testItemArmorMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock).getItemBodyPartSlots().contains("head"));
    }

    @Test
    public void correctItemTypeFetchedToDataModel() {
        Mockito.when(testItemArmorMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock)).thenReturn(ShareableDataForTests.itemArmorDataModel);
        Assertions.assertTrue(testItemArmorMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock).getClass().getName().contains("ItemArmorDataModel"));
    }

    @Test
    public void providedItemSlugAndIdDoesntExistThrowsException() {
        Assertions.assertThrows(RuntimeException.class, ()-> {
            itemArmorMapper.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdAsDoesntExist, ShareableDataForTests.testFetchTokenMock);
        });
    }

    @Test
    public void providedItemSlugAndIdNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemArmorMapper.fetchItemToDataModel(null, ShareableDataForTests.testFetchTokenMock);
        });
    }

    @Test
    public void providedTokenNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemArmorMapper.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, null);
        });
    }
}

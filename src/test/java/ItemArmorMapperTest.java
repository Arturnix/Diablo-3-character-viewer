import com.diablo3CharViewer.data_models.ItemArmorDataModel;
import com.diablo3CharViewer.data_models.ItemDataModel;
import com.diablo3CharViewer.json_mappers.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ItemArmorMapperTest {
    @Mock
    private ItemMapper testItemMapperMock;
    private ItemMapper itemArmorMapper = new ItemMapper();
    private final List<String> itemBodyPartSlots = new ArrayList<>(
            Arrays.asList("head")
    );
    private final String expectedItemId = "p43_RetroHelm_003";
    private final String expectedItemName = "Stalowy Calun";
    private final Map<String, List<String>> attributes = new HashMap<>() {{
    }};
    private final ItemDataModel itemDataModel = new ItemArmorDataModel(
            itemBodyPartSlots,
            "p43_RetroHelm_003",
            "Stalowy Calun",
            0,
            attributes,
            "21 - 24"
    );
    private final String itemSlugAndId = "veil-of-steel-p43_RetroHelm_003";

    @Test
    public void correctItemArmorFetchedToDataModel() {
        Mockito.when(testItemMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock)).thenReturn(itemDataModel);
        Assertions.assertEquals(expectedItemId,testItemMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getId());
        Assertions.assertEquals(expectedItemName,testItemMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getName());
    }

    @Test
    public void correctItemBodyPartSlotsListFetchedToDataModel() {
        Mockito.when(testItemMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock)).thenReturn(itemDataModel);
        Assertions.assertTrue(testItemMapperMock.fetchItemToDataModel(itemSlugAndId, ShareableDataForTests.testFetchTokenMock).getItemBodyPartSlots().contains("head"));
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
            itemArmorMapper.fetchItemToDataModel(itemSlugAndId, null);
        });
    }

}

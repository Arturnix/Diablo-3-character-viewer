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

    @Test
    public void correctItemTypeFetchedToDataModel() {
        Mockito.when(testItemMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock)).thenReturn(ShareableDataForTests.itemArmorDataModel);
        Mockito.when(testItemMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock)).thenReturn(ShareableDataForTests.itemWeaponDataModel);

        Assertions.assertTrue(testItemMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, ShareableDataForTests.testFetchTokenMock).getClass().getName().contains("ItemArmorDataModel"));
        Assertions.assertTrue(testItemMapperMock.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon, ShareableDataForTests.testFetchTokenMock).getClass().getName().contains("ItemWeaponDataModel"));
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
            itemMapper.fetchItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, null);
        });
    }
}

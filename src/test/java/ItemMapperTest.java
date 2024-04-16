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
        Mockito.when(testItemMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor)).thenReturn(ShareableDataForTests.itemArmorDataModel);
        Mockito.when(testItemMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon)).thenReturn(ShareableDataForTests.itemWeaponDataModel);

        Assertions.assertTrue(testItemMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor).getClass().getName().contains("ItemArmorDataModel"));
        Assertions.assertTrue(testItemMapperMock.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdWeapon).getClass().getName().contains("ItemWeaponDataModel"));
    }

    @Test
    public void providedItemSlugAndIdDoesntExistThrowsException() {
        Assertions.assertThrows(RuntimeException.class, ()-> {
            itemMapper.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdAsDoesntExist);
        });
    }

    @Test
    public void providedItemDataNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            itemMapper.mapItemToDataModel(null);
        });
    }

    /*@Test //test dla item handler api
    public void providedTokenNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, ()-> {
            itemMapper.mapItemToDataModel(ShareableDataForTests.itemSlugAndIdArmor, null);
        });
    }*/
}

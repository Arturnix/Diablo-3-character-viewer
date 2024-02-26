import com.diablo3CharViewer.data_models.AccountDataModel;
import com.diablo3CharViewer.data_models.HeroDataModel;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.token.FetchToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AccountMapperTest {

   @Mock
    private FetchToken testFetchToken = new FetchToken();
   @Mock
    private AccountMapper testAccountMapper = new AccountMapper();

   @Test
    public void isCorrectAccountFetchedToDataModelTest() { // czy obsługiwac w testach zgodnosc danych pobranych jako pozostale pola w data modelu?

       String battleTag = "Jokefish#2265";
       List<HeroDataModel> heroes = new ArrayList<>();
       Map<String, Integer> mapKills = new HashMap<String, Integer>();
       AccountDataModel accountDataModel = new AccountDataModel("Jokefish#2265", 1111, "Phantas Magoria",
               heroes, 70, mapKills);

       lenient().when(testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken)).thenReturn(accountDataModel);

       Assertions.assertEquals("Jokefish#2265", accountDataModel.getBattleTag());

    }
}

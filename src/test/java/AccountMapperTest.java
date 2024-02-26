import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.data_models.AccountDataModel;
import com.diablo3CharViewer.data_models.HeroDataModel;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.token.FetchToken;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//@ExtendWith(MockitoExtension.class)
public class AccountMapperTest {

   // @Mock
    private FetchToken testFetchToken = new FetchToken();
   // @Mock
    private AccountMapper testAccountMapper = new AccountMapper();

   @Test
    public void correctFetchedAccountToDataModelTest() {

        String battleTag = "Jokefish#2265";

        AccountDataModel accountDataModel = testAccountMapper.fetchAccountToDataModel(battleTag, testFetchToken);

       Assertions.assertTrue(accountDataModel.getBattleTag().equals("Jokefish#2265"));

    }
}

import com.diablo3CharViewer.CharacterViewerManager;
import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.json_mappers.HeroMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

@ExtendWith(MockitoExtension.class)
public class CharacterViewerManagerTest {
    @Mock
    private CharacterViewerManager testCharacterViewerManagerMock;
    @Mock
    private Scanner showRequestedData;
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private HeroMapper heroMapper;
    @Mock
    private AccountHandlerApi accountHandlerApi; //= new AccountHandlerApi();

    @Test
    public void wrongBattleTagFormatProvidedShowProfile() {
        Mockito.when(testCharacterViewerManagerMock.profileDataInfoProvider(showRequestedData, accountHandlerApi, accountMapper, ShareableDataForTests.testFetchTokenMock))
                .thenReturn("Niepoprawny format battleTag! Spróbuj ponownie.");

        Assertions.assertTrue(testCharacterViewerManagerMock.profileDataInfoProvider(showRequestedData, accountHandlerApi, accountMapper, ShareableDataForTests.testFetchTokenMock)
                .contains(ShareableDataForTests.wrongBattleTagFormatWarning));
    }

    @Test
    public void wrongBattleTagFormatProvidedShowHero() {
        Mockito.when(testCharacterViewerManagerMock.heroDataInfoProvider(showRequestedData, accountHandlerApi, accountMapper, heroMapper, ShareableDataForTests.testFetchTokenMock))
                .thenReturn("Niepoprawny format battleTag! Spróbuj ponownie.");

        Assertions.assertTrue(testCharacterViewerManagerMock.heroDataInfoProvider(showRequestedData, accountHandlerApi, accountMapper, heroMapper, ShareableDataForTests.testFetchTokenMock)
                .contains(ShareableDataForTests.wrongBattleTagFormatWarning));
    }

   @Test
    public void wrongHeroIdFormatProvidedShowHero() {
        Mockito.when(testCharacterViewerManagerMock.heroDataInfoProvider(showRequestedData, accountHandlerApi, accountMapper, heroMapper, ShareableDataForTests.testFetchTokenMock))
                .thenReturn("Niepoprawny format heroId - tylko cyfry! Spróbuj ponownie.");

        Assertions.assertTrue(testCharacterViewerManagerMock.heroDataInfoProvider(showRequestedData, accountHandlerApi, accountMapper, heroMapper, ShareableDataForTests.testFetchTokenMock)
                .contains(ShareableDataForTests.wrongHeroIdFormatWarning));
    }
}

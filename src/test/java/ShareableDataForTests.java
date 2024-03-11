import com.diablo3CharViewer.token.FetchToken;
import org.mockito.Mock;

public class ShareableDataForTests {
    @Mock
    protected FetchToken testFetchTokenMock;
    protected final FetchToken testFetchToken = new FetchToken();
    protected final String battleTag = "Jokefish#2265";
    protected final String battleTagAsWrongFormat = "abc123";
    protected final String battleTagAsDosentExist = "dsf#123";
    protected final String wrongBattleTagFormatWarning = "Niepoprawny format battleTag! Spróbuj ponownie.";
}

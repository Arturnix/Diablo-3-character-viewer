import com.diablo3CharViewer.token.FetchToken;
import org.mockito.Mock;

public class ShareableDataForTests {
    @Mock
    public static FetchToken testFetchTokenMock;
    public static final FetchToken testFetchToken = new FetchToken();
    public static final String battleTag = "Jokefish#2265";
    public static final String battleTagAsWrongFormat = "abc123";
    public static final String battleTagAsDosentExist = "dsf#123";
    public static final String wrongBattleTagFormatWarning = "Niepoprawny format battleTag! Spróbuj ponownie.";
}

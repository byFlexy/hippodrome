import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Disabled
    @Test
    @Timeout(22)
    public void mainSpeedScore() throws Exception {
        Main.main(null);
    }
}
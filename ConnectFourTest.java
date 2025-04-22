import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectFourTest {

    @Test
    public void testEndsInTie() {
        ConnectFour c = new ConnectFour();
        c.board = new String[][]{
                {"Y", "Y", "R", "Y", "R", "Y", "R"},
                {"Y", "R", "Y", "R", "Y", "R", "Y"},
                {"Y", "R", "Y", "R", "Y", "R", "Y"},
                {"R", "Y", "R", "Y", "R", "Y", "R"},
                {"R", "Y", "R", "Y", "R", "Y", "R"},
                {"Y", "R", "Y", "R", "Y", "R", "Y"}
        };
        assertTrue(c.checkTie());
    }

    @Test
    public void testDoesNotEndInTie() {
        ConnectFour c = new ConnectFour();
        c.board = new String[][]{
                {null, "Y", "R", "Y", "R", "Y", "R"},
                {"Y", "R", "Y", "R", "Y", "R", "Y"},
                {"Y", "R", "Y", "R", "Y", "R", "Y"},
                {"R", "Y", "R", "Y", "R", "Y", "R"},
                {"R", "Y", "R", "Y", "R", "Y", "R"},
                {"Y", "R", "Y", "R", "Y", "R", "Y"}
        };
        assertFalse(c.checkTie());
    }
}

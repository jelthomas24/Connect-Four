import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectFourTest {
    ConnectFour c = new ConnectFour();

    @Test
    public void testEndsInTie() {
        c.board = new String[][]{
                {c.Y_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT},
                {c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT},
                {c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT},
                {c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT},
                {c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT},
                {c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT}
        };
        assertTrue(c.checkTie());
    }

    @Test
    public void testDoesNotEndInTie() {
        c.board = new String[][]{
                {null, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT},
                {c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT},
                {c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT},
                {c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT},
                {c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT},
                {c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT, c.R_TEXT, c.Y_TEXT}
        };
        assertFalse(c.checkTie());
    }

    @Test
    public void testCheckHorizontalWins() {
        // TODO: Make this parametrized for all edge cases
        c.board = new String[][]{
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {c.R_TEXT, c.R_TEXT, c.R_TEXT, c.R_TEXT, null, null, null}
        };
        assertTrue(c.isWinner(c.R_TEXT));
    }

    @Test
    public void testCheckVerticalWins() {
        // TODO: Make this parametrized for all edge cases
        c.board = new String[][]{
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {c.R_TEXT, null, null, null, null, null, null},
                {c.R_TEXT, null, null, null, null, null, null},
                {c.R_TEXT, null, null, null, null, null, null},
                {c.R_TEXT, null, null, null, null, null, null}
        };
        assertTrue(c.isWinner(c.R_TEXT));
    }

    @Test
    public void testCheckDiagonalWins() {
        // TODO: Make this parametrized for all edge cases
        c.board = new String[][]{
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, c.R_TEXT, null, null, null},
                {null, null, c.R_TEXT, null, null, null, null},
                {null, c.R_TEXT, null, null, null, null, null},
                {c.R_TEXT, null, null, null, null, null, null}
        };
        assertTrue(c.isWinner(c.R_TEXT));

        c.board = new String[][]{
                {c.R_TEXT, null, null, null, null, null, null},
                {null, c.R_TEXT, null, null, null, null, null},
                {null, null, c.R_TEXT, null, null, null, null},
                {null, null, null, c.R_TEXT, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
        };
        assertTrue(c.isWinner(c.R_TEXT));
    }
}

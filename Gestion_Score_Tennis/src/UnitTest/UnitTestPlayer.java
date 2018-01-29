import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnitTestPlayer {
    Player player1;
    Player player2;

    @Before
    public void init(){
        player1 = new Player("Deline");
        player2 = new Player("Johan");
    }

    @Test
    public void testCreationPlayer(){
        assertEquals("Deline", player1.getName());
        assertEquals("Johan", player2.getName());
    }
}

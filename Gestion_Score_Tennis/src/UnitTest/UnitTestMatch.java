import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnitTestMatch {

    Player player1;
    Player player2;
    TennisMatch match3;
    TennisMatch match5;

    @Before
    public void init(){
        player1 = new Player("Deline");
        player2 = new Player("Johan");
        match3 = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);
        match5 = new TennisMatch(player1, player2, MatchType.BEST_OF_FIVE, false);
    }

    @Test
    public void testCreationMatch(){
        assertSame(player1, match3.getPlayer1());
        assertSame(player2, match3.getPlayer2());
        assertSame(player1, match5.getPlayer1());
        assertSame(player2, match5.getPlayer2());
    }

    @Test
    public void testUpdateWithPointWonBy(){
        match3.updateWithPointWonBy(player1);
        match5.updateWithPointWonBy(player2);
        assertEquals(match3.pointsForPlayer(player1), "15");
        assertEquals(match5.pointsForPlayer(player2), "15");
        match3.updateWithPointWonBy(player1);
        match5.updateWithPointWonBy(player2);
        assertEquals(match3.pointsForPlayer(player1), "30");
        assertEquals(match5.pointsForPlayer(player2), "30");
        match3.updateWithPointWonBy(player1);
        match5.updateWithPointWonBy(player2);
        assertEquals(match3.pointsForPlayer(player1), "40");
        assertEquals(match5.pointsForPlayer(player2), "40");
        match3.updateWithPointWonBy(player1);
        match5.updateWithPointWonBy(player2);
        assertEquals(match3.pointsForPlayer(player1), "A");
        assertEquals(match5.pointsForPlayer(player2), "A");
    }
}

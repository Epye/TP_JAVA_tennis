import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnitTestMatch {

    Player player1;
    Player player2;
    TennisMatch match3;
    TennisMatch match5;
    TennisMatch match3Tie;
    TennisMatch match5Tie;

    @Before
    public void init(){
        player1 = new Player("Deline");
        player2 = new Player("Johan");
        match3 = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);
        match5 = new TennisMatch(player1, player2, MatchType.BEST_OF_FIVE, false);
        match3Tie = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, true);
        match5Tie = new TennisMatch(player1, player2, MatchType.BEST_OF_FIVE, true);
    }

    @Test
    public void testCreationMatch(){
        assertSame(player1, match3.getPlayer1());
        assertSame(player2, match3.getPlayer2());
        assertSame(player1, match5.getPlayer1());
        assertSame(player2, match5.getPlayer2());
        assertSame(MatchType.BEST_OF_THREE, match3.getMatchType());
        assertSame(MatchType.BEST_OF_FIVE, match5.getMatchType());
    }

    @Test
    public void testUpdateWithPointWonBy(){
        match3.updateWithPointWonBy(player1);
        match5.updateWithPointWonBy(player2);
        assertEquals("15", match3.pointsForPlayer(player1));
        assertEquals("15", match5.pointsForPlayer(player2));
        match3.updateWithPointWonBy(player1);
        match5.updateWithPointWonBy(player2);
        assertEquals("30", match3.pointsForPlayer(player1));
        assertEquals("30", match5.pointsForPlayer(player2));
        match3.updateWithPointWonBy(player1);
        match5.updateWithPointWonBy(player2);
        assertEquals("40", match3.pointsForPlayer(player1));
        assertEquals("40", match5.pointsForPlayer(player2));
        match3.updateWithPointWonBy(player1);
        match5.updateWithPointWonBy(player2);
        assertEquals(1, match3.gamesInSetForPlayer(1, player1));
        assertEquals(1, match5.gamesInSetForPlayer(1, player2));
    }

    @Test
    public void testNewSetIfWin(){
        winSet(match3, player1);

        assertEquals(0, match3.gamesInCurrentSetForPlayer(player1));
        assertEquals(2, match3.currentSetNumber());
    }

    @Test
    public void testWinMatch3(){
        winSet(match3, player1);
        assertEquals(6, match3.gamesInSetForPlayer(1, player1));

        winSet(match3, player2);
        assertEquals(6, match3.gamesInSetForPlayer(2, player2));

        winSet(match3, player1);
        assertEquals(6, match3.gamesInSetForPlayer(3, player1));
    }

    @Test
    public void testWinMatch5(){

        winSet(match5, player1);
        assertEquals(6, match5.gamesInSetForPlayer(1, player1));

        winSet(match5, player2);
        assertEquals(6, match5.gamesInSetForPlayer(2, player2));

        winSet(match5, player1);
        assertEquals(6, match5.gamesInSetForPlayer(3, player1));

        winSet(match5, player2);
        assertEquals(6, match5.gamesInSetForPlayer(4, player2));

        winGames(match5, player1, 5);
        winGames(match5, player2, 5);
        winGames(match5, player1, 1);
        assertEquals(6, match5.gamesInSetForPlayer(5, player1));
        assertTrue(match5.isFinished());
    }

    @Test
    public void testWinMatch3Tie(){
        for(int i=0; i<24; i++){
            match3Tie.updateWithPointWonBy(player1);
        }

        assertEquals(6, match3Tie.gamesInSetForPlayer(1, player1));

        for(int i=0; i<24; i++){
            match3Tie.updateWithPointWonBy(player2);
        }

        assertEquals(6, match3Tie.gamesInSetForPlayer(2, player2));

        for(int i=0; i<24; i++){
            match3Tie.updateWithPointWonBy(player1);
        }

        assertEquals(6, match3Tie.gamesInSetForPlayer(3, player1));
    }

    @Test
    public void testWinMatch5Tie(){

        for(int i=0; i<24; i++){
            match5Tie.updateWithPointWonBy(player1);
        }

        assertEquals(6, match5Tie.gamesInSetForPlayer(1, player1));

        for(int i=0; i<24; i++){
            match5Tie.updateWithPointWonBy(player2);
        }

        assertEquals(6, match5Tie.gamesInSetForPlayer(2, player2));

        for(int i=0; i<24; i++){
            match5Tie.updateWithPointWonBy(player1);
        }

        assertEquals(6, match5Tie.gamesInSetForPlayer(3, player1));

        for(int i=0; i<24; i++){
            match5Tie.updateWithPointWonBy(player2);
        }

        assertEquals(6, match5Tie.gamesInSetForPlayer(4, player2));

        for(int i=0; i<20; i++){
            match5Tie.updateWithPointWonBy(player1);
        }

        for(int i=0; i<20; i++){
            match5Tie.updateWithPointWonBy(player2);
        }

        for(int i=0; i<4; i++){
            match5Tie.updateWithPointWonBy(player1);
        }

        assertEquals(6, match5Tie.gamesInSetForPlayer(5, player1));
        assertTrue(match5Tie.isFinished());
    }

    private void winSet(TennisMatch match, Player player) {
        for(int i=0; i<24; i++){
            match.updateWithPointWonBy(player);
        }
    }

    private void winGames(TennisMatch match, Player player, int nbGames) {
        for(int i=0; i<4*nbGames; i++){
            match.updateWithPointWonBy(player);
        }
    }
}

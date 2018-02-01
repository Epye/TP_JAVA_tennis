import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnitTestJeu {
    Jeu jeu;
    Player player1;
    Player player2;

    @Before
    public void init(){
        player1 = new Player("Johan");
        player2 = new Player("Deline");
        jeu = new Jeu(player1, player2);
    }

    @Test
    public void testCreationJeu(){
        assertEquals( "0", jeu.getCurrentScorePlayer(player1));
        assertEquals( "0", jeu.getCurrentScorePlayer(player2));
    }

    @Test
    public void testWinPoint(){
        jeu.updateWithPointWonBy(player1);
        assertEquals( "15", jeu.getCurrentScorePlayer(player1));
        assertEquals( "0", jeu.getCurrentScorePlayer(player2));
    }

    @Test
    public void testLoosePoint(){
        jeu.updateWithPointWonBy(player1);
        jeu.updateWithPointWonBy(player1);
        jeu.updateWithPointWonBy(player1);

        jeu.updateWithPointWonBy(player2);
        jeu.updateWithPointWonBy(player2);
        jeu.updateWithPointWonBy(player2);
        jeu.updateWithPointWonBy(player2);

        jeu.updateWithPointWonBy(player1);
        jeu.updateWithPointWonBy(player1);

        assertEquals("40", jeu.getCurrentScorePlayer(player2));
        assertEquals("A", jeu.getCurrentScorePlayer(player1));

    }

}

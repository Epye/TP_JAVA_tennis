import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.sax.SAXSource;

import static org.junit.Assert.assertEquals;

public class UnitTestJeuTieBreak {

    JeuTieBreak jeuTieBreak;
    Player player1;
    Player player2;

    @Before
    public void init(){
        player1 = new Player("Johan");
        player2 = new Player("Deline");
        jeuTieBreak = new JeuTieBreak(player1, player2);
    }

    @Test
    public void testCreationJeu(){
        assertEquals( "0", jeuTieBreak.getCurrentScorePlayer(player1));
        assertEquals( "0", jeuTieBreak.getCurrentScorePlayer(player2));
    }

    @Test
    public void testWinPointTieBreak(){
        jeuTieBreak.updateWithPointWonBy(player1);
        assertEquals( "1", jeuTieBreak.getCurrentScorePlayer(player1));
    }
}

import com.googlecode.lanterna.TerminalPosition;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class towerTest {
    Tower tower1 = new  Tower(new TerminalPosition(20, 20), Tower.Type.CloseRange, Generation.MODERN);
    Tower tower2 = new  Tower(new TerminalPosition(20, 20), Tower.Type.LongRange, Generation.FUTURISTIC);
    Tower tower3 = new  Tower(new TerminalPosition(20, 20), Tower.Type.Special, Generation.FUTURISTIC);
    Tower tower4 = new  Tower(new TerminalPosition(20, 20), Tower.Type.Air, Generation.MODERN);

    public towerTest() throws Tower.InvalidTowerException {
    }

    @Test
    public void towerConstructorTest()  throws Tower.InvalidTowerException{


        assertEquals("C", tower1.getRepresentation());
        assertEquals(3, tower2.getRange());
        assertEquals(160.0, tower3.getAttackValue());

    }

    @Test
    public void getGen() throws Tower.InvalidTowerException {

        assertEquals(Generation.FUTURISTIC, tower2.getGen());
        assertEquals(Generation.MODERN, tower1.getGen());


    }

    @Test
    public void getAttackValueTest() throws Tower.InvalidTowerException {
          assertEquals(60.0, tower1.getAttackValue());
        assertEquals(40.0, tower2.getAttackValue());
    }

    @Test
    public void getRangeTest() throws Tower.InvalidTowerException {

        assertEquals(1, tower1.getRange());
        assertEquals(3, tower2.getRange());

    }

    @Test
    public void setpositionTest() throws Tower.InvalidTowerException {
         tower1.setPosition(new TerminalPosition(20, 21));
        assertEquals(21, tower1.getPosition().getRow());
        assertEquals(20, tower1.getPosition().getColumn());

    }

   @Test
    public void geCostTest() throws Tower.InvalidTowerException {

        assertEquals(250, Tower.Type.Special.getCost());
        assertEquals(100, Tower.Type.LongRange.getCost());
        assertEquals(75, Tower.Type.Air.getCost());
   }
   @Test
    public void getrepresentationTest(){

        assertEquals("C", tower1.getRepresentation());
        assertEquals("L", tower2.getRepresentation());
        assertEquals("S", tower3.getRepresentation());
        assertEquals("A", tower4.getRepresentation());
   }
}

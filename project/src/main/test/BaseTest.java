import com.googlecode.lanterna.TerminalPosition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BaseTest {
    Base base1;
@Test
    public void baseConstructorTest(){
    base1 = new Base(new TerminalPosition(20, 20), Settings.Difficulty.EASY, Generation.PREHISTORY);
       int x = base1.getPosition().getColumn();
       int y = base1.getPosition().getRow();
       assertEquals(20, x);
       assertEquals(20, y);
       assertEquals(1000.0, base1.getHealth(), 0.001);
       assertEquals("B", base1.getRepresentation());
    }
}

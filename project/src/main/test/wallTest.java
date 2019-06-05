import com.googlecode.lanterna.TerminalPosition;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class wallTest {
    Wall wall = new Wall(20, 20);
    @Test
    public void wallTest(){

        assertEquals(20, wall.getPosition().getColumn());

        wall.setPosition(new TerminalPosition(25, 26));
        assertEquals(25, wall.getPosition().getColumn());
        assertEquals(26, wall.getPosition().getRow());

    }
}

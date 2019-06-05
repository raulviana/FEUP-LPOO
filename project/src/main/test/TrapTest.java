import com.googlecode.lanterna.TerminalPosition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrapTest {

    private Trap trap1 = new Trap(new TerminalPosition(20, 20), Trap.Type.normal, Settings.STARTING_GENERATION);
    private Trap trap2 = new Trap(new TerminalPosition(20, 20), Trap.Type.special, Settings.STARTING_GENERATION);

    public TrapTest() throws Trap.InvalidTrapException {
    }


    @Test
        public void constructorTest() {
        assertEquals("n", trap1.getRepresentation());
        assertEquals("s", trap2.getRepresentation());
    }

    @Test
        public void getDmgTest() {

            assertEquals(20, trap1.getDmg());
            assertEquals(50, trap2.getDmg());
        }
    @Test
        public void getCostTest() throws Trap.InvalidTrapException {
            assertEquals(20, Trap.Type.normal.getCost());
            assertEquals(50, Trap.Type.special.getCost());
        }

    @Test
        public void getRepresentation(){
            assertEquals("n", trap1.getRepresentation());
            assertEquals("s", trap2.getRepresentation());
    }
    }



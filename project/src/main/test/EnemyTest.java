import com.googlecode.lanterna.TerminalPosition;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class EnemyTest {
    private Player player1 = new Player("Jon", 200);
    private Arena arena = new Arena(Generation.PREHISTORY, player1, Settings.Difficulty.EASY);

    private Enemy enemy1 = new Enemy(arena, new TerminalPosition(20, 20), Enemy.Type.Infantry, Generation.PREHISTORY);
    private Enemy enemy2 = new Enemy(arena, new TerminalPosition(20, 20), Enemy.Type.Infantry, Generation.MODERN);
    private Enemy enemy3 = new Enemy(arena, new TerminalPosition(20, 20), Enemy.Type.Heavy, Generation.PREHISTORY);
    private Enemy enemy4 = new Enemy(arena, new TerminalPosition(20, 20), Enemy.Type.Aerial, Generation.FUTURISTIC);
    private Enemy enemy5 = new Enemy(arena, new TerminalPosition(20, 20), Enemy.Type.Ranged, Generation.MEDIEVAL);
    private Enemy enemy6 = new Enemy(arena, new TerminalPosition(20, 20), Enemy.Type.Cavalry, Generation.MODERN);
    private Enemy enemy7 = new Enemy(arena, new TerminalPosition(20, 20), Enemy.Type.Ranged, Generation.PREHISTORY);


    public EnemyTest() throws Exception {
    }

    @Test
    public void getHealthTest() {
        assertEquals(20.0, enemy1.getHealth());
        assertEquals(60.0, enemy2.getHealth());
        assertEquals(75.0, enemy3.getHealth());
        assertEquals(120.0, enemy4.getHealth());
        assertEquals(30.0, enemy5.getHealth());
        assertEquals(150.0, enemy6.getHealth());
        assertEquals(15.0, enemy7.getHealth());
    }

    @Test
    public void getAttackValueTest() {
        assertEquals(5.0, enemy1.getAttackValue());
        assertEquals(15.0, enemy2.getAttackValue());
        assertEquals(25.0, enemy3.getAttackValue());
        assertEquals(40.0, enemy4.getAttackValue());
        assertEquals(12.0, enemy5.getAttackValue());
        assertEquals(30.0, enemy6.getAttackValue());
        assertEquals(6.0, enemy7.getAttackValue());
    }

    @Test
    public void getRangeTest() {
        assertEquals(1, enemy1.getRange());
        assertEquals(1, enemy2.getRange());
        assertEquals(1, enemy3.getRange());
        assertEquals(2, enemy4.getRange());
        assertEquals(3, enemy5.getRange());
        assertEquals(1, enemy6.getRange());
        assertEquals(3, enemy7.getRange());
    }

    @Test
    public void getPrizeTest() {
        assertEquals(20, enemy1.getPrize());
        assertEquals(20, enemy2.getPrize());
        assertEquals(75, enemy3.getPrize());
        assertEquals(30, enemy4.getPrize());
        assertEquals(25, enemy5.getPrize());
        assertEquals(50, enemy6.getPrize());
        assertEquals(25, enemy7.getPrize());
    }

    @Test
    public void hitTest() {
        enemy1.hit(5.0);
        enemy2.hit(5.0);
        enemy3.hit(5.0);
        enemy4.hit(5.0);
        enemy5.hit(5.0);
        enemy6.hit(5.0);
        enemy7.hit(5.0);
        assertEquals(15.0, enemy1.getHealth());
        assertEquals(55.0, enemy2.getHealth());
        assertEquals(70.0, enemy3.getHealth());
        assertEquals(115.0, enemy4.getHealth());
        assertEquals(25.0, enemy5.getHealth());
        assertEquals(145.0, enemy6.getHealth());
        assertEquals(10.0, enemy7.getHealth());
    }

    @Test
    public void moveTest() throws Enemy.InvalidMovementException {
        enemy1.move(Movement.UP);
        assertEquals(19, enemy1.getPosition().getRow());
        enemy1.move(Movement.DOWN);
        assertEquals(20, enemy1.getPosition().getRow());
        enemy1.move(Movement.LEFT);
        assertEquals(19, enemy1.getPosition().getColumn());
        enemy1.move(Movement.RIGHT);
        assertEquals(20, enemy1.getPosition().getColumn());
    }

    @Test
    public void typeTest() {
        assertEquals(Enemy.Type.Infantry, enemy1.getType());
        assertEquals(Enemy.Type.Heavy, enemy3.getType());
        assertEquals(Enemy.Type.Aerial, enemy4.getType());
        assertEquals(Enemy.Type.Ranged, enemy5.getType());
        assertEquals(Enemy.Type.Cavalry, enemy6.getType());
    }
}



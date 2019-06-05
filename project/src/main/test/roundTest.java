import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class roundTest {
    @Test
    public void testround() throws Exception {
        Player player = new Player("Jon", 200);
        Arena arena1 = new Arena(Generation.PREHISTORY, player, Settings.Difficulty.EASY);
        List<Enemy> enemies = Round.ROUND1.getEnemies(arena1);
        assertEquals(2, enemies.size());

        Arena arena2 = new Arena(Generation.PREHISTORY, player, Settings.Difficulty.MEDIUM);
        enemies = Round.ROUND1.getEnemies(arena2);
        assertEquals(2, enemies.size());

        Arena arena3 = new Arena(Generation.PREHISTORY, player, Settings.Difficulty.HARD);
        enemies = Round.ROUND1.getEnemies(arena3);
        assertEquals(2, enemies.size());

        Arena arena4 = new Arena(Generation.PREHISTORY, player, Settings.Difficulty.EASY);
        enemies = Round.ROUND2.getEnemies(arena4);
        assertEquals(3, enemies.size());

        Arena arena5 = new Arena(Generation.PREHISTORY, player, Settings.Difficulty.MEDIUM);
        enemies = Round.ROUND3.getEnemies(arena5);
        assertEquals(4, enemies.size());

        Arena arena6 = new Arena(Generation.PREHISTORY, player, Settings.Difficulty.HARD);
        enemies = Round.ROUND3.getEnemies(arena6);
        assertEquals(4, enemies.size());
    }
}

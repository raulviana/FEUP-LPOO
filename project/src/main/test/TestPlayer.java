import org.junit.Test;

import static org.junit.Assert.*;

public class TestPlayer {
    private Player player1 = new Player("Mary", 200);

    @Test
    public void getName() {
        assertEquals("Mary", player1.getName());
    }

    @Test
    public void setName() {
        player1.setName("MaryHanna");
        assertEquals("MaryHanna", player1.getName());
    }

    @Test
    public void getBalance() {
        assertEquals(200, player1.getBalance());
    }

    @Test
    public void setBalance() {
        player1.setBalance(400);
        assertEquals(400, player1.getBalance());
    }
    @Test
    public void addBalance(){
        player1.setBalance(300);
        player1.addBalance(10);
        assertEquals(310, player1.getBalance());
    }
}
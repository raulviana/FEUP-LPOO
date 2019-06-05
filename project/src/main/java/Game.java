import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;
    private UI ui;

    public Game() throws Exception {
        ui = new UI();
        ui.awaitStart();

        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(Settings.WIDTH,Settings.HEIGHT)).createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }

        Player player = new Player(ui.getName(), ui.getDifficulty().getInitBal());
        arena = new Arena(Settings.STARTING_GENERATION, player, ui.getDifficulty());
        ui.assignArena(arena);
    }

    private void draw() throws Exception {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        ui.draw(graphics);
        arena.draw(graphics);
        screen.refresh();
    }

    private void endGame(GameStatus gameStatus, Arena arena) throws Exception {
        screen.close();
        EndGame endgame = new EndGame(gameStatus, arena);
        endgame.awaitStart();
        System.exit(0);
        return;
    }

    public void run() throws Exception {
        while(arena.getSettings().getGameStatus().equals(GameStatus.ONGOING)) {
            draw();
            if(arena.getSettings().isGameOn()) {
                if(arena.getEnemies().isEmpty() && arena.getSettings().getAreSpawnsFinished())
                    if(arena.getSettings().getRound().equals(Round.ROUND12)) arena.endGame(true);
                    else arena.getSettings().toggleGameStatus();
                    arena.moveEnemies();
                    arena.checkTraps();
                    arena.attackEnemies();
                    Thread.sleep(Settings.GAME_SPEED);
            } else {
                KeyStroke key = screen.readInput();
                if (key.getKeyType().equals(KeyType.EOF)) break;
                processKey(key);
            }
        }
        endGame(arena.getSettings().getGameStatus(), arena);
    }

    private void processKey(KeyStroke key) throws Exception {
        if(arena.getSettings().isGameOn()) return;
        if(arena.getSettings().isPlaceDefenseModeOn()) {
            switch(key.getKeyType()) {
                case ArrowUp:
                    arena.moveMenu(Movement.UP);
                    break;
                case ArrowDown:
                    arena.moveMenu(Movement.DOWN);
                    break;
                case Enter:
                    if (arena.placeDefense(arena.getPlacer().getPosition()) == 0) arena.closeDefenseMenu();
                    break;
                default:
                    arena.closeDefenseMenu();
            }
        } else {
            switch(key.getKeyType()) {
                case ArrowUp:
                    arena.movePlacer(Movement.UP);
                    break;
                case ArrowDown:
                    arena.movePlacer(Movement.DOWN);
                    break;
                case ArrowLeft:
                    arena.movePlacer(Movement.LEFT);
                    break;
                case ArrowRight:
                    arena.movePlacer(Movement.RIGHT);
                    break;
                case Backspace:
                    arena.getSettings().toggleGameStatus();
                    arena.startNextRound();
                    break;
                case Enter:
                    if (arena.isValidForTrap(arena.getPlacer().getPosition()))
                        if(arena.isValidForTower(arena.getPlacer().getPosition())) arena.openDefenseMenu(arena.getPlacer().getPosition(), true);
                        else arena.openDefenseMenu(arena.getPlacer().getPosition(), false);
                    //#TODO else UPGRADES DE TORRES/BASE;
                    break;
            }
        }
    }

    public void close() {

    }
}
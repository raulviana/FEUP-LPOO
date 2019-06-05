import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.*;

public class Arena {

    static TerminalPosition BASE_POS() {return new TerminalPosition((Settings.ARENA_WIDTH +1)/2, (Settings.ARENA_HEIGHT)/2);}
    static TerminalPosition NORTH_ENTRANCE() {return new TerminalPosition((Settings.ARENA_WIDTH +1)/2, 1);}
    static TerminalPosition SOUTH_ENTRANCE() {return new TerminalPosition((Settings.ARENA_WIDTH +1)/2, Settings.ARENA_HEIGHT -1);}
    static TerminalPosition WEST_ENTRANCE() {return new TerminalPosition(2, Settings.ARENA_HEIGHT /2);}
    static TerminalPosition EAST_ENTRANCE() {return new TerminalPosition(Settings.ARENA_WIDTH -1, Settings.ARENA_HEIGHT /2);}

    private Player player;
    private Base base;
    private Placer placer;
    private PlaceMenu defenseMenu;
    private List<Wall> walls = new ArrayList<>();
    private List<Tower> towers = new ArrayList<>();
    private List<Trap> traps = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Element> elements = new ArrayList<>();
    private Settings settings;
    private Generation gen;

    public Arena(Generation gen, Player player, Settings.Difficulty difficulty) throws Exception {
        this.gen = gen;
        this.player = player;
        base = new Base(BASE_POS(), difficulty, gen);
        elements.add(base);
        placer = new Placer(base.getPosition());
        createWalls();
        settings = new Settings(difficulty);
    }

    public void draw(TextGraphics graphics) throws Exception {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.BACK_BG));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Settings.ARENA_WIDTH+2, Settings.ARENA_HEIGHT+1), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.BG));
        graphics.fillRectangle(new TerminalPosition(2, 1), new TerminalSize(Settings.ARENA_WIDTH-2, Settings.ARENA_HEIGHT-1), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Tower tower : towers)
            tower.draw(graphics);
        for (Trap trap : traps)
            trap.draw(graphics);
        for (Enemy enemy : enemies)
            enemy.draw(graphics);
        base.draw(graphics);
        if(!settings.isGameOn()) {
            placer.draw(graphics);
            if (isElement(placer.getPosition())) redraw(graphics, placer.getPosition());
        }
        if(settings.isPlaceDefenseModeOn()) defenseMenu.draw(graphics);
    }

    private void redraw(TextGraphics graphics, TerminalPosition pos) {
        if(isBase(pos)) {
            base.draw(graphics);
            return;
        }
        for(Tower tower : towers)
            if(pos.equals(tower.getPosition())) {
                tower.draw(graphics);
                return;
            }
        for (Trap trap : traps)
            if(pos.equals(trap.getPosition())) {
                trap.draw(graphics);
                return;
            }
    }

    public Placer getPlacer() {
        return placer;
    }

    public Base getBase() {
        return base;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Generation getGeneration() {
        return gen;
    }

    private void createWalls() throws Exception {
        for (int c = 2; c < Settings.ARENA_WIDTH; c++) {
            if(!(c == (Settings.ARENA_WIDTH+1)/2 && gen.compareTo(Generation.PREHISTORY) >= 0)) walls.add(new Wall(c, 1));
            if(!(c == (Settings.ARENA_WIDTH+1)/2 && gen.compareTo(Generation.FUTURISTIC) >= 0)) walls.add(new Wall(c, Settings.ARENA_HEIGHT - 1));
        }

        for (int r = 1; r < Settings.ARENA_HEIGHT - 1; r++) {
            if(!(r == Settings.ARENA_HEIGHT/2 && gen.compareTo(Generation.MEDIEVAL) >= 0)) walls.add(new Wall(2, r));
            if(!(r == Settings.ARENA_HEIGHT/2 && gen.compareTo(Generation.MODERN) >= 0)) walls.add(new Wall(Settings.ARENA_WIDTH - 1, r));
        }

        createObstacles();
    }

    private void createObstacles() throws Exception {
        int randInt;
        TerminalPosition randPos;
        WallShapes block;
        for(int i = 0; i < 50; i++) {
            randInt = new Random().nextInt(5);
            randPos = getRandomPosition();
            switch (randInt) {
                case 0:
                    block = WallShapes.I;
                    break;
                case 1:
                    block = WallShapes.L;
                    break;
                case 2:
                    block = WallShapes.O;
                    break;
                case 3:
                    block = WallShapes.T;
                    break;
                case 4:
                    block = WallShapes.Z;
                    break;
                default:
                    throw new InvalidRandomIntException();
            }
            walls.addAll(removeInvalidWalls(block.getWalls(randPos)));
        }
    }

    private List<Wall> removeInvalidWalls(List<Wall> walls) throws Exception {
        List<Wall> toRemove = new ArrayList<>();
        for (Wall w : walls)
            if (isInvalidWall(w)) toRemove.add(w);
        for (Wall w : toRemove)
            walls.remove(w);
        List<Element> tmp = new ArrayList<>(this.walls);
        tmp.addAll(walls);
        Path testPath1 = new Path(NORTH_ENTRANCE(), BASE_POS(), tmp);
        Path testPath2 = new Path(WEST_ENTRANCE(), BASE_POS(), tmp);
        Path testPath3 = new Path(EAST_ENTRANCE(), BASE_POS(), tmp);
        Path testPath4 = new Path(SOUTH_ENTRANCE(), BASE_POS(), tmp);
        if (testPath1.getPath().isEmpty() || testPath2.getPath().isEmpty() || testPath3.getPath().isEmpty() || testPath4.getPath().isEmpty()) return new ArrayList<>();
        else return walls;
    }

    private boolean isInvalidWall(Wall w) {
        if (w.getPosition().getColumn() <= 2 || w.getPosition().getColumn() >= Settings.ARENA_WIDTH - 1) return true;
        if (w.getPosition().getRow() <= 1 || w.getPosition().getRow() >= Settings.ARENA_HEIGHT - 1) return true;
        if (getDistance(w.getPosition(), BASE_POS()) < 2) return true;
        return isElement(w.getPosition());
    }

    private TerminalPosition getRandomPosition() {
        int randX = new Random().nextInt(Settings.ARENA_WIDTH-3)+2;
        int randY = new Random().nextInt(Settings.ARENA_WIDTH-3)+1;
        return new TerminalPosition(randX, randY);
    }

    public boolean isBase(TerminalPosition pos) {
        return base.getPosition().equals(pos);
    }

    public boolean isWall(TerminalPosition pos) {
        for (Wall w : walls)
            if (w.getPosition().equals(pos)) return true;
        return false;
    }

    public boolean isElement(TerminalPosition pos) {
        for (Element e : elements)
            if (e.getPosition().equals(pos)) return true;
        return false;
    }

    public boolean isValidForTrap(TerminalPosition pos) {
        return !isElement(pos);
    }

    public boolean isValidForTower(TerminalPosition pos) throws Exception {
        List<Element> tmp = new ArrayList<>(walls);
        tmp.addAll(towers);
        tmp.add(new Tower(pos, Tower.Type.CloseRange, Generation.PREHISTORY));
        Path testPath1 = new Path(NORTH_ENTRANCE(), BASE_POS(), tmp);
        Path testPath2 = new Path(WEST_ENTRANCE(), BASE_POS(), tmp);
        Path testPath3 = new Path(EAST_ENTRANCE(), BASE_POS(), tmp);
        Path testPath4 = new Path(SOUTH_ENTRANCE(), BASE_POS(), tmp);
        return !(testPath1.getPath().isEmpty() || testPath2.getPath().isEmpty() || testPath3.getPath().isEmpty() || testPath4.getPath().isEmpty() || isElement(pos));
    }

    public Settings getSettings() {
        return settings;
    }

    public void movePlacer(Movement movement) throws Exception {
        TerminalPosition newPos;
        switch(movement) {
            case UP:
                newPos = new TerminalPosition(placer.getPosition().getColumn(), placer.getPosition().getRow()-1);
                if(canMovePlacer(newPos)) placer.setPosition(newPos);
                else
                    if (canMovePlacer(new TerminalPosition(placer.getPosition().getColumn(), placer.getPosition().getRow()-2)))
                        placer.setPosition(new TerminalPosition(placer.getPosition().getColumn(), placer.getPosition().getRow()-2));
                break;
            case DOWN:
                newPos = new TerminalPosition(placer.getPosition().getColumn(), placer.getPosition().getRow()+1);
                if(canMovePlacer(newPos)) placer.setPosition(newPos);
                else
                    if (canMovePlacer(new TerminalPosition(placer.getPosition().getColumn(), placer.getPosition().getRow()+2)))
                        placer.setPosition(new TerminalPosition(placer.getPosition().getColumn(), placer.getPosition().getRow()+2));
                break;
            case LEFT:
                newPos = new TerminalPosition(placer.getPosition().getColumn()-1, placer.getPosition().getRow());
                if(canMovePlacer(newPos)) placer.setPosition(newPos);
                else
                    if (canMovePlacer(new TerminalPosition(placer.getPosition().getColumn()-2, placer.getPosition().getRow())))
                        placer.setPosition(new TerminalPosition(placer.getPosition().getColumn()-2, placer.getPosition().getRow()));
                break;
            case RIGHT:
                newPos = new TerminalPosition(placer.getPosition().getColumn()+1, placer.getPosition().getRow());
                if(canMovePlacer(newPos)) placer.setPosition(newPos);
                else
                    if (canMovePlacer(new TerminalPosition(placer.getPosition().getColumn()+2, placer.getPosition().getRow())))
                        placer.setPosition(new TerminalPosition(placer.getPosition().getColumn()+2, placer.getPosition().getRow()));
                break;
        }
    }

    private boolean canMovePlacer(TerminalPosition pos) {
        boolean test = isWall(pos);
        if(pos.equals(NORTH_ENTRANCE()) || pos.equals(EAST_ENTRANCE()) ||
                pos.equals(WEST_ENTRANCE()) || pos.equals(SOUTH_ENTRANCE())) test = true;
        if (pos.getColumn() <= 2 || pos.getColumn() >= Settings.ARENA_WIDTH - 1) test = true;
        if (pos.getRow() <= 1 || pos.getRow() >= Settings.ARENA_HEIGHT - 1) test = true;
        return !test;
    }

    public void openDefenseMenu(TerminalPosition position, boolean canHaveTowers) {
        defenseMenu = new PlaceMenu(position, 1, gen, player.getBalance(), canHaveTowers);
        settings.setPlaceDefenseMode(true);
    }

    public void moveMenu(Movement movement) {
        int newOpt = defenseMenu.getSelectedOpt();
        if(movement.equals(Movement.UP) && newOpt > 1) {
            defenseMenu.setSelectedOpt(--newOpt);
        } else if(movement.equals(Movement.DOWN) && newOpt < settings.numTowerTypes + settings.numTrapTypes) {
            defenseMenu.setSelectedOpt(++newOpt);
        }
    }

    public void closeDefenseMenu() {
        defenseMenu = null;
        settings.setPlaceDefenseMode(false);
    }

    public int placeDefense(TerminalPosition position) throws Exception {
        int opt = defenseMenu.getSelectedOpt();
        if(defenseMenu.getCanHaveTowers()) {
            if (opt >= 1 && opt <= settings.numTowerTypes) {
                Tower.Type type = getTowerType(opt);
                if (player.getBalance() < type.getCost()) return 1;
                Tower tower = new Tower(position, type, gen);
                player.setBalance(player.getBalance() - type.getCost());
                elements.add(tower);
                towers.add(tower);
                return 0;
            } else if (opt > settings.numTowerTypes && opt <= (settings.numTowerTypes + settings.numTrapTypes)) {
                Trap.Type type = getTrapType(opt);
                if (player.getBalance() < type.getCost()) return 1;
                Trap trap = new Trap(position, type, gen);
                player.setBalance(player.getBalance() - type.getCost());
                elements.add(trap);
                traps.add(trap);
                return 0;
            } else throw new InvalidOptionException();
        } else {
            Trap.Type type = getTrapType(opt + settings.numTowerTypes);
            if (player.getBalance() < type.getCost()) return 1;
            Trap trap = new Trap(position, type, gen);
            player.setBalance(player.getBalance() - type.getCost());
            elements.add(trap);
            traps.add(trap);
            return 0;
        }
    }

    public void moveEnemies() throws Enemy.InvalidMovementException {
        for (Enemy enemy : enemies) {
            if (getDistance(enemy.getPosition(), base.getPosition()) > enemy.getRange()) enemy.applyNextMove();
            else if(base.getHealth() - enemy.getAttackValue() <= 0) endGame(false);
                else enemy.attack(base);
        }
    }

    public void attackEnemies() {
        for (Tower tower : towers) {
            for (Enemy enemy : enemies) {
                if(getDistance(tower.getPosition(), enemy.getPosition()) <= tower.getRange()) {
                    if((enemy.getType().equals(Enemy.Type.Aerial) && !tower.getType().equals(Tower.Type.Air)) || (!enemy.getType().equals(Enemy.Type.Aerial) && tower.getType().equals(Tower.Type.Air))) continue;
                    if (enemy.getHealth() - tower.getAttackValue() <= 0) {
                        enemies.remove(enemy);
                        player.addBalance(enemy.getPrize());
                    }
                    else enemy.hit(tower.getAttackValue());
                    break;
                }
            }
        }
    }

    public double getDistance(TerminalPosition pos1, TerminalPosition pos2) {
        return Math.sqrt(Math.pow(pos1.getColumn()-pos2.getColumn(), 2) + Math.pow(pos1.getRow()-pos2.getRow(), 2));
    }

    private Tower.Type getTowerType(int opt) throws InvalidOptionException {
        int tmp = 1;
        for(Tower.Type type : Tower.Type.values()) {
            if (tmp == opt) return type;
            tmp++;
        }
        throw new InvalidOptionException();
    }

    private Trap.Type getTrapType(int opt) throws InvalidOptionException {
        int tmp = settings.numTowerTypes + 1;
        for(Trap.Type type : Trap.Type.values()) {
            if (tmp == opt) return type;
            tmp++;
        }
        throw new InvalidOptionException();
    }

    public Player getPlayer() {
        return player;
    }

    public void startNextRound() throws Exception {
        settings.setRound(settings.getRound().next());
        settings.setAreSpawnsFinished(false);
        goToNextGenIfNeeded();
        List<Enemy> enemiesToAdd = settings.getRound().getEnemies(this);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int i = 0;
            public void run() {
                enemies.add(enemiesToAdd.get(i));
                i++;
                if (i >= enemiesToAdd.size()) {
                    timer.cancel();
                    settings.setAreSpawnsFinished(true);
                }
            }
        }, 0, 1000);
    }

    public void goToNextGenIfNeeded() {
        Wall toRemove = null;
        switch (settings.getRound()) {
            case ROUND4:
                gen = Generation.MEDIEVAL;
                base.setGen(gen);
                for(Tower t : towers)
                    t.setGen(gen);
                for(Trap t : traps)
                    t.setGen(gen);
                for (Wall w : walls)
                    if (w.getPosition().equals(WEST_ENTRANCE())) toRemove = w;
                break;
            case ROUND7:
                gen = Generation.MODERN;
                base.setGen(gen);
                for(Tower t : towers)
                    t.setGen(gen);
                for(Trap t : traps)
                    t.setGen(gen);
                for (Wall w : walls)
                    if (w.getPosition().equals(EAST_ENTRANCE())) toRemove = w;
                break;
            case ROUND10:
                gen = Generation.FUTURISTIC;
                base.setGen(gen);
                for(Tower t : towers)
                    t.setGen(gen);
                for(Trap t : traps)
                    t.setGen(gen);
                for (Wall w : walls)
                    if (w.getPosition().equals(SOUTH_ENTRANCE())) toRemove = w;
                break;
        }
        if (toRemove != null) walls.remove(toRemove);
    }

    public void checkTraps() {
        List<Trap> tToRemove = new ArrayList<>();
        List<Enemy> eToRemove = new ArrayList<>();
        for(Enemy e : enemies) {
            for (Trap t : traps)
                if (e.getPosition().equals(t.getPosition())) {
                    if (e.getHealth() - t.getDmg() <= 0) eToRemove.add(e);
                    else e.hit(t.getDmg());
                    tToRemove.add(t);
                }
        }
        traps.removeAll(tToRemove);
        enemies.removeAll(eToRemove);
    }

    public void endGame(boolean isGameWon) {
        if(isGameWon) settings.setGameStatus(GameStatus.GAME_WON);
        else settings.setGameStatus(GameStatus.GAME_LOST);
    }

    private class InvalidOptionException extends Exception {
        public InvalidOptionException(){
            super("Invalid Option");
        }
    }

    private class InvalidRandomIntException extends Exception {
        public InvalidRandomIntException(){
            super("Invalid Random Integer Generated");
        }
    }
}

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena {
    private int width;
    private int heigth;
    private Position position = new Position(10, 10);
    private Hero hero = new Hero(position);
    private List<Wall> walls;
    private List<Coins> coins;
    private List<Monster> monsters;


    Arena(int width, int heigth){
        this.heigth = heigth;
        this.width = width;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < this.getWidth(); c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, this.getHeigth() - 1));
        }

        for (int r = 1; r < this.getHeigth() - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(this.getWidth() - 1, r));
        }
        return walls;
    }
    private List<Coins> createCoins() {
        Random random = new Random();
        ArrayList<Coins> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            coins.add(new Coins(random.nextInt(width - 2) + 1, random.nextInt(heigth - 2) + 1));
        }
        return coins;
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(new Position(random.nextInt(width - 2) + 1, random.nextInt(heigth - 2) + 1)));
        return monsters;
    }

    /*public boolean checkplace(List<Coins> coins){
        for (int i = 0; i < coins.size(); i++){
            for (int j = 0; j < coins.size(); j++){
                if (i == j) continue;
                else{
                    if(coins.get(i).equals(coins.get(i))) return true;
                }
            }
        }
        return false;
    }*/

    public int getWidth() {
        return width;
    }
    public int getHeigth() {
        return heigth;
    }

    private void moveHero(Position position){
        if(canMoveHero(position)){
            hero.setPosition(position);
            ArrayList<Coins> toRemove = new ArrayList<>();
            for (Coins coin : coins){
                if(coin.getPosition().equals(position)) {
                    toRemove.add(coin);
                }
            }
            retreiveCoins(toRemove);
        }
    }

    public void retreiveCoins(ArrayList<Coins> toRemove){
        coins.removeAll(toRemove);
        if(coins.size() == 0) {
            System.out.println("***************");
            System.out.println("   Ganhaste!!");
            System.out.println("***************");
            System.exit(0);
        }
    }

    public boolean canMoveHero(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position)) return false;
        }
        for(Monster monster : monsters){
            if(monster.getPosition().equals(position)){
                System.out.println("*******************");
                System.out.println("     Perdeste!!");
                System.out.println("*******************");
                System.exit(0);
            }
        }
        return true;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, heigth), ' ');
        for (int i = 0; i < walls.size(); i++) walls.get(i).draw(graphics);
        for (int i = 0; i < coins.size(); i++) coins.get(i).draw(graphics);
        for (Monster monster : monsters) monster.draw(graphics);
        hero.draw(graphics);
    }

    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                System.exit(0);
        }
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                moveMonsters();
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                moveMonsters();
                break;
            case ArrowLeft:
                moveHero(hero.MoveLeft());
                moveMonsters();
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                moveMonsters();
        }
    }

    public void moveMonsters(){
        for(Monster monster : monsters){
            Position newPosition = monster.move();
            if(canMoveMonster(newPosition)) monster.setPosition(newPosition);
            else monster.setPosition(position);
        }
    }
    public boolean canMoveMonster(Position newPosition){
        for(Wall wall : walls){
            if(wall.getPosition().equals(newPosition)) return false;
        }
        return true;
    }
}

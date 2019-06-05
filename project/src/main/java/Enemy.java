import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Element {
    Type type;
    private Generation gen;
    private double health;
    private double attackValue;
    private int range;
    private int prize;
    private String representation;
    private Path path; // Path the enemy will pursue to achieve an attacking position against the Player's Base.
    private int move; // Variable that hols the information on what step of the path the enemy currently is in.

    public Enemy(Arena arena, TerminalPosition position, Type type, Generation generation) throws Exception {
        super(position);
        this.type = type;
        this.gen = generation;
        List<Element> tmp = new ArrayList<>();
        if(type != Type.Aerial) {
            tmp.addAll(arena.getWalls());
            tmp.addAll(arena.getTowers());
        }
        this.path = new Path(position, arena.getBase().getPosition(), tmp);
        move = 1;
        switch(type) {
            case Infantry:
                this.health = 20 * (gen.ordinal() + 1);
                this.attackValue = 5 * (gen.ordinal() + 1);
                this.range = 1;
                this.prize = 20;
                this.representation = "I";
                break;
            case Ranged:
                this.health = 15 * (gen.ordinal() + 1);
                this.attackValue = 6 * (gen.ordinal() + 1);
                this.range = 3;
                this.prize = 25;
                this.representation = "R";
                break;
            case Cavalry:
                this.health = 50 * (gen.ordinal() + 1);
                this.attackValue = 10 * (gen.ordinal() + 1);
                this.range = 1;
                this.prize = 50;
                this.representation = "C";
                break;
            case Heavy:
                this.health = 75 * (gen.ordinal() + 1);
                this.attackValue = 25 * (gen.ordinal() + 1);
                this.range = 1;
                this.prize = 75;
                this.representation = "H";
                break;
            case Aerial:
                this.health = 30 * (gen.ordinal() + 1);
                this.attackValue = 10 * (gen.ordinal() + 1);
                this.range = 2;
                this.prize = 30;
                this.representation = "A";
                break;
            default:
                throw new InvalidEnemyException();
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.ENEMY(gen)));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.getPosition().getColumn(), this.getPosition().getRow()), representation);
    }

    public Type getType() {
        return type;
    }

    public int getRange() {
        return range;
    }

    public double getHealth() {
        return health;
    }

    public double getAttackValue() {
        return attackValue;
    }

    public void applyNextMove() throws InvalidMovementException {
        this.move(path.getPath().get(move));
        move++;
    }

    public void attack(Base base) {
        base.hit(this.attackValue);
    }

    public void hit(double healthTaken) {
        health = health - healthTaken;
    }

    public void move(Movement movement) throws InvalidMovementException {
        switch(movement) {
            case UP:
                this.setPosition(new TerminalPosition(this.getPosition().getColumn(), this.getPosition().getRow()-1));
                break;
            case DOWN:
                this.setPosition(new TerminalPosition(this.getPosition().getColumn(), this.getPosition().getRow()+1));
                break;
            case LEFT:
                this.setPosition(new TerminalPosition(this.getPosition().getColumn()-1, this.getPosition().getRow()));
                break;
            case RIGHT:
                this.setPosition(new TerminalPosition(this.getPosition().getColumn()+1, this.getPosition().getRow()));
                break;
            default:
                throw new InvalidMovementException();
        }
    }

    public int getPrize() {
        return prize;
    }

    public enum Type {
        Infantry,
        Ranged,
        Cavalry,
        Heavy,
        Aerial;

        public int getRange() throws InvalidEnemyException {
            switch (this) {
                case Infantry:
                case Cavalry:
                case Heavy:
                    return 1;
                case Ranged:
                    return 3;
                case Aerial:
                    return 2;
                default:
                    throw new InvalidEnemyException();
            }
        }
    }

    public static class InvalidEnemyException extends Exception {
        public InvalidEnemyException(){
            super("Invalid Enemy");
        }
    }

    public class InvalidMovementException extends Exception {
        public InvalidMovementException(){
            super("Invalid Movement");
        }
    }
}

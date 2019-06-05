import com.googlecode.lanterna.TerminalPosition;

public class Trap extends Defense {
    private int dmg;
    private int range;

    public Trap(TerminalPosition position, Type type, Generation generation) throws InvalidTrapException {
        super(position);
        this.setGen(generation);
        switch(type) {
            case normal:
                this.dmg = 20 * (getGen().ordinal() + 1);
                this.range = 0;
                this.setRepresentation("n");
                break;
            case special:
                this.dmg = 50 * (getGen().ordinal() + 1);
                this.range = 1;
                this.setRepresentation("s");
                break;
            default:
                throw new InvalidTrapException();
        }
    }

    public enum Type {
        normal,
        special;

        public int getCost() throws InvalidTrapException {
            switch(this) {
                case normal:
                    return 20;
                case special:
                    return 50;
                default:
                    throw new InvalidTrapException();
            }
        }

        public String getRepresentation() throws InvalidTrapException {
            switch(this) {
                case normal:
                    return "n";
                case special:
                    return "s";
                default:
                    throw new InvalidTrapException();
            }
        }
    }

    public int getDmg() {
        return dmg;
    }

    public static class InvalidTrapException extends Exception {
        public InvalidTrapException(){
            super("Invalid Trap");
        }
    }
}

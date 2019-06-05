import com.googlecode.lanterna.TerminalPosition;

public class Tower extends Defense {
    Type type;
    private double attackValue;
    private int range;


    public double getAttackValue() {
        return attackValue;
    }

    public int getRange() {
        return range;
    }

    public Tower(TerminalPosition position, Type type, Generation generation) throws InvalidTowerException {
        super(position);
        this.type = type;
        this.setGen(generation);
        switch(type) {
            case CloseRange:
                this.attackValue = 20 * (getGen().ordinal() + 1);
                this.range = 1;
                this.setRepresentation("C");
                break;
            case LongRange:
                this.attackValue = 10 * (getGen().ordinal() + 1);
                this.range = 3;
                this.setRepresentation("L");
                break;
            case Special:
                this.attackValue = 40 * (getGen().ordinal() + 1);
                this.range = 3;
                this.setRepresentation("S");
                break;
            case Air:
                this.attackValue = 15 * (getGen().ordinal() + 1);
                this.range = 2;
                this.setRepresentation("A");
                break;
            default:
                throw new InvalidTowerException();
        }
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        CloseRange,
        LongRange,
        Special,
        Air;

        public int getCost() throws InvalidTowerException {
            switch(this) {
                case CloseRange:
                case LongRange:
                    return 100;
                case Special:
                    return 250;
                case Air:
                    return 75;
                default:
                    throw new InvalidTowerException();
            }
        }

        public int getRange() throws InvalidTowerException {
            switch (this) {
                case CloseRange:
                    return 1;
                case LongRange:
                case Special:
                    return 3;
                case Air:
                    return 2;
                default:
                    throw new InvalidTowerException();
            }
        }



        public String getRepresentation() throws InvalidTowerException {
            switch (this) {
                case CloseRange:
                    return "C";
                case LongRange:
                    return "L";
                case Special:
                    return "S";
                case Air:
                    return "A";
                default:
                    throw new InvalidTowerException();
            }
        }
    }

    public static class InvalidTowerException extends Exception {
        public InvalidTowerException(){
            super("Invalid Tower");
        }
    }
}

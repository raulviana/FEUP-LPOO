import com.googlecode.lanterna.TerminalPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum WallShapes {
    I,
    Z,
    T,
    O,
    L;

    public List<Wall> getWalls(TerminalPosition pos) throws InvalidWallShapeException {
        List<Wall> walls = new ArrayList<>();
        int x = pos.getColumn(), y = pos.getRow();
        int randInt = new Random().nextInt(2);
        switch(this) {
            case I:
                if(randInt == 0) {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x+1, y));
                    walls.add(new Wall(x+2, y));

                    walls.add(new Wall(x+1, y+1));

                    walls.add(new Wall(x, y+2));
                    walls.add(new Wall(x+1, y+2));
                    walls.add(new Wall(x+2, y+2));
                } else {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x, y+1));
                    walls.add(new Wall(x, y+2));

                    walls.add(new Wall(x+1, y+1));

                    walls.add(new Wall(x+2, y));
                    walls.add(new Wall(x+2, y+1));
                    walls.add(new Wall(x+2, y+2));
                }
                break;
            case Z:
                if(randInt == 0) {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x+1, y));
                    walls.add(new Wall(x+2, y));

                    walls.add(new Wall(x+1, y+1));
                    walls.add(new Wall(x+2, y+1));
                    walls.add(new Wall(x+3, y+1));
                } else {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x, y+1));
                    walls.add(new Wall(x, y+2));

                    walls.add(new Wall(x+1, y+1));
                    walls.add(new Wall(x+1, y+2));
                    walls.add(new Wall(x+1, y+3));
                }
                break;
            case T:
                if(randInt == 0) {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x+1, y));
                    walls.add(new Wall(x+2, y));

                    walls.add(new Wall(x+1, y+1));
                    walls.add(new Wall(x+1, y+2));
                } else {
                    walls.add(new Wall(x, y+2));
                    walls.add(new Wall(x+1, y+2));
                    walls.add(new Wall(x+2, y+2));

                    walls.add(new Wall(x+1, y));
                    walls.add(new Wall(x+1, y+1));
                }
                break;
            case O:
                if(randInt == 0) {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x+1, y));
                    walls.add(new Wall(x+2, y));

                    walls.add(new Wall(x, y+1));
                    walls.add(new Wall(x+2, y+1));

                    walls.add(new Wall(x, y+2));
                    walls.add(new Wall(x+1, y+2));
                    walls.add(new Wall(x+2, y+2));
                } else {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x+1, y));
                    walls.add(new Wall(x, y+1));
                    walls.add(new Wall(x+1, y+1));
                }
                break;
            case L:
                if(randInt == 0) {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x, y+1));
                    walls.add(new Wall(x, y+2));
                    walls.add(new Wall(x+1, y+2));
                    walls.add(new Wall(x+2, y+2));
                } else {
                    walls.add(new Wall(x, y));
                    walls.add(new Wall(x+1, y));
                    walls.add(new Wall(x+2, y));
                    walls.add(new Wall(x+2, y+1));
                    walls.add(new Wall(x+2, y+2));
                }
                break;
            default:
                throw new InvalidWallShapeException();
        }
        return walls;
    }

    private class InvalidWallShapeException extends Exception {
        public InvalidWallShapeException(){
            super("Invalid Wall Shape");
        }
    }
}

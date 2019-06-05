import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Base extends Defense {
    private double health;

    public Base(TerminalPosition basePos, Settings.Difficulty dif, Generation gen) {
        super(basePos);
        this.setGen(gen);
        this.setRepresentation("B");
        switch(dif) {
            case EASY:
                this.health = 1000;
                break;
            case MEDIUM:
                this.health = 500;
                break;
            case HARD:
                this.health = 250;
                break;
        }
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.DEFENSE(getGen())));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.getPosition().getColumn(), this.getPosition().getRow()), this.getRepresentation());
        graphics.disableModifiers(SGR.BOLD);
    }

    public double getHealth() {
        return health;
    }

    public void hit(double healthTaken) {
        health = health - healthTaken;
    }
}

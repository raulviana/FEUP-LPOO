import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Defense extends Element {
    private Generation gen;
    private String representation;

    public Defense(TerminalPosition position) {
        super(position);
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.DEFENSE(gen)));
        graphics.putString(new TerminalPosition(this.getPosition().getColumn(), this.getPosition().getRow()), representation);
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public Generation getGen() {
        return gen;
    }

    public void setGen(Generation gen) {
        this.gen = gen;
    }
}

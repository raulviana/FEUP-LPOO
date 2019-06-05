import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private TerminalPosition position;

    public Element(int x, int y) {
        position = new TerminalPosition(x, y);
    }

    public Element(TerminalPosition pos) {
        position = pos;
    }

    public abstract void draw(TextGraphics graphics) throws Exception;

    public TerminalPosition getPosition() {
        return position;
    }

    public void setPosition(TerminalPosition position) {
        this.position = position;
    }
}

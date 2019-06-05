import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Placer extends Element {

    public Placer(TerminalPosition position) {
        super(position);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.PLACER));
        graphics.fillRectangle(this.getPosition(), new TerminalSize(1, 1), ' ');
    }
}

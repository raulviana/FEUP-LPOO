import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Hero extends Element {

    public Hero(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffff99"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}

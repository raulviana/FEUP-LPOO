
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coins extends Element{

    public Coins(int x, int y){
        this.position = new Position(x, y);
    }


    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#e2e185"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "o");
    }

}



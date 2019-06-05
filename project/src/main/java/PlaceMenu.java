import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PlaceMenu extends Element {
    private int selectedOpt;
    private Generation gen;
    private int balance;
    private boolean canHaveTowers;

    public PlaceMenu(TerminalPosition position, int selectedOpt, Generation gen, int balance, boolean canHaveTowers) {
        super(position);
        this.selectedOpt = selectedOpt;
        this.gen = gen;
        this.balance = balance;
        this.canHaveTowers = canHaveTowers;
    }

    public void draw(TextGraphics graphics) throws Exception {
        TerminalPosition actualPos = new TerminalPosition(this.getPosition().getColumn()+1, this.getPosition().getRow()+1);
        TerminalPosition tmpPos = actualPos;
        int tmp = selectedOpt;

        graphics.enableModifiers(SGR.BOLD);
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.BACK_BG));
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));

        if(canHaveTowers) {
            graphics.fillRectangle(actualPos, new TerminalSize(30, Tower.Type.values().length + Trap.Type.values().length + 2), ' ');

            graphics.putString(tmpPos, " Turrets");
            graphics.disableModifiers(SGR.BOLD);
            tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);

            for (Tower.Type type : Tower.Type.values()) {
                if (tmp == 1) {
                    if (hasMoney(type.getCost()))
                        graphics.setForegroundColor(TextColor.Factory.fromString(Color.GREENTEXT));
                    else graphics.setForegroundColor(TextColor.Factory.fromString(Color.REDTEXT));
                    graphics.enableModifiers(SGR.BLINK);
                    graphics.putString(tmpPos, "  " + type.getCost() + "$ - (" + type.getRepresentation() + ") " + gen.getActualTurretName(type));
                    tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);
                    graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));
                    graphics.disableModifiers(SGR.BLINK);
                } else {
                    graphics.putString(tmpPos, "  " + type.getCost() + "$ - (" + type.getRepresentation() + ") " + gen.getActualTurretName(type));
                    tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);
                }
                tmp--;
            }

            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(tmpPos, " Traps");
            graphics.disableModifiers(SGR.BOLD);
            tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);

            for (Trap.Type type : Trap.Type.values()) {
                if (tmp == 1) {
                    if (hasMoney(type.getCost()))
                        graphics.setForegroundColor(TextColor.Factory.fromString(Color.GREENTEXT));
                    else graphics.setForegroundColor(TextColor.Factory.fromString(Color.REDTEXT));
                    graphics.enableModifiers(SGR.BLINK);
                    graphics.putString(tmpPos, "  " + type.getCost() + "$ - (" + type.getRepresentation() + ") " + gen.getActualTrapName(type));
                    tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);
                    graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));
                    graphics.disableModifiers(SGR.BLINK);
                } else {
                    graphics.putString(tmpPos, "  " + type.getCost() + "$ - (" + type.getRepresentation() + ") " + gen.getActualTrapName(type));
                    tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);
                }
                tmp--;
            }
        } else {
            graphics.fillRectangle(actualPos, new TerminalSize(30, Trap.Type.values().length + 1), ' ');

            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(tmpPos, " Traps");
            graphics.disableModifiers(SGR.BOLD);
            tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);

            for (Trap.Type type : Trap.Type.values()) {
                if (tmp == 1) {
                    if (hasMoney(type.getCost()))
                        graphics.setForegroundColor(TextColor.Factory.fromString(Color.GREENTEXT));
                    else graphics.setForegroundColor(TextColor.Factory.fromString(Color.REDTEXT));
                    graphics.enableModifiers(SGR.BLINK);
                    graphics.putString(tmpPos, "  " + type.getCost() + "$ - (" + type.getRepresentation() + ") " + gen.getActualTrapName(type));
                    tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);
                    graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));
                    graphics.disableModifiers(SGR.BLINK);
                } else {
                    graphics.putString(tmpPos, "  " + type.getCost() + "$ - (" + type.getRepresentation() + ") " + gen.getActualTrapName(type));
                    tmpPos = new TerminalPosition(tmpPos.getColumn(), tmpPos.getRow() + 1);
                }
                tmp--;
            }
        }
    }

    private boolean hasMoney(int cost) {
        return balance >= cost;
    }

    public int getSelectedOpt() {
        return selectedOpt;
    }

    public void setSelectedOpt(int opt) {
        selectedOpt = opt;
    }

    public boolean getCanHaveTowers() {
        return canHaveTowers;
    }
}

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.*;
import java.awt.*;

public class UI {
    private int ready = 0;
    private String name;
    private Settings.Difficulty difficulty;
    private Arena arena;

    private JFrame frame;
    private JTextField nameField;
    private JComboBox difficultyField;
    private JButton startGameButton;

    public UI() {
        setupGUI();

        startGameButton.addActionListener(actionEvent -> {
            name = nameField.getText();
            switch(difficultyField.getSelectedIndex()) {
                case 0:
                    difficulty = Settings.Difficulty.EASY;
                    break;
                case 1:
                    difficulty = Settings.Difficulty.MEDIUM;
                    break;
                case 2:
                    difficulty = Settings.Difficulty.HARD;
                    break;
            }
            ready = 1;
        });
    }

    public void draw(TextGraphics graphics) throws Exception {
        drawPlayerUI(graphics);
        drawEnemyUI(graphics);
    }

    public void drawPlayerUI(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.UI_BG));
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));

        graphics.fillRectangle(new TerminalPosition(Settings.P_UI_POS_WIDTH, Settings.P_UI_POS_HEIGHT), new TerminalSize(Settings.P_UI_WIDTH, Settings.P_UI_HEIGHT), ' ');

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT*8, Settings.P_UI_POS_HEIGHT + 1), "Eternal Defense");

        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 3), "Name: ");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + 7, Settings.P_UI_POS_HEIGHT + 3), name);

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 5), "Base health: ");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + 14, Settings.P_UI_POS_HEIGHT + 5), arena.getBase().getHealth() + "");

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 7), "Balance: ");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + 10, Settings.P_UI_POS_HEIGHT + 7), arena.getPlayer().getBalance() + "$");

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 9), "Generation: ");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + 13, Settings.P_UI_POS_HEIGHT + 9), arena.getGeneration().toString());

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 11), "Round: ");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + 8, Settings.P_UI_POS_HEIGHT + 11), arena.getSettings().getRound().ordinal() + "");

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 13), "Difficulty: ");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + 13, Settings.P_UI_POS_HEIGHT + 13), difficulty.toString());

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 15), "Game status: ");
        graphics.disableModifiers(SGR.BOLD);
        if (arena.getSettings().isGameOn()) {
            graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + 14, Settings.P_UI_POS_HEIGHT + 15), "Round ongoing!");
        } else {
            graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + 14, Settings.P_UI_POS_HEIGHT + 15), "Building time");
            graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 17), "Press [BACKSPACE] to start");
            graphics.putString(new TerminalPosition(Settings.P_UI_POS_WIDTH + Settings.INDENT, Settings.P_UI_POS_HEIGHT + 18), "the next round!");
        }
    }

    public void drawEnemyUI(TextGraphics graphics) throws Exception {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.UI_BG));
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));

        graphics.fillRectangle(new TerminalPosition(Settings.E_UI_POS_WIDTH, Settings.E_UI_POS_HEIGHT), new TerminalSize(Settings.E_UI_WIDTH, Settings.E_UI_HEIGHT), ' ');

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition((Settings.E_UI_WIDTH-5)/2, Settings.E_UI_POS_HEIGHT + 1), "Game Guide");

        graphics.setForegroundColor(TextColor.Factory.fromString(Color.ENEMY(arena.getGeneration())));
        graphics.putString(new TerminalPosition(Settings.INDENT + 8, Settings.E_UI_POS_HEIGHT + 3), "Enemies");
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.DEFENSE(arena.getGeneration())));
        graphics.putString(new TerminalPosition((Settings.E_UI_WIDTH-7)/2 +2, Settings.E_UI_POS_HEIGHT + 3), "Towers");
        graphics.putString(new TerminalPosition(Settings.E_UI_WIDTH - 14, Settings.E_UI_POS_HEIGHT + 3), "Traps");
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));

        for (Enemy.Type type : Enemy.Type.values()) {
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(Settings.INDENT + 2, Settings.E_UI_POS_HEIGHT + type.ordinal() + 5), highlightFirstChar(type.name()) + ":");
            graphics.disableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(Settings.INDENT + type.name().length() + 6, Settings.E_UI_POS_HEIGHT + type.ordinal() + 5), "Range of " + type.getRange() + " blocks");
        }

        for (Tower.Type type : Tower.Type.values()) {
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition((Settings.E_UI_WIDTH-7)/2 - 6, Settings.E_UI_POS_HEIGHT + type.ordinal() + 5), highlightFirstChar(type.name()) + ":");
            graphics.disableModifiers(SGR.BOLD);
            if(type.equals(Tower.Type.Air)) {
                graphics.putString(new TerminalPosition((Settings.E_UI_WIDTH-7)/2 + type.name().length() - 2, Settings.E_UI_POS_HEIGHT + type.ordinal() + 5), "Range of " + type.getRange() + " blocks");
                graphics.putString(new TerminalPosition((Settings.E_UI_WIDTH-7)/2 - 6, Settings.E_UI_POS_HEIGHT + type.ordinal() + 6), "*Only tower that attacks aerial enemies!");
            } else graphics.putString(new TerminalPosition((Settings.E_UI_WIDTH-7)/2 + type.name().length() - 2, Settings.E_UI_POS_HEIGHT + type.ordinal() + 5), "Range of " + type.getRange() + " blocks");
        }

        for (Trap.Type type : Trap.Type.values()) {
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(Settings.E_UI_WIDTH - 20, Settings.E_UI_POS_HEIGHT + type.ordinal() + 5), highlightFirstChar(type.name()) + ":");
            graphics.disableModifiers(SGR.BOLD);
            if (type.equals(Trap.Type.normal)) graphics.putString(new TerminalPosition(Settings.E_UI_WIDTH - 16 + type.name().length(), Settings.E_UI_POS_HEIGHT + type.ordinal() + 5), "One hit");
            else graphics.putString(new TerminalPosition(Settings.E_UI_WIDTH - 16 + type.name().length(), Settings.E_UI_POS_HEIGHT + type.ordinal() + 5), "Multi-hit");
        }
    }

    private String highlightFirstChar(String str) {
        return "(" + str.charAt(0) + ")" + str.substring(1);
    }

    public void awaitStart() throws InterruptedException {
        while(ready == 0) {
            frame.validate();
            Thread.sleep(1000);
        }
        frame.setVisible(false);
        return;
    }

    private void setupGUI() {
        frame = new JFrame("Eternal Defense");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 25, 0, 25), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(10, 0, 10, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Eternal Defense");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 20, 0), -1, -1));
        panel1.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        startGameButton = new JButton();
        startGameButton.setText("Start game!");
        panel3.add(startGameButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(10, 0, 10, 0), -1, -1));
        panel1.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nameField = new JTextField();
        nameField.setText("Sir Spiff");
        panel4.add(nameField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        difficultyField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Easy");
        defaultComboBoxModel1.addElement("Medium");
        defaultComboBoxModel1.addElement("Hard");
        difficultyField.setModel(defaultComboBoxModel1);
        panel4.add(difficultyField, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("What's your name?");
        panel4.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Choose difficulty:");
        panel4.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        frame.getContentPane().add(panel2);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
    }

    public String getName() {
        return name;
    }

    public Settings.Difficulty getDifficulty() {
        return difficulty;
    }

    public void assignArena(Arena arena) {
        this.arena = arena;
    }
}

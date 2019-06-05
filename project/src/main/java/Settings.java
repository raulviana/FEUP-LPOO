public class Settings {

    // ** DEBUGGING SETTINGS ** \\

    static final int GAME_SPEED = 200; //Time between each game action, in milliseconds
    static final Generation STARTING_GENERATION = Generation.PREHISTORY; //Generation the game starts in
    static final Round STARTING_ROUND = Round.PRE_GAME; //Round the game starts in (NOTE: To test last round, select only ROUND11, NOT Round12!

    // ** DEBUGGING SETTINGS ** \\


    static final int INDENT = 1;

    static final int WIDTH = 110; //Width of the screen
    static final int HEIGHT = 45; //Height of the screen
    static final int ARENA_WIDTH = 55; //Width of the arena
    static final int ARENA_HEIGHT = 30; //Height of the arena

    static final int P_UI_WIDTH = 30; //Width of the Players UI
    static final int P_UI_HEIGHT = 20; //Height of the Players UI
    static final int P_UI_POS_WIDTH = ARENA_WIDTH + 8; //Left margin of the Players UI
    static final int P_UI_POS_HEIGHT = (ARENA_HEIGHT - P_UI_HEIGHT) / 2; //Top margin of the Players UI

    static final int E_UI_WIDTH = ARENA_WIDTH + P_UI_WIDTH + 7; //Width of the Players UI
    static final int E_UI_HEIGHT = 11; //Height of the Players UI
    static final int E_UI_POS_WIDTH = 1; //Left margin of the Players UI
    static final int E_UI_POS_HEIGHT = ARENA_HEIGHT + 2; //Top margin of the Players UI

    private boolean buildMode;
    private boolean gameMode;
    private boolean placeDefenseMode;
    private Difficulty difficulty;
    private Round round;
    private boolean areSpawnsFinished;

    private GameStatus gameStatus;

    final int numTowerTypes = Tower.Type.values().length;
    final int numTrapTypes = Trap.Type.values().length;

    Settings(Difficulty difficulty) {
        buildMode = true;
        gameMode = false;
        placeDefenseMode = false;
        this.difficulty = difficulty;
        this.round = STARTING_ROUND;
        this.areSpawnsFinished = false;

        this.gameStatus = GameStatus.ONGOING;
    }

    boolean isGameOn() {
        return gameMode;
    }

    void toggleGameStatus() {
        buildMode = !buildMode;
        gameMode = !gameMode;
    }

    boolean isPlaceDefenseModeOn() {
        return placeDefenseMode;
    }

    void setPlaceDefenseMode(boolean placeDefenseMode) {
        this.placeDefenseMode = placeDefenseMode;
    }

    Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean getAreSpawnsFinished() {
        return areSpawnsFinished;
    }

    public void setAreSpawnsFinished(boolean areSpawnsFinished) {
        this.areSpawnsFinished = areSpawnsFinished;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD;

        public int getInitBal() throws InvalidDifficultyException {
            switch (this) {
                case EASY:
                    return 300;
                case MEDIUM:
                    return 200;
                case HARD:
                    return 100;
                default:
                    throw new InvalidDifficultyException();
            }
        }

        public static class InvalidDifficultyException extends Exception {
            InvalidDifficultyException(){
                super("Invalid Difficulty");
            }
        }
    }
}

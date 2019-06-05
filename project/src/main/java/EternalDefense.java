public class EternalDefense {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        try {
            game.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

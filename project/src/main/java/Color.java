final class Color {

    static final String BACK_BG = "#222222";
    static final String BG = "#444444";
    static final String UI_BG = "#333333";
    static final String PLACER = "#24252a";
    static final String TEXT = "#fafafa";
    static final String GREENTEXT = "#3fc380";
    static final String REDTEXT = "#d91e18";
    static final String WALL = "#bdc3c7";
    static String DEFENSE(Generation gen) {
        switch(gen) {
            case PREHISTORY:
                return "#2ecc71";
            case MEDIEVAL:
                return "#fcd670";
            case MODERN:
                return "#2574a9";
            case FUTURISTIC:
                return "#29f1c3";

        }
        return "#000000";
    }
    static String ENEMY(Generation gen) {
        switch(gen) {
            case PREHISTORY:
                return "#f4d03f";
            case MEDIEVAL:
                return "#d64541";
            case MODERN:
                return "#f9690e";
            case FUTURISTIC:
                return "#a537fd";

        }
        return "#000000";
    }
}

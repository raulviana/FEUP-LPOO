
import java.util.ArrayList;
import java.util.List;

public enum Round {
    PRE_GAME,
    ROUND1,
    ROUND2,
    ROUND3,
    ROUND4,
    ROUND5,
    ROUND6,
    ROUND7,
    ROUND8,
    ROUND9,
    ROUND10,
    ROUND11,
    ROUND12;

    public List<Enemy> getEnemies(Arena arena) throws Exception {
        List<Enemy> enemyList = new ArrayList<>();
        switch (this) {
            case ROUND1:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.PREHISTORY));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.PREHISTORY));
                break;
            case ROUND2:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.PREHISTORY));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Ranged, Generation.PREHISTORY));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Cavalry, Generation.PREHISTORY));
                break;
            case ROUND3:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Ranged, Generation.PREHISTORY));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Aerial, Generation.PREHISTORY));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Aerial, Generation.PREHISTORY));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Heavy, Generation.PREHISTORY));
                break;
            case ROUND4:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.MEDIEVAL));
                break;
            case ROUND5:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Ranged, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Cavalry, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Ranged, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Cavalry, Generation.MEDIEVAL));
                break;
            case ROUND6:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Ranged, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Aerial, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Aerial, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Heavy, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Ranged, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Aerial, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Aerial, Generation.MEDIEVAL));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Heavy, Generation.MEDIEVAL));
                break;
            case ROUND7:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                break;
            case ROUND8:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Ranged, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Cavalry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Ranged, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Cavalry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Infantry, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Ranged, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Cavalry, Generation.MODERN));
                break;
            case ROUND9:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Ranged, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Aerial, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Aerial, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Heavy, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Ranged, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Aerial, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Aerial, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Heavy, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Ranged, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Aerial, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Aerial, Generation.MODERN));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Heavy, Generation.MODERN));
                break;
            case ROUND10:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                break;
            case ROUND11:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Ranged, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Cavalry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Ranged, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Cavalry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Ranged, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Cavalry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Infantry, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Ranged, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Cavalry, Generation.FUTURISTIC));
                break;
            case ROUND12:
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Ranged, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Aerial, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Aerial, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.NORTH_ENTRANCE(), Enemy.Type.Heavy, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Ranged, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Aerial, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Aerial, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.WEST_ENTRANCE(), Enemy.Type.Heavy, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Ranged, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Aerial, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Aerial, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.EAST_ENTRANCE(), Enemy.Type.Heavy, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Ranged, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Aerial, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Aerial, Generation.FUTURISTIC));
                enemyList.add(new Enemy(arena, Arena.SOUTH_ENTRANCE(), Enemy.Type.Heavy, Generation.FUTURISTIC));
                break;
            default:
                throw new InvalidRoundException();
        }
        return enemyList;
    }

    public Round next() throws InvalidRoundException {
        if (this.equals(Round.ROUND12)) throw new InvalidRoundException("Invalid round achieved, invalid debugging settings might be the culprit!");
        return Round.values()[(this.ordinal()+1) % Round.values().length];
    }

    public static class InvalidRoundException extends Exception {
        InvalidRoundException(){
            super("Invalid Round");
        }
        InvalidRoundException(String s) {
            super(s);
        }
    }
}

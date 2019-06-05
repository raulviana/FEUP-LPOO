public enum Generation {
    PREHISTORY,
    MEDIEVAL,
    MODERN,
    FUTURISTIC;

    @Override
    public String toString() {
        switch(this) {
            case PREHISTORY: return "Pre-History";
            case MEDIEVAL: return "Medieval";
            case MODERN: return "Modern";
            case FUTURISTIC: return "Futuristic";
            default: throw new InvalidGenerationException();
        }
    }

    public String getActualTurretName(Tower.Type type) throws Exception {
        String actualName;
        switch(type) {
            case CloseRange:
                switch (this) {
                    case PREHISTORY:
                        actualName = "Spearmen Tower";
                        break;
                    case MEDIEVAL:
                        actualName = "Swordsman Tower";
                        break;
                    case MODERN:
                        actualName = "Riflemen Tower";
                        break;
                    case FUTURISTIC:
                        actualName = "Robots Tower";
                        break;
                    default:
                        throw new InvalidGenerationException();
                }
                break;
            case LongRange:
                switch (this) {
                    case PREHISTORY:
                        actualName = "Archers Tower";
                        break;
                    case MEDIEVAL:
                        actualName = "Musketeers Tower";
                        break;
                    case MODERN:
                        actualName = "Snipers Tower";
                        break;
                    case FUTURISTIC:
                        actualName = "Laser Tower";
                        break;
                    default:
                        throw new InvalidGenerationException();
                }
                break;
            case Special:
                switch (this) {
                    case PREHISTORY:
                        actualName = "Fire Tower";
                        break;
                    case MEDIEVAL:
                        actualName = "Catapult Tower";
                        break;
                    case MODERN:
                        actualName = "Tank Tower";
                        break;
                    case FUTURISTIC:
                        actualName = "Antimatter Tower";
                        break;
                    default:
                        throw new InvalidGenerationException();
                }
                break;
            case Air:
                switch (this) {
                    case PREHISTORY:
                        actualName = "Slingers Tower";
                        break;
                    case MEDIEVAL:
                        actualName = "Arbalist Tower";
                        break;
                    case MODERN:
                        actualName = "Flak Tower";
                        break;
                    case FUTURISTIC:
                        actualName = "Plasma Tower";
                        break;
                    default:
                        throw new InvalidGenerationException();
                }
                break;
            default:
                throw new Tower.InvalidTowerException();
        }
        return actualName;
    }

    public String getActualTrapName(Trap.Type type) throws Exception {
        String actualName;
        switch(type) {
            case normal:
                switch (this) {
                    case PREHISTORY:
                        actualName = "Hole Trap";
                        break;
                    case MEDIEVAL:
                        actualName = "Spikes Trap";
                        break;
                    case MODERN:
                        actualName = "Gas Trap";
                        break;
                    case FUTURISTIC:
                        actualName = "Rays Trap";
                        break;
                    default:
                        throw new InvalidGenerationException();
                }
                break;
            case special:
                switch (this) {
                    case PREHISTORY:
                        actualName = "FirePit Trap";
                        break;
                    case MEDIEVAL:
                        actualName = "Boil Trap";
                        break;
                    case MODERN:
                        actualName = "Mine Trap";
                        break;
                    case FUTURISTIC:
                        actualName = "Void Trap";
                        break;
                    default:
                        throw new InvalidGenerationException();
                }
                break;
            default:
                throw new Trap.InvalidTrapException();
        }
        return actualName;
    }

    private class InvalidGenerationException extends RuntimeException {
        public InvalidGenerationException(){
            super("Invalid Generation");
        }
    }
}

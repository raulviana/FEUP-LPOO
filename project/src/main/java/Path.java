import com.googlecode.lanterna.TerminalPosition;

import java.util.HashMap;
import java.util.List;

public class Path {
    private HashMap<Integer, Movement> path = new HashMap<>();

    public Path(TerminalPosition startPos, TerminalPosition goalPos, List<Element> blocked) throws InvalidNodesException {
        Node start = new Node(startPos.getRow(), startPos.getColumn());
        Node goal = new Node(goalPos.getRow(), goalPos.getColumn());
        Pathfinder tmp = new Pathfinder(start, goal);
        tmp.setBlocked(blocked);
        List<Node> nodeList = tmp.findPath();
        for(int i = 1; i < nodeList.size()-1; i++) // -1 to remove the last movement, the enemy goes next to the base, not into it.
            path.put(i, getMovement(nodeList.get(i-1), nodeList.get(i)));
    }

    private Movement getMovement(Node nodeOne, Node nodeTwo) throws InvalidNodesException {
        if(nodeOne.equals(nodeTwo)) throw new InvalidNodesException("Nodes can't be the same.");
        if(nodeOne.getRow() != nodeTwo.getRow() && nodeOne.getCol() != nodeTwo.getCol()) throw new InvalidNodesException("Nodes need to be next to each other horizontally or vertically.");
        if(nodeOne.getCol() == nodeTwo.getCol()) {
            if(nodeOne.getRow() > nodeTwo.getRow()) return Movement.UP;
            else return Movement.DOWN;
        } else {
            if(nodeOne.getCol() > nodeTwo.getCol()) return Movement.LEFT;
            else return Movement.RIGHT;
        }
    }

    public class InvalidNodesException extends Exception {
        public InvalidNodesException(String err){
            super(err);
        }
    }

    public HashMap<Integer, Movement> getPath() {
        return path;
    }
}

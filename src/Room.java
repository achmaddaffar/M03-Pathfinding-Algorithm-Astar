public class Room {
    final Pair position;
    Pair source, goal;
    boolean[] isWay = new boolean[4];
    double gCost, hCost, fCost = Double.MAX_VALUE;
    Room parent;

    public Room(String alias, int x, int y, Pair source, Pair goal) {
        this.isWay[0] = alias.charAt(0) == '0';
        this.isWay[1] = alias.charAt(1) == '0';
        this.isWay[2] = alias.charAt(2) == '0';
        this.isWay[3] = alias.charAt(3) == '0';
        this.position = new Pair(x, y);
        this.source = new Pair(source.x, source.y);
        this.goal = new Pair(goal.x, goal.y);
    }

    public void calculateCost() {
        this.gCost = Math.sqrt( (Math.pow((position.x - source.x), 2) + Math.pow((position.y - source.y), 2)) );
        this.hCost = Math.sqrt( (Math.pow((position.x - goal.x), 2) + Math.pow((position.y - goal.y), 2)) );
        this.fCost = gCost + hCost;
    }
}

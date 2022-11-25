import java.util.*;

public class Astar {
    List<Room> openRoom = new LinkedList<>();
    Queue<Room> visitedRoom = new LinkedList<>();
    Room[][] room;
    Pair source, finish;
    int xSize, ySize, step = 0;
    boolean[][] checkMap;
    boolean[][] path;

    public Astar(Room[][] room, Pair source, Pair finish) {
        this.room = room;
        this.source = source;
        this.finish = finish;
        this.xSize = room[0].length;
        this.ySize = room.length;
        this.checkMap = new boolean[ySize][xSize];
        this.path = new boolean[ySize][xSize];
    }

    private final Comparator<Room> comparator = (o1, o2) -> {
        if (o1.fCost > o2.fCost) return 1;
        else if (o1.fCost < o2.fCost) return -1;
        return 0;
    };

    public void printMaze() {
        for (int i = 1; i < ySize - 1; i++) {
            for (int j = 1; j < xSize - 1; j++) {
                System.out.printf(Arrays.toString(room[i][j].isWay));
            }
            System.out.println("");
        }
    }

    public void printCheckedRoom() {
        for (int i = 1; i < ySize - 1; i++) {
            for (int j = 1; j < xSize - 1; j++) {
                System.out.print("[" + (checkMap[i][j]? "1" : " ") + "]");
            }
            System.out.println();
        }
    }

    public void printPath() {
        for (int i = 1; i < ySize - 1; i++) {
            for (int j = 1; j < xSize - 1; j++) {
                System.out.print("[" + (path[i][j]? "1" : " ") + "]");
            }
            System.out.println();
        }
    }

    public int getStepCount() {
        return step;
    }
    public int aStar() {
        openRoom.add(room[source.y][source.x]);
        checkMap[source.y][source.x] = true;

        while (!openRoom.isEmpty()) {
            Room currentRoom = openRoom.get(0);
            visitedRoom.add(currentRoom);
            openRoom.remove(0);

            if (currentRoom.position.x == finish.x && currentRoom.position.y == finish.y) {
                path[source.y][source.x] = true;
                while (currentRoom.parent != null) {
                    path[currentRoom.position.y][currentRoom.position.x] = true;
                    currentRoom = currentRoom.parent;
                    step++;
                }
                return step;
            }

            for (int i = 0; i < 4; i++) {
                int nextX, nextY;

                switch (i) {
                    case 0 -> {
                        nextX = currentRoom.position.x;
                        nextY = currentRoom.position.y - 1;
                    }
                    case 1 -> {
                        nextX = currentRoom.position.x + 1;
                        nextY = currentRoom.position.y;
                    }
                    case 2 -> {
                        nextX = currentRoom.position.x;
                        nextY = currentRoom.position.y + 1;
                    }
                    case 3 -> {
                        nextX = currentRoom.position.x - 1;
                        nextY = currentRoom.position.y;
                    }
                    default -> {
                        nextX = currentRoom.position.x;
                        nextY = currentRoom.position.y;
                    }
                }
                if (room[nextY][nextX] == null || !currentRoom.isWay[i] || visitedRoom.contains(room[nextY][nextX]))
                    continue;

                if (!openRoom.contains(room[nextY][nextX])) {
                    room[nextY][nextX].calculateCost();
                    room[nextY][nextX].parent = currentRoom;
                    openRoom.add(room[nextY][nextX]);
                    checkMap[nextY][nextX] = true;
                }
            }

            openRoom.sort(comparator);
        }

        return -1;
    }
}
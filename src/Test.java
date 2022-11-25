import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("%s :\n", "Masukkan ukuran labirin");
        System.out.printf("%-5s: ", "x");
        int x = sc.nextInt();
        System.out.printf("%-5s: ", "y");
        int y = sc.nextInt();
        Room[][] room = new Room[y + 2][x + 2];

        System.out.printf("%s :\n", "Masukkan posisi awal");
        System.out.printf("%-5s: ", "x");
        int xSource = sc.nextInt();
        System.out.printf("%-5s: ", "y");
        int ySource = sc.nextInt();
        Pair source = new Pair(++xSource, ++ySource);

        System.out.printf("%s :\n", "Masukkan posisi tujuan");
        System.out.printf("%-5s: ", "x");
        int xFinish = sc.nextInt();
        System.out.printf("%-5s: ", "y");
        int yFinish = sc.nextInt();
        Pair finish = new Pair(++xFinish, ++yFinish);

        // Collecting Maze Data
        System.out.println("Masukkan bentuk puzzle (NESW) :");
        for (int i = 1; i < y + 1; i++) {
            String[] s = sc.next().split("-");
            for (int j = 0; j < s.length; j++) {
                room[i][j + 1] = new Room(s[j], j + 1, i, source, finish);
            }
        }
        sc.close();
        System.out.println();

        Astar astar = new Astar(room, source, finish);
        astar.printMaze();
        System.out.println();
        int stepCount = astar.aStar();
        System.out.println("Langkah yang ditempuh : " + stepCount);
        astar.printPath();
    }
}

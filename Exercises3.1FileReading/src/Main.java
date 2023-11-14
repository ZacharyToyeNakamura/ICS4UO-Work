import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static final String FILENAME = "src/grid.txt";
    public static final int[][] MOVES = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    public static final int MN = 20;
    public static int[][] grid = new int[MN][MN];

    public static void main(String[] args) throws FileNotFoundException {
//        int grid[][] = new int[MN][MN];
        Scanner reader = new Scanner(new File(FILENAME));
        for(int i = 0; i < MN; i++) {
            for (int j = 0; j < MN; j++) {
                if (reader.hasNextInt()) {
                    grid[i][j] = reader.nextInt();
                } else {
                    System.out.println("No int");
                }
            }
        }
        int highest = 0;


    }

    public int dijkstra(int x, int y) {
        PriorityQueue<pair> pq = new PriorityQueue<pair>(new pqComp());
        int[][] high = new int[MN][MN]; // remove probably
        for (int i = 0; i < MN; i++) {
            for (int j = 0; j < MN; j++) {
                high[i][j] = 0;
            }
        }

        ArrayList<position> temp = new ArrayList<>();
        temp.add(new position(x, y));
        pq.add(new pair(x, y, 0, grid[x][y], temp));
        high[x][y] = 0;

        int highest = 0;

        while(!pq.isEmpty()) {
            pair next = pq.poll();
            int xPos = next.pos.x;
            int yPos = next.pos.y;
            ArrayList<position> vis = next.vis;
            int val = next.val;
            int dist = next.distance;
            for(int[] move: MOVES) {
                int cx = move[0], cy = move[1];
                int x2 = cx + xPos, y2 = cy + yPos;
                if(isIn(x2, y2, vis)) {
                    continue;
                }
                vis.add(new position(x2, y2));
                val *= grid[x2][y2];
                if(vis.size() >= 4) {
                    if(highest < val) {
                        highest = val;
                    }
                    continue;
                }
                pq.add(new pair(x, y, dist+1,val, vis));

            }


        }
        return highest;
    }

    class position {
        int x;
        int y;

        position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class pair {
        int distance;
        ArrayList<position> vis;

        position pos;

        int val;

        pair(int x, int y, int dist, int val) {
            pos = new position(x, y);
            distance = dist;
            this.val = val;
            vis = new ArrayList<>();
        }
        pair(int x, int y, int dist, int val, ArrayList<position> vis) {
            this.pos.x = x;
            this.pos.y = y;
            distance = dist;
            this.val = val;
            this.vis = vis;
        }
    }

    class pqComp implements Comparator<pair>{
        // lowest first
        public int compare(pair p1, pair p2) {
            if (p1.distance > p2.distance)
                return 1;
            else if (p1.distance < p2.distance)
                return -1;
            return 0;
        }
    }

    /**
     * checks if a value is in a list 2d
     * @return
     */
    public static boolean isIn(int x, int y, ArrayList<position> haystack) {
        for (position pos: haystack) {
            if(x == pos.x && y == pos.y) {
                return true;
            }
        }
        return false;
    }

}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * x is rows
 * y is columns
 */


public class Main {
    public static final String FILENAME = "/workspaces/javaG12/Exercises3.1FileReading/src/grid.txt";
    public static final long[][] MOVES = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}}; 
    public static final int MN = 20;
    public static final int PATHLEN = 4;
    public static long[][] grid = new long[MN][MN];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(FILENAME));
        for(int i = 0; i < MN; i++) {
            for (int j = 0; j < MN; j++) {
                if (reader.hasNextInt()) {
                    grid[i][j] = reader.nextInt();
                } else {
                    System.out.println("No long");
                }
            }
        }
        long highest = 0;
        ArrayList<position> bestPath = new ArrayList<>();
        ArrayList<position> tempPath = new ArrayList<>();
        for(int i = 0; i < MN; i++) {
            for(int j = 0; j < MN; j++) {
                long test = dijkstra(i, j, tempPath);
                if(test > highest) {
                    highest = test;
                    bestPath.clear();
                    for(position p: tempPath) {
                        bestPath.add(p.clone());
                    }
                }
            }
        }
        System.out.println(highest);
        System.out.println("The best path contains: ");
        for(position posi: bestPath) {
            System.out.println("("+posi.x+", "+posi.y+")");
        }
    }

    public static long dijkstra(int x, int y, ArrayList<position> bestPath) {
        PriorityQueue<pair> pq = new PriorityQueue<pair>(new pqComp()); // create a pq with a custom compare
        ArrayList<position> temp = new ArrayList<>(); // Array.asLit() . . .
        temp.add(new position(x, y));
        pq.add(new pair(x, y, 1, grid[x][y], temp)); // init pq at starting pos
        long highest = 0;

        ArrayList<position> highPath = new ArrayList<>();

        while(!pq.isEmpty()) {
            pair next = pq.poll();
            long xPos = next.pos.x;
            long yPos = next.pos.y;
            ArrayList<position> vis = next.vis;
            long val = next.val;
            long dist = next.distance;

            for(long[] move: MOVES) {
                long cx = move[0], cy = move[1];
                int x2 = (int)(cx + xPos), y2 = (int)(cy + yPos);
                if(isIn(x2, y2, vis)) {
                    continue;
                }
                // If out of bounds, don't continue;
                if(x2 < 0 || x2 >= MN || y2 < 0 || y2 >= MN) continue;
                ArrayList<position> nextVis = new ArrayList<>(vis);
                nextVis.add(new position(x2, y2));
                long nextVal = val * grid[x2][y2];
                if(nextVis.size() >= PATHLEN) {
                    if(highest < nextVal) {
                        highest = nextVal;
                        highPath = nextVis;
                        
                        // if(x == 0 && y == 0) {
                        //     System.out.println("New high:");
                        //     for (position posi : nextVis) {
                        //         System.out.println("Including (" +posi.x + ", " + posi.y + ") == " + val);
                        //     }
                        // System.out.println("Visited size " + nextVis.size() + " distance " + dist);
                        // }
                    }
                    continue;
                }
                pq.add(new pair(x2, y2, dist+1, nextVal, nextVis));
            }
        }
        bestPath.clear(); // Copy over the best path
        for (position p : highPath) {
            bestPath.add(p);
        }
        return highest;
    }

    public static class position {
        long x;
        long y;

        position(long x, long y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Returns a duplicate of the position
         */
        public position clone() {
            return new position(x, y);
        }
    }

    public static class pair {
        long distance; // This can be completely deleted
        ArrayList<position> vis; // all past positions in the chain
        position pos; // current position
        long val; // current value

        pair(long x, long y, long dist, long val) {
            pos = new position(x, y);
            distance = dist;
            this.val = val;
            vis = new ArrayList<>();
        }

        pair(long x, long y, long dist, long val, ArrayList<position> vis) {
            pos = new position(x, y);
            distance = dist;
            this.val = val;
            this.vis = vis;
        }
    }

    public static class pqComp implements Comparator<pair>{
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
    public static boolean isIn(long x, long y, ArrayList<position> haystack) {
        for (position pos: haystack) {
            if(x == pos.x && y == pos.y) {
                return true;
            }
        }
        return false;
    }

}
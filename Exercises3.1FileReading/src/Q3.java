import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// Question 3
// Should have made a class .. . .

/**
 * 0 - 10 = 0 - 10
 * J = 11, Q = 12, K = 13, A = 14
 *
 * HDSC = 0123
 * 0123
 */
public class Q3 {

    public static void main(String[] args) {
        String filename = "src/poker.txt";
        int won = 0;
        int p1Val = 0;
        int p2Val = 0;
        int[] p1ValHigh = new int[10];
        int[] p2ValHigh = new int[10];

        int[] p1Num = new int[5];
        int[] p1Suit = new int[5];
        int[] p2Num = new int[5];
        int[] p2Suit = new int[5];
        ArrayList<Integer> p1Sorted = new ArrayList<>();
        ArrayList<Integer> p2Sorted = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filename));
            while(reader.ready()) {

                for(int i = 0; i < 5; i++) {
                    int num = reader.read(); // THERE ARE NO 10s???
                    if (num == '1') { // ten
                        num = reader.read();
                        p1Num[i] = 10;
                    }
                    if('0' <= num && num <= '9') {
                        p1Num[i] = num - '0';
                    }
                    else if (num == 'J') p1Num[i] = 11;
                    else if (num == 'Q') p1Num[i] = 12;
                    else if (num == 'K') p1Num[i] = 13;
                    else if (num == 'A') p1Num[i] = 14;

                    // HDSC = 0123
                    reader.read(); // space
                    num = reader.read();
                    if(num == 'H') p1Suit[i] = 0;
                    else if(num == 'D') p1Suit[i] = 1;
                    else if(num == 'S') p1Suit[i] = 2;
                    else if(num == 'C') p1Suit[i] = 3;

                    // new line
                    if(reader.ready()) reader.read();

                }
                for (int i = 0; i < 5; i++) {
                    int num = reader.read(); // THERE ARE NO 10s???
                    if (num == '1') { // ten
                        num = reader.read();
                        p2Num[i] = 10;
                    }
                    if('0' <= num && num <= '9') {
                        p2Num[i] = num - '0';
                    }
                    else if (num == 'J') p2Num[i] = 11;
                    else if (num == 'Q') p2Num[i] = 12;
                    else if (num == 'K') p2Num[i] = 13;
                    else if (num == 'A') p2Num[i] = 14;

                    // HDSC = 0123
                    reader.read(); // space
                    num = reader.read();
                    if(num == 'H') p2Suit[i] = 0;
                    else if(num == 'D') p2Suit[i] = 1;
                    else if(num == 'S') p2Suit[i] = 2;
                    else if(num == 'C') p2Suit[i] = 3;

                    // new line
                    if(reader.ready()) reader.read();
                }
                // high card
                for (int i = 0; i < 5; i++) {
                    if(p1Num[i] > p1ValHigh[0]) p1ValHigh[0] = p1Num[i];
                }
                for (int i = 0; i < 5; i++) {
                    if(p2Num[i] > p2ValHigh[0]) p2ValHigh[0] = p2Num[i];
                }

                // inefficient but it will have to do
                ArrayList<Integer> seen = new ArrayList<>();
                int dupeNum = -1;
                int dupeCnt = 0;
                int dupeNum2 = -1;
                int dupeCnt2 = 0;
                for (int i = 0; i < 5; i++) {
                    seen.add(p1Num[i]);
                    dupeCnt2 = 0;
                    dupeCnt = 0;
                    for (int j = 0; j < seen.size()-1; j++) {
                        if(seen.get(i) == p1Num[i]) {
                            if(dupeNum == -1 || dupeNum == p1Num[i]) {
                                dupeNum = p1Num[i];
                                dupeCnt++;
                            }
                            else if (dupeNum2 == -1 || dupeNum2 == p1Num[i]) {
                                dupeNum = p1Num[i];
                                dupeCnt2++;
                            }
                        }
                    }
                }
                if(dupeCnt == 2 && dupeCnt2 < 2) {
                    p1Val = 1;
                    p1ValHigh[1] = dupeNum;
                } else if(dupeCnt == 2 && dupeCnt2 == 2) {
                    p1Val = 2;
                    p1ValHigh[2] = Math.max(dupeNum, dupeNum2); // change later?
                } else if (dupeCnt == 3 && dupeCnt2 < 2) {
                    p1Val = 3;
                    p1ValHigh[3] = dupeNum;
                } else if (dupeCnt == 3 && dupeCnt2 == 2) {
                    p1Val = 6;
                    p1ValHigh[6] = dupeNum; // change later to include the val of dupeNum 2
                } else if(dupeCnt == 2 && dupeCnt2 == 3) {
                    p1Val = 6;
                    p1ValHigh[6] = dupeNum2; // change later to include the val of dupeNum
                } else if (dupeCnt == 4) {
                    p1Val = 7;
                    p1ValHigh[7] = dupeNum;
                }

                // Straights
                p1Sorted.clear();
                for(int i = 0; i < 5; i++) p1Sorted.add(p1Num[i]);
                Collections.sort(p1Sorted);
                int curNum = p1Sorted.get(0);
                boolean wrap = false;
                if(curNum == 2) wrap = true;
                for (int i = 1; i < 5; i++) {
                    if (p1Sorted.get(i) == curNum+1 || wrap && i == 4 && p1Sorted.get(i) == 14) {
                        curNum++;
                    }
                    else {
                        break;
                    }
                    // it's a straight
                    if(i == 4) {
                        if(p1Val < 5) {
                            p1Val = 5;

                        }
                    }
                }

            }

        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }

    }
}

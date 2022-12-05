import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public int totalScore(List<String> opponent, List<String> self) {
        int sum = 0;
        int len = opponent.size();
        for (int i = 0; i < len; ++i) {
            String opp = opponent.get(i);
            String slf = self.get(i);
            sum += pointsEarned(opp, slf);
        }
        return sum;
    }
    private int pointsEarned(String opp, String slf) {
        // opponent rock
        if (opp.equals("A")) {
            // rock-rock draw 1 + 3
            if (slf.equals("X")) return 4;
            // rock-paper win 2 + 6
            if (slf.equals("Y")) return 8;
            // rock-scissors lose 3 + 0
            if (slf.equals("Z")) return 3;
        }
        // opponent paper
        if (opp.equals("B")) {
            // paper-rock lose 1 + 0
            if (slf.equals("X")) return 1;
            // paper-paper draw 2 + 3
            if (slf.equals("Y")) return 5;
            // paper-scissors win 3 + 6
            if (slf.equals("Z")) return 9;
        }
        // opponent scissors
        if (opp.equals("C")) {
            // scissors-rock win 1 + 6
            if (slf.equals("X")) return 7;
            // scissors-paper lose 2 + 0
            if (slf.equals("Y")) return 2;
            // scissors-scissors draw 3 + 3
            if (slf.equals("Z")) return 6;
        }
        // error
        return -1;
    }

    public int newScore(List<String> opponent, List<String> self) {
        int sum = 0;
        int len = opponent.size();
        for (int i = 0; i < len; ++i) {
            String opp = opponent.get(i);
            String slf = self.get(i);
            sum += newPointsEarned(opp, slf);
        }
        return sum;
    }

    private int newPointsEarned(String opp, String slf) {
        // opponent rock
        if (opp.equals("A")) {
            // rock-scissors lose 3 + 0
            if (slf.equals("X")) return 3;
            // rock-rock draw 1 + 3
            if (slf.equals("Y")) return 4;
            // rock-paper win 2 + 6
            if (slf.equals("Z")) return 8;
        }
        // opponent paper
        if (opp.equals("B")) {
            // paper-rock lose 1 + 0
            if (slf.equals("X")) return 1;
            // paper-paper draw 2 + 3
            if (slf.equals("Y")) return 5;
            // paper-scissors win 3 + 6
            if (slf.equals("Z")) return 9;
        }
        // opponent scissors
        if (opp.equals("C")) {
            // scissors-paper lose 2 + 0
            if (slf.equals("X")) return 2;
            // scissors-scissors draw 3 + 3
            if (slf.equals("Y")) return 6;
            // scissors-rock win 1 + 6
            if (slf.equals("Z")) return 7;
        }
        // error
        return -1;
    }

    public static void main(String[] args) {
        List<String> opponent = new ArrayList<>();
        List<String> self = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\65912\\OneDrive - National University of Singapore\\AdventOfCode\\2022\\txtfiles\\Day2.txt"));
            while (sc.hasNext()) {
                String opp = sc.next();
                String slf = sc.next();
                opponent.add(opp);
                self.add(slf);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Day2 test = new Day2();
        System.out.println(test.totalScore(opponent, self));
        System.out.println(test.newScore(opponent, self));
    }
}

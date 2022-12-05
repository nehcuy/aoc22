import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public int countFullyContained(List<String> input) {
        int len = input.size();
        int count = 0;
        for (int i = 0; i < len; i += 4) {
            int first = Integer.parseInt(input.get(i));
            int second = Integer.parseInt(input.get(i + 1));
            int third = Integer.parseInt(input.get(i + 2));
            int fourth = Integer.parseInt(input.get(i + 3));
            if (first >= third && second <= fourth
                || first <= third && second >= fourth) ++count;
        }
        return count;
    }

    public int countOverlaps(List<String> input) {
        int len = input.size();
        int count = 0;
        for (int i = 0; i < len; i += 4) {
            int first = Integer.parseInt(input.get(i));
            int second = Integer.parseInt(input.get(i + 1));
            int third = Integer.parseInt(input.get(i + 2));
            int fourth = Integer.parseInt(input.get(i + 3));
            if (second < third || first > fourth) continue;
            ++count;
        }
        return count;
    }

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(new File("C:\\Users\\65912\\OneDrive - National University of Singapore\\AdventOfCode\\2022\\txtfiles\\Day4.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            arr.addAll(Arrays.asList(sc.next().split("[-,]")));
        }

        Day4 test = new Day4();
        System.out.println(test.countFullyContained(arr));
        System.out.println(test.countOverlaps(arr));
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    public static final int LOWERCASE_CONSTANT = 96;
    public static final int UPPERCASE_CONSTANT = 38;
    public int getSum(List<String> input) {
        int sum = 0;
        for (String i : input) {
            int len = i.length();
            int common = extractCommonChar(i.substring(0, len / 2), i.substring(len / 2, len));
            if (common > 64 && common < 91) sum += common - UPPERCASE_CONSTANT;
            if (common > 96 && common < 123) sum += common - LOWERCASE_CONSTANT;
        }
        return sum;
    }

    private int extractCommonChar(String a, String b) {
        int len = a.length();
        int output = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; ++i) {
            set.add((int) a.charAt(i));
        }
        for (int i = 0; i < len; ++i) {
            int curr = b.charAt(i);
            if (set.contains(curr)) output = curr;
        }
        return output;
    }

    public int getNewSum(List<String> input) {
        int sum = 0;
        int arrLen = input.size();
        for (int i = 0; i < arrLen; i += 3) {
            int common = extractCommonChar(input.get(i), input.get(i + 1), input.get(i + 2));
            if (common > 64 && common < 91) sum += common - UPPERCASE_CONSTANT;
            if (common > 96 && common < 123) sum += common - LOWERCASE_CONSTANT;
        }
        return sum;
    }

    private int extractCommonChar(String a, String b, String c) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        Set<Integer> setC = new HashSet<>();
        for (int i = 0; i < a.length(); ++i) setA.add((int) a.charAt(i));
        for (int i = 0; i < b.length(); ++i) setB.add((int) b.charAt(i));
        for (int i = 0; i < c.length(); ++i) setC.add((int) c.charAt(i));
        int output = 0;
        for (int i = 1; i < 53; ++i) {
            int chr;
            if (i < 28) {
                chr = i + LOWERCASE_CONSTANT;
            } else {
                chr = i + UPPERCASE_CONSTANT;
            }
            if (!setA.contains(chr)) continue;
            if (!setB.contains(chr)) continue;
            if (!setC.contains(chr)) continue;
            output = chr;
        }
        return output;
    }

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(new File("C:\\Users\\65912\\OneDrive - National University of Singapore\\AdventOfCode\\2022\\txtfiles\\Day3.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            arr.add(sc.next());
        }

        Day3 test = new Day3();
        System.out.println(test.getSum(arr));
        System.out.println(test.getNewSum(arr));
    }
}

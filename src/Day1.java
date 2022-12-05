import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public int maxCalories(List<Integer> arr) {
        int max = 0;
        for (int i : sums(arr)) {
            max = Math.max(max, i);
        }
        return max;
    }

    public int sumOfTopThree(List<Integer> arr) {
        int first = 0, second = 0, third = 0;
        for (int i : sums(arr)) {
            if (i > first) {
                third = second;
                second = first;
                first = i;
            } else if (i > second) {
                third = second;
                second = i;
            } else if (i > third) {
                third = i;
            }
        }
        return first + second + third;
    }

    private static List<Integer> sums(List<Integer> arr) {
        List<Integer> output = new ArrayList<>();
        int sum = 0;
        for (int i : arr) {
            if (i == -1) {
                output.add(sum);
                sum = 0;
            } else {
                sum += i;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\65912\\OneDrive - National University of Singapore\\AdventOfCode\\2022\\txtfiles\\Day1.txt"));
            while(sc.hasNext()) {
                String line = sc.nextLine();
                if (line.equals("")) {
                    arr.add(-1);
                } else {
                    arr.add(Integer.parseInt(line));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Day1 test = new Day1();
        System.out.println(test.maxCalories(arr));
        System.out.println(test.sumOfTopThree(arr));
    }
}
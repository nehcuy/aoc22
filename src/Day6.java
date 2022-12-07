import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day6 {

    public int firstMarker(String input, int distinctCharacters) {
        int leftIdx = 0, rightIdx = distinctCharacters;
        Set<Character> set = new HashSet<>();
        while (set.size() < distinctCharacters) {
            set.clear();
            for (int i = leftIdx; i < rightIdx; ++i) {
                set.add(input.charAt(i));
            }
            leftIdx++;
            rightIdx++;
        }
        return rightIdx - 1;
    }

    public static void main(String[] args) {
        Scanner sc;
        try {
            sc = new Scanner(new File("txtfiles/Day6.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String input = sc.next();
        Day6 test = new Day6();
        // part 1
        System.out.println(test.firstMarker(input, 4));
        // part 2
        System.out.println(test.firstMarker(input, 14));
    }
}

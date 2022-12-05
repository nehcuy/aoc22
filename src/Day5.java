import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Day5 {
    private List<List<Integer>> instructions;
    private List<Stack<Character>> stacks;

    /**
     * Part 1 logic
     */
    public void executeMoves() {
        for (List<Integer> instr : instructions) {
            for (int i = 0; i < instr.get(0); ++i) {
                Character from = stacks.get(instr.get(1) - 1).pop();
                stacks.get(instr.get(2) - 1).push(from);
            }
        }
    }

    /**
     * Part 2 logic
     */
    public void executeCrateMover9001() {
        Stack<Character> temp = new Stack<>();
        for (List<Integer> instr : instructions) {
            for (int i = 0; i < instr.get(0); ++i) {
                Character from = stacks.get(instr.get(1) - 1).pop();
                temp.push(from);
            }
            while (!temp.isEmpty()) {
                stacks.get(instr.get(2) - 1).push(temp.pop());
            }
        }
    }

    private String printTopChars() {
        StringBuilder output = new StringBuilder();
        for (Stack<Character> stack : stacks) {
            output.append(stack.peek());
        }
        return output.toString();
    }

    private void initialiseInstructions() {
        List<List<Integer>> temp = new ArrayList<>();
        Scanner sc;
        try {
            File instructionSet = new File("C:\\Users\\65912\\OneDrive - National University of Singapore\\AdventOfCode\\2022\\txtfiles\\Day5\\Day5_instructions.txt");
            sc = new Scanner(instructionSet);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            List<Integer> curr = new ArrayList<>();
            for (int i = 0; i < 3; ++i) {
                curr.add(sc.useDelimiter("\\D+").nextInt());
            }
            temp.add(curr);
        }
        instructions = temp;
    }

    private void initialiseStacks() {
        List<Stack<Character>> temp = new ArrayList<>();
        for (int i = 0; i < 9; ++i) temp.add(new Stack<>());
        Scanner sc;
        try {
            File stackSet = new File("C:\\Users\\65912\\OneDrive - National University of Singapore\\AdventOfCode\\2022\\txtfiles\\Day5\\Day5_stack.txt");
            sc = new Scanner(stackSet);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            String currLine = sc.nextLine();
            int len = currLine.length();
            int stackIdx = 0;
            for (int i = 0; i < len; i += 4) {
                Character curr = currLine.charAt(i + 1);
                if (!curr.equals(' ')) temp.get(stackIdx).add(0, curr);
                stackIdx++;
            }
        }
        stacks = temp;
    }

    public List<Stack<Character>> getStacks() {
        return stacks;
    }

    public List<List<Integer>> getInstructions() {
        return instructions;
    }

    public static void main(String[] args) {
        Day5 test = new Day5();
        // part 1
        test.initialiseInstructions();
        test.initialiseStacks();
        test.executeMoves();
        System.out.println(test.printTopChars());
        // part 2
        test.initialiseInstructions();
        test.initialiseStacks();
        test.executeCrateMover9001();
        System.out.println(test.printTopChars());
    }
}

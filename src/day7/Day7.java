package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day7 {
    private final List<String[]> input = new ArrayList<>();
    private final Directory root = new Directory("/", null);
    private Directory currentDir;
    public static final long MAX_DISC_SPACE = 70000000;
    public static final long MIN_UNUSED_SPACE = 30000000;

    /**
     * Initialises the scanner.
     */
    private void initialise() {
        Scanner sc;
        try {
            sc = new Scanner(new File("txtfiles/Day7/Day7.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            input.add(sc.nextLine().split(" "));
        }
    }

    /**
     * Parses the input file and creates a directory tree.
     */
    private void parseCommands() {
        for (String[] line : input) {
            switch (line[0]) {
            case "$":
                if (line[1].equals("cd")) {
                    switch (line[2]) {
                    case "..":
                        currentDir = currentDir.parent;
                        break;
                    case "/":
                        currentDir = root;
                        break;
                    default:
                        currentDir = currentDir.children.get(line[2]);
                        break;
                    }
                }
                break;
            case "dir":
                Directory dir = new Directory(line[1], currentDir);
                currentDir.addDir(dir);
                break;
            default:
                Files file = new Files(line[1], Long.parseLong(line[0]));
                currentDir.addFile(file);
                break;
            }
        }
    }

    /**
     * Returns all directories with size less than limit, including duplicate subdirectories.
     *
     * @param startingDir the directory to start from
     * @param limit the limit
     * @return all directories with size less than limit
     */
    private long getSizeLessThan(Directory startingDir, long limit) {
        long size = 0;
        if (startingDir.getSize() < limit) {
            for (Directory dir : startingDir.children.values()) {
                size += getSizeLessThan(dir, limit);
            }
            size += startingDir.getSize();
        } else {
            for (Directory dir : startingDir.children.values()) {
                size += getSizeLessThan(dir, limit);
            }
        }
        return size;
    }

    public long partOne() {
        return getSizeLessThan(root, 100000);
    }

    private long minSpaceToDelete() {
        return MIN_UNUSED_SPACE - (MAX_DISC_SPACE - root.getSize());
    }

    /**
     * Gets the smallest possible directory size minimally needed to be deleted.
     *
     * @param startingDir the directory to start from
     * @param limit the limit
     * @return size of the smallest directory to be deleted
     */
    private long getMinSpaceToDelete(Directory startingDir, long limit) {
        long size = Long.MAX_VALUE;
        if (startingDir.getSize() < limit) {
            for (Directory dir : startingDir.children.values()) {
                size = Math.min(getMinSpaceToDelete(dir, limit), size);
            }
        } else {
            for (Directory dir : startingDir.children.values()) {
                size = Math.min(getMinSpaceToDelete(dir, limit), size);
            }
            size = Math.min(startingDir.getSize(), size);
        }
        return size;
    }

    public long partTwo() {
        return getMinSpaceToDelete(root, minSpaceToDelete());
    }

    public static void main(String[] args) {
        Day7 test = new Day7();
        test.initialise();
        test.parseCommands();
        // part 1
        System.out.println(test.partOne());
        // part 2
        System.out.println(test.partTwo());
    }
}

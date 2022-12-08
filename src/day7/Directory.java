package day7;

import java.util.HashMap;

public class Directory {
    private final String name;
    public Directory parent;
    public final HashMap<String, Directory> children; // Key: Directory Name, Value: Directory
    private final HashMap<String, Files> files; // Key: File Name, Value: Files

    Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        children = new HashMap<>();
        files = new HashMap<>();
    }

    public void addDir(Directory dir) {
        children.put(dir.name, dir);
    }

    public void addFile(Files file) {
        files.put(file.getName(), file);
    }

    /**
     * Returns the size of the directory.
     *
     * @return the size of the directory
     */
    public long getSize() {
        long size = 0;
        for (Files file : files.values()) {
            size += file.getSize();
        }
        for (Directory dir : children.values()) {
            size += dir.getSize();
        }
        return size;
    }

    /**
     * Returns all directories with size less than limit, including duplicate subdirectories.
     *
     * @param startingDir the directory to start from
     * @param limit the limit
     * @return all directories with size less than limit
     */
    public long getSizeLessThan(Directory startingDir, long limit) {
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

    @Override
    public String toString() {
        return "[Directory: " + name + ", ParentDir: " + parent.name + "]";
    }
}

package day7;

public class Files {
    private final String name;
    private final long size;

    Files(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "[File: " + name + ", Size: " + size + "]";
    }
}

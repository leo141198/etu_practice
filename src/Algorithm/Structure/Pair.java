package Algorithm.Structure;

public class Pair {
    private Integer first;
    private Integer second;

    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Pair(Pair other) {
        this.first = other.first;
        this.second = other.second;
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    public static Pair of(Integer first, Integer second) {
        return new Pair(first, second);
    }
}

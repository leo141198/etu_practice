package Algorithm.Structure;

public class SortStep {
    private Integer first;
    private Integer second;
    private Integer pivot;
    private boolean first_change;
    private boolean second_change;
    private boolean swap;

    public SortStep(Integer first, Integer second,  Integer pivot, boolean first_change, boolean second_change, boolean swap) {
        this.first = first;
        this.second = second;
        this.first_change = first_change;
        this.second_change = second_change;
        this.pivot = pivot;
        this.swap = swap;
    }

    public SortStep(SortStep other) {
        this.first = other.first;
        this.second = other.second;
        this.first_change = other.first_change;
        this.second_change = other.second_change;
        this.pivot = other.pivot;
        this.swap = other.swap;
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    public Integer getPivot() {
        return pivot;
    }

    public Boolean getFirstChange() {
        return first_change;
    }

    public Boolean getSecondChange() {
        return second_change;
    }

    public Boolean getSwap() {
        return swap;
    }
}

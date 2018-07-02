package Algorithm.Structure;

public class SortStep {
    private Integer first = null;
    private Integer second = null;
    private Integer pivot = null;
    private boolean first_change = false;
    private boolean second_change = false;
    private boolean swap = false;
    private boolean final_pos = false;

    public SortStep() {}

    public SortStep(SortStep other) {
        this.first = other.first;
        this.second = other.second;
        this.first_change = other.first_change;
        this.second_change = other.second_change;
        this.pivot = other.pivot;
        this.swap = other.swap;
        this.final_pos = other.final_pos;
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

    public Boolean getFinalPos() {
        return final_pos;
    }

    public SortStep setFirst(Integer first) {
        this.first = first;
        return this;
    }

    public SortStep setSecond(Integer second) {
        this.second = second;
        return this;
    }

    public SortStep setFirstChanged() {
        this.first_change = true;
        return this;
    }

    public SortStep setSecondChanged() {
        this.second_change = true;
        return this;
    }

    public SortStep setPivot(Integer pivot) {
        this.pivot = pivot;
        return this;
    }

    public SortStep setSwap() {
        this.swap = true;
        return this;
    }

    public SortStep setFinalPos() {
        this.final_pos = true;
        return this;
    }
}

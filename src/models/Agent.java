package models;

public abstract class Agent {

    protected final View view;

    public Agent(View view) {
        this.view = view;
    }

    public View getView() {
        return this.view;
    }

    public abstract Coord probe() throws NothingToProbeException;

    public void uncover(Coord coord, char value) {
        this.view.uncover(coord, value);
    }

    public int getUncoveredCount() {
        return this.view.getUncoveredCount();
    }
}

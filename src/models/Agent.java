package models;

public abstract class Agent {

    protected final View view;

    public Agent(View view) {
        this.view = view;
    }

    public void probe(Coord coord) {
        throw new RuntimeException("Not implemented");
    }
}

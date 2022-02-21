package models;

import delegate.Game;

public class BeginnerAgent extends Agent {

    public BeginnerAgent(Game game, View view) {
        super(game, view);
    }

    @Override
    public void playGame() throws NothingToProbeException, MineFoundException {
        throw new RuntimeException("Not implemented");
    }
}

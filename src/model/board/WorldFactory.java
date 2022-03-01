package model.board;

public class WorldFactory {

    public static World createWorld(String mode, String worldName) {
        switch (mode) {
            case "":
            case "rect":
                return RectWorld.valueOf(worldName);
            case "tri":
                return TriWorld.valueOf(worldName);
            case "hex":
                throw new RuntimeException("Not implemented yet.");
            default:
                throw new IllegalArgumentException("Unknown world mode: " + mode);
        }
    }

}

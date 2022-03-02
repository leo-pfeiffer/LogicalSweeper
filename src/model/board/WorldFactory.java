package model.board;

public class WorldFactory {

    /**
     * Given a mode and a world name, return the appropriate world
     *
     * @param mode The type of world to create.
     * @param worldName The name of the world to create.
     * @return The World object that corresponds to the world name.
     */
    public static World createWorld(String mode, String worldName) {
        switch (mode) {
            case "":
            case "rect":
                return RectWorld.valueOf(worldName);
            case "tri":
                return TriWorld.valueOf(worldName);
            case "hex":
                return HexWorld.valueOf(worldName);
            default:
                throw new IllegalArgumentException("Unknown world mode: " + mode);
        }
    }

}

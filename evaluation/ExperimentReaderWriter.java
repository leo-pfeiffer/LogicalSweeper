import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import delegate.ObscuredSweeper;
import model.Tracker;
import model.board.World;
import model.board.WorldFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ExperimentReaderWriter {

    private static final String EVALUATION_FOLDER = "evaluation/";
    private static final String OUT_FOLDER = "evaluation/out/";

    public static void main(String[] args) {
        Conf[] configurations = readJSON(EVALUATION_FOLDER + "configurations.json");
        try {
            assert configurations != null;
            ArrayList<Experiment> results = runExperiments(configurations);
            writeJSON(OUT_FOLDER + "results.json", results);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static ArrayList<Experiment> runExperiments(Conf[] configurations) {
        ArrayList<Experiment> experiments = new ArrayList<>();

        for (Conf conf : configurations) {
            for (World world : WorldFactory.getWorlds(conf.getMode())) {
                ObscuredSweeper game = new ObscuredSweeper(world, conf.getAgent());
                game.run();
                Experiment e = makeExperiment(conf, game);
                experiments.add(e);
            }
        }
        return experiments;
    }

    public static Experiment makeExperiment(Conf conf, ObscuredSweeper game) {
        Tracker tracker = game.getTracker();
        return new Experiment(
                conf.getAgent(),
                conf.getMode(),
                game.getWorldName(),
                tracker.isTerminated(),
                tracker.isAlive(),
                tracker.getPercentageRemaining(),
                tracker.getNumIterations(),
                tracker.getRunTime()
        );
    }

    /**
     * Read a JSON file containing the experiment settings.
     * */
    public static Conf[] readJSON(String path) {
        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            return gson.fromJson(bufferedReader, (Type) Conf[].class);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Write a JSON file with the experiment results.
     * */
    public static void writeJSON(String path, ArrayList<Experiment> experiments) {
        try {

            FileWriter writer = new FileWriter(path);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(experiments, writer);
            writer.close();

            System.out.println("Experiment results written to " + path);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
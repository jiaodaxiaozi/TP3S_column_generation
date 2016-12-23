package runner;

import algorithm.Algorithm;
import algorithm.ColumnGeneration;
import data.DataInstance;
import data.Reader;
import facility.ColumnGenerationFacility;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by yuhuishi on 12/22/2016.
 * University of Michigan
 * Academic use only
 */
public class RunNoFacilityVersion {

    public static void main(String[] args) {

    }

    private static void runSmall() {
        String instanceDir = "C:\\Users\\yuhuishi\\Desktop\\projects\\TP3S_column_generation\\instance\\small";

        try {
            File dir = new File(instanceDir);
            File[] files = dir.listFiles();

            Algorithm colgenSolver;

            if (files != null) {
                for (File child : files) {
                    System.out.println("Start solving w/o facility version " + child.getPath());
                    String outPath = child.getName().replace("tp3s", "log");
                    PrintStream ps = new PrintStream("./logs/small" + outPath);
                    System.setOut(ps);

                    Reader jsonReader = new Reader(child.getAbsolutePath());
                    DataInstance.init(jsonReader);
                    colgenSolver = new ColumnGeneration();

                    long time = System.nanoTime();

                    colgenSolver.solve();

                    System.out.println("Time spent " + (System.nanoTime() - time) / 1e6 + "ms");

                    ps.close();

                    // run the facility version
                    System.out.println("Start solving w/o facility version " + child.getPath());
                    ps = new PrintStream("./logs/facility/small" + outPath);
                    System.setOut(ps);

                    Algorithm solverFacility = new ColumnGenerationFacility();

                    solverFacility.solve();


                    ps.close();

                }
            } else {
                // Handle the case where dir is not really a directory.
                // Checking dir.isDirectory() above would not be sufficient
                // to avoid race conditions with another process that deletes
                // directories.
            }



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void runModerate() {

    }

    private static void runLarge() {

    }
}
package games.fx;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ScoreManager {
    //private static final String FILE_PATH = "scores.txt";

    private static String infoFileDir = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "FallenAngel" + File.separator;

    private static String fileName = "scores.txt";

    private static String FILE_PATH = infoFileDir+fileName;

    static File directory = new File( System.getProperty("user.home") + File.separator + "Documents" + File.separator + "FallenAngel");

    static File file = new File(infoFileDir,fileName);
    private static final int MAX_SCORES = 5;

    public ScoreManager() throws IOException {

    }

    public static void addScore(int score) throws IOException {
        if (!directory.exists()){
            directory.mkdirs();
        }

        if(!file.exists()){

            file.createNewFile();
        }
        List<Integer> scores = readScoresFromFile();
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());
        while (scores.size() > MAX_SCORES) {
            scores.remove(scores.size() - 1);
        }
        writeScoresToFile(scores);
    }

    public static List<Integer> getScores() throws IOException {
        if (!directory.exists()){
            directory.mkdirs();
        }

        if(!file.exists()){

            file.createNewFile();
        }
        return readScoresFromFile();
    }

    private static List<Integer> readScoresFromFile() throws IOException {
        if (!directory.exists()){
            directory.mkdirs();
        }

        if(!file.exists()){

            file.createNewFile();
        }
        List<Integer> scores = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return scores;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                scores.add(scanner.nextInt());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    private static void writeScoresToFile(List<Integer> scores) throws IOException {
        if (!directory.exists()){
            directory.mkdirs();
        }

        if(!file.exists()){

            file.createNewFile();
        }
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (int score : scores) {
                writer.write(score + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

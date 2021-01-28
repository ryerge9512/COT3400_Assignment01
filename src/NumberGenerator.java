import java.io.*;
import java.nio.*;
import java.util.Arrays;
import java.util.Random;

public class NumberGenerator {

    private final int MAX_POWER = 6;
    private int[] randomKeys;

    private Sort sorter;
    private Random generator;
    private File file;

    public NumberGenerator() throws IOException {
        int currentPower = 1;
        generator = new Random();
        while(currentPower <= MAX_POWER) {
            randomKeys = new int[(int) Math.pow(10, currentPower)];
            for(int i = 0; i < randomKeys.length; i++){
                randomKeys[i] = generator.nextInt();
            }
            writeToFile(randomKeys, randomKeys.length, "random");
            sortKeys(randomKeys);
            currentPower++;
        }
    }

    public void sortKeys(int[] randomKeys) throws IOException {
        sorter = new Sort(randomKeys);
        sorter.doMergeSort();
        writeToFile(sorter.getKeys(), sorter.getKeys().length, "ascending");
        sorter.doDescendingMergeSort();
        writeToFile(sorter.getKeys(), sorter.getKeys().length, "descending");

    }

    public void writeToFile(int[] keys, int numberOfKeys, String fileType) throws IOException {
        String fileName = "./" + numberOfKeys + "keys." + fileType + ".txt";
        try {
            file = new File(fileName);
            PrintWriter printWriter = new PrintWriter(file);

            for(int i = 0; i < keys.length; i++) {
                printWriter.println(keys[i]);
            }

            printWriter.close();

        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}

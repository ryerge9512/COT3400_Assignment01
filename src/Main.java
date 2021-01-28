import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Sort sorter;
        final int MAX_POWER = 6;
        int currentPower = 1;
        int[] array;
        String[] fileTypes = {"ascending", "descending" , "random"};

        NumberGenerator generator = new NumberGenerator();
        while(currentPower <= MAX_POWER) {
            for(int i = 0; i < fileTypes.length; i++) {
                String filePath = "./" + (int)Math.pow(10, currentPower) + "keys." + fileTypes[i] + ".txt";
                Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)));
                array = new int[(int)Math.pow(10, currentPower)];

                for(int j = 0; sc.hasNextLine(); j++) {
                    array[j] = Integer.parseInt(sc.nextLine());
                }
                sorter = new Sort(array);

                System.out.println("Insertion sort with " + array.length + " keys in " + fileTypes[i] + " order: \n");
                sorter.doInsertionSort();
                writeToFile(sorter.getKeys(), sorter.getKeys().length, sorter.getTimestamps(), fileTypes[i], "insertionSort");


                System.out.println("Merge sort with " + array.length + " keys in " + fileTypes[i] + " order: \n");
                sorter.doMergeSort();
                writeToFile(sorter.getKeys(), sorter.getKeys().length, sorter.getTimestamps(), fileTypes[i], "mergeSort");

            }
            currentPower++;
        }
    }

    public static void writeToFile(int[] keys, int numberOfKeys, long[] timestamps, String fileType, String algorithm) throws IOException {
        String fileName = "./output." + algorithm + "." + numberOfKeys + "keys." + fileType + ".txt";
        try {
            File file = new File(fileName);
            PrintWriter printWriter = new PrintWriter(file);

            for(int i = 0; i < keys.length; i++) {
                printWriter.println(keys[i] + " " + timestamps[i]);
            }

            printWriter.close();

        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}

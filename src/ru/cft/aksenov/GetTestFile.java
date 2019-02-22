package ru.cft.aksenov;

import java.io.*;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Get or create test files and presort them
 */
class GetTestFile {
    /**
     * @param fileName path to file
     * @param start    number of input files
     * @param entries  numbers of maximum entries in file
     * @param delta    random int from 0 to delta
     * @return
     * @throws IOException Get or create test files
     */
    private static int GetTestFile(String fileName, int start, int entries, int delta) throws IOException {
        Random r = new Random();
        int d = r.nextInt(delta);
        entries = entries - d;

        try (PrintStream fn = new PrintStream(createAndCheckFiles(fileName))) {
            for (int i = start; i < start + entries; i++) {
                if (r.nextBoolean()) fn.println(i);
            }
        }
        return start + entries;
    }

    /**
     * @param entries     numbers of maximum entries in file
     * @param delta       random int from 0 to delta
     */
    static void getTestFiles(int filesNumber, int entries, int delta) throws IOException {
        int start = 0;
        for (int i = 0; i < filesNumber; i++) {
            start = GetTestFile("E:\\mergeSortTest\\" + (i + 1) + ".txt", start, entries, delta);
        }
    }

    /**
     * @param fileName path to out file
     * @param parse    parse string to T
     * @param compare  return max of T-T(need for presort)
     * @param dir      direction of sort

     */
    static <T> void checkOutFile(String fileName, Function<String, T> parse, BiFunction<T, T, Integer> compare,
                                 int dir) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            T prev;
            String line = br.readLine();
            prev = parse.apply(line);
            line = br.readLine();
            while (line != null) {
                T next = parse.apply(line);
                Integer d = compare.apply(prev, next);
                if (d != 0) {
                    d = d / Math.abs(d);
                    if (d != dir) {
                        System.out.println("Отсортировано ошибочно");
                        return;
                    }
                }
                prev = next;
                line = br.readLine();


            }
        }
        System.out.println("Сортировка корректна");
    }


    /**
     * @return new File regardless of whether he existed
     */
    private static File createAndCheckFiles(String fileName) throws IOException {
        File f = new File(fileName);
        if (f.exists()){return f;}
        else f.createNewFile();



        return f;
    }

}

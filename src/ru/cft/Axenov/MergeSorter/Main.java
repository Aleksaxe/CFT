package ru.cft.Axenov.MergeSorter;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

    private static int getTestFile(String fileName, int start, int entries, int delta) throws IOException {
        Random r = new Random();
        int d = r.nextInt(delta);
        entries = entries - d;
        File f = new File(fileName);
        //  if (f.exists()) f.delete();
        //  f.createNewFile();
        try (PrintStream fn = new PrintStream(f)) {
            for (int i = start; i < start + entries; i++) {
                if (r.nextBoolean()) fn.println(i);
            }
        }
        return start + entries;
    }

    private static void getTestFiles(int filesNumber, int entries, int delta) throws IOException {
        int start = 0;
        for (int i = 0; i < filesNumber; i++) {
            start = getTestFile("E:\\mergeSortTest\\" + (i + 1) + ".txt", start, entries, delta);
        }
    }

    private static <T> void checkOutFile(String fileName, Function<String, T> parse, BiFunction<T, T, Integer> compare,
                                         int dir) throws IOException {
        try (FileReader fr = new FileReader(new File(fileName))) {
            try (BufferedReader st = new BufferedReader(fr)) {
                T prev;
                String line = st.readLine();
                prev = parse.apply(line);
                line = st.readLine();
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
                    line = st.readLine();

                }
            }
        }
        System.out.println("Сортировка корректна");
    }

    public static void main(String[] args) throws Exception {
        boolean restart = true;

            //getTestFiles(2, 5000, 2500);
            //checkOutFile("E:\\mergeSortTest\\out.txt", s -> Integer.parseInt(s), (i1, i2) -> i2.compareTo(i1), 1);
            //Создаем экземпляр LA для передачи аргументов командной строки
            LaunchArgs commandlineArguments = new LaunchArgs(args);
            //Направление сортировки
            int sortDir = commandlineArguments.getSortDirection();
            if (commandlineArguments.isOperatingStrings()) {
                //Если аргумент -s то сортируем этим
                FileSorter<String> fileSorter = new FileSorter<>(
                        commandlineArguments.getInFiles(),
                        commandlineArguments.getOutFile(),
                        (s1, s2) -> {
                            //если элемент первого и второго файлов ==null то возвращаем 0
                            if (s1 == null && s2 == null) return 0;
                            if (s1 == null) return sortDir;
                            if (s2 == null) return -sortDir;
                            return s1.compareTo(s2) * sortDir;

                        },
                        s -> s
                );
                fileSorter.outWrite();
            } else {
                //Если аргумент -i то сортируем этим
                FileSorter<Integer> fileSorter = new FileSorter<>(
                        commandlineArguments.getInFiles(),
                        commandlineArguments.getOutFile(),
                        (i1, i2) -> {
                            if (i1 == null && i2 == null) return 0;
                            if (i1 == null) return sortDir;
                            if (i2 == null) return -sortDir;
                            return (i2 - i1) * sortDir;
                        },
                        s -> Integer.parseInt(s)
                );
                fileSorter.outWrite();

            }



    }
}
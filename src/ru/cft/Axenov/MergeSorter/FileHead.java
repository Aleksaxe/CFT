package ru.cft.Axenov.MergeSorter;

import java.io.*;
import java.util.function.BiFunction;
import java.util.function.Function;

class FileHead<T> {
    private T headData;
    private BufferedReader br;
    private FileReader reader;
    private boolean finishReached;
    private BiFunction<T, T, Integer> comparer;
    private Function<String, T> converter;


    /**
     * @param filepath path to readable file
     * @throws FileNotFoundException
     */
    FileHead(File filepath, BiFunction<T, T, Integer> comparer, Function<String, T> converter) {
        boolean restart = true;

        try {
            reader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден или не правильно введен путь к файлу.");
        }
        br = new BufferedReader(reader);
        this.comparer = comparer;
        this.converter = converter;

    }

    T getHeadData() {
        return headData;
    }

    public boolean canAdvance() {
        return !finishReached;
    }

    /**
     * @param lastOutputWritten последняя запись для сравнения
     * @throws IOException
     */
    public void tryAdvance(T lastOutputWritten) throws IOException {

        String line;
        T data = null;
        while ((line = br.readLine()) != null) {
            data = converter.apply(line);
            boolean invalidOrder = comparer.apply(data, lastOutputWritten) > 0;

            if (invalidOrder && lastOutputWritten != null) {
                System.out.println("Нарушен порядок сортировки: элемент " + line +
                        " меньше последнего записанного в выходной файл элемента "
                        + lastOutputWritten.toString());
            } else {
                break;
            }
        }

        if (line == null) {
            finishReached = true;
            close();
        }

        headData = data;

    }


    private void close() throws IOException {
        br.close();
        reader.close();
    }

}
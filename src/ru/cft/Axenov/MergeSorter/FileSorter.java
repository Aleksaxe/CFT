package ru.cft.Axenov.MergeSorter;


import java.io.*;
import java.util.function.BiFunction;
import java.util.function.Function;

class FileSorter<T> {
    private FileHead<T>[] fileHeads;
    private String outFileName;
    private BiFunction<T, T, Integer> compareFunc;

    public FileSorter(String[] inFileNames, String outFileName,
                      BiFunction<T, T, Integer> compareFunc,
                      Function<String,T> converter) throws IOException {

        this.compareFunc = compareFunc;
        fileHeads = new FileHead[inFileNames.length];
        for (int i = 0; i < inFileNames.length; i++) {
            fileHeads[i] = new FileHead<T>(new File(inFileNames[i]), compareFunc,converter);
            fileHeads[i].tryAdvance(null);
        }
        this.outFileName = outFileName;
    }

    /**
     * Запись в конечный файл
     */
    public void outWrite() throws IOException {

        //отерываем выходной файл
        try(FileWriter fw = new FileWriter(outFileName)) {
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                try (PrintWriter pw = new PrintWriter(bw)) {

                    //начинаем бегать по входным файлам
                    while (true) {

                        T min = null;//минимальный элемент из всех открытых файлов
                        int advanceIdx = -1; // индекс файла, где найден минимальный элемент
                        for (int i = 0; i < fileHeads.length; i++) { //идём по всем открытым файлам
                            if (fileHeads[i].canAdvance()) {
                                /*если не достигнут конец файла, то
                                //ищем минимальный элемент
                                // сравнимаем, и если элемент из текущего файла меньше минимального
                                 ну или если он - первый попавшийся*/
                                if (min==null|| compareFunc.apply(min, fileHeads[i].getHeadData()) < 0) {

                                    // сохраняем индекс файла, в котором содержался самый минимальный элемент
                                    advanceIdx = i;
                                    // сохраняем сам элемент, шоб було
                                    min = fileHeads[i].getHeadData();
                                }
                            }
                        }

                        // если минимального элемента не обнаружено, то выходим - все файлы дочитаны
                        if (advanceIdx == -1) break;

                        else {
                            //в противном случае пишем в выходной файл минимальный элемент
                            pw.println(min);
                            // а тот входной файл, в котором обнаружили минимальный элемент - продвигаем вперёд
                            fileHeads[advanceIdx].tryAdvance(min);
                        }
                    }
                }
            }
        }
    }
}
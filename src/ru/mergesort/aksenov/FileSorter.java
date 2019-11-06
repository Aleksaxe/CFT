package ru.mergesort.aksenov;
import java.io.*;
import java.util.function.BiFunction;
import java.util.function.Function;

class FileSorter<T> {
    private FileHead<T>[] fileHeads;
    private String outFileName;
    private BiFunction<T, T, Integer> compareFunc;

    /**
     * @param inFileNames array in files
     * @param outFileName out file
     * @param compareFunc compare second param with first
     * @param converter convert func (s=s if string \ s=Integer(Parse.int(s)  if int))
     * @throws IOException
     */
    public FileSorter(String[] inFileNames, String outFileName,
                      BiFunction<T, T, Integer> compareFunc,
                      Function<String,T> converter) throws IOException {

        this.compareFunc = compareFunc;
        fileHeads = new FileHead[inFileNames.length];
        for (int i = 0; i < inFileNames.length; i++) {
            fileHeads[i] = new FileHead<>(new File(inFileNames[i]), compareFunc, converter);
            fileHeads[i].tryAdvance(null);
        }
        this.outFileName = outFileName;
    }

    /**
     *  write to ending file
     */
    void outWrite() throws IOException {


        try(PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(outFileName)))) {

                    while (true) {
                        /**
                         *@param min minimal elem from all opened files
                         * @param advanceIdx file index with minimal elem
                         */
                        T min = null;
                        int advanceIdx = -1;
                        for (int i = 0; i < fileHeads.length; i++) { //идём по всем открытым файлам
                            if (fileHeads[i].canAdvance()) {
                                /*если не достигнут конец файла, то
                                //ищем минимальный элемент
                                // сравнимаем, и если элемент из текущего файла меньше минимального
                                 ну или если он - первый попавшийся*/
                                if (min==null|| compareFunc.apply(min, fileHeads[i].getHeadData()) < 0) {

                                    // сохраняем индекс файла, в котором содержался самый минимальный элемент
                                    advanceIdx = i;
                                    // сохраняем сам элемент
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


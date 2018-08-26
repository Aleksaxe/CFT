import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.BiFunction;

class FileSorter<T> {
    private FileHead<T>[] fileHeads;
    private String outFileName;
    private BiFunction<T, T, Integer> compareFunc;
    private boolean someCanAdvance;

    public FileSorter(BiFunction<Integer, String, FileHead<T>> headCreator,
                      BiFunction<T, T, Integer> compareFunc,
                      String[] inFileNames, String outFileName) {
        this.compareFunc = compareFunc;
        for (int i = 0; i < inFileNames.length; i++) {
            fileHeads[i] = headCreator.apply(i, inFileNames[i]);
        }
        this.outFileName = outFileName;
    }

    /**
     * Запись в конечный файл
     */
    public void outWrite() throws IOException {
        FileWriter fw = new FileWriter(outFileName);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        {
            T lastOutputWritten = null;//последний записанный элемент
            while (true) {

                T min = null;//минимальный записанный элемент
                for (int i = 0; i < fileHeads.length; i++) {//пока длинна объекта fileHeads больше
                    if (fileHeads[i].canAdvance()) {//есть ли элемент к прочтению?
                        someCanAdvance = true;
                        fileHeads[i].tryAdvance(lastOutputWritten);
                        //если compareFunc возвращает меньше 0
                        if (compareFunc.apply(min, fileHeads[i].getHeadData()) < 0) {
                            min = fileHeads[i].getHeadData();//то  минимальному элементу присваиваем HeadData
                        }
                    }
                }
                if (min == null) break;//если минимальный эллемент нулевой выходим из if
                else {
                    pw.println(min);//записать в файл минимальный элемент
                    lastOutputWritten = min;// присвоить последнем записанному минимальный элемент
                }
            }
        }
    }
}
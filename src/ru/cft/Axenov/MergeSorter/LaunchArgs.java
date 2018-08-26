package ru.cft.Axenov.MergeSorter;

import java.util.ArrayList;
import java.util.List;

public class LaunchArgs {

    private String[] commandLineArguments;
//массив передоваемых аргументов для командной строки
    public LaunchArgs(String[] commandLineArguments) {

        this.commandLineArguments = commandLineArguments;
    }

    /**
     *Направление сортировки
     */
    public int getSortDirection() {

        for (int i = 0; i < commandLineArguments.length; i++) {

            if (commandLineArguments[i].equals("-a")) return 1;
        }
        return -1;
    }

    /**
     *Тип входных файлов -i цифры \ -s символы
     */
    public boolean isOperatingStrings() throws Exception {
        for (int i = 0; i < commandLineArguments.length; i++) {
            if (commandLineArguments[i].equals("-s")) return true;
            if (commandLineArguments[i].equals("-i")) return false;

        }
        throw new Exception("Параметры -i или -s являются обязательными");
    }
    /**
     *Обработка аргумента входного файла
     */

    public String getOutFile() throws Exception {
        for (int i = 0; i < commandLineArguments.length; i++) {
            if (!commandLineArguments[i].startsWith("-")) return commandLineArguments[i];
        }
        //на случай отсутствия аргумента выходного файла
        throw new Exception("Выходной файл не указан");
    }

    /**
     *Обработка аргумента входных файлов
     */

   public String[] getInFiles() throws Exception {
        boolean skipOut = false;
        List<String> result = new ArrayList<>();//Хранит пути кфайлам

        for (int i = 0; i < commandLineArguments.length; i++) {
            if (!commandLineArguments[i].startsWith("-")) {
                if (!skipOut) {
                    skipOut = true;

                } else {
                    result.add(commandLineArguments[i]);
                }
            }
        }
        //на случай отсутсвия аргументов входных файлов
        if (result.isEmpty()) throw new Exception("Входные файлы не указаны");
        //костыль дабы вернуть массив,а не список
        String[] reslt = new String[result.size()];
        result.toArray(reslt);
        return reslt;
    }
}

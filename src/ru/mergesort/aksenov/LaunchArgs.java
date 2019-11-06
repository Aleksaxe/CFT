package ru.mergesort.aksenov;

import java.util.ArrayList;
import java.util.List;

/**
 * Get launch arguments and parse it
 */
class LaunchArgs {

    private static String[] commandLineArguments;

    LaunchArgs(String[] commandLineArguments) {

        LaunchArgs.commandLineArguments = commandLineArguments;
    }

    /**
     * Sort direction
     */
    int getSortDirection() {

        for (String commandLineArgument : commandLineArguments) {

            if (commandLineArgument.equals("-a")) return 1;
        }
        return -1;
    }

    /**
     * Input files type -i numbers \ -s characters
     */
    boolean isOperatingStrings() throws Exception {
        for (String commandLineArgument : commandLineArguments) {
            if (commandLineArgument.equals("-s")) return true;
            if (commandLineArgument.equals("-i")) return false;

        }
        throw new Exception("Параметры -i или -s являются обязательными");
    }

    boolean needToCreate() throws Exception {
        for (String commandLineArgument : commandLineArguments) {
            if (commandLineArgument.equals("-y")) return true;
            if (commandLineArgument.equals("-n")) return false;
        }
        throw new Exception("Что-то не так с созданием фалов!");
    }

    String getDir() throws Exception {
        for (String commandLineArgument : commandLineArguments) {
            if (!commandLineArgument.startsWith("-")) return commandLineArgument;
        }

        throw new Exception("Не указанна директория");
    }

    /**
     * Processing output file argument
     */

    String getOutFile() throws Exception {
        boolean skipOut = false;
        for (String commandLineArgument : commandLineArguments) {
            if (!commandLineArgument.startsWith("-")) {
                if (!skipOut) {
                    skipOut = true;
                } else {
                    return commandLineArgument;
                }
            }

        }

        throw new Exception("Выходной файл не указан");
    }

    /**
     * Processing input file argument
     */

    String[] getInFiles() throws Exception {
        int skipToInFiles = 2;
        List<String> result = new ArrayList<>();

        for (String commandLineArgument : commandLineArguments) {
            if (!commandLineArgument.startsWith("-")) {
                if (skipToInFiles != 0) {
                    --skipToInFiles;
                } else {
                    result.add(commandLineArgument);
                }
            }
        }

        if (result.isEmpty()) throw new Exception("Входные файлы не указаны");
        String[] reslt = new String[result.size()];
        result.toArray(reslt);
        return reslt;
    }


}

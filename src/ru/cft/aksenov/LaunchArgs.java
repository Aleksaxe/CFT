package ru.cft.aksenov;

import java.util.ArrayList;
import java.util.Arrays;
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
     *Sort direction
     */
    int getSortDirection() {

        for (String commandLineArgument : commandLineArguments) {

            if (commandLineArgument.equals("-a")) return 1;
        }
        return -1;
    }

    /**
     *Input files type -i numbers \ -s characters
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
        throw new Exception("Параметры -i или -s являются обязательными");
    }
    /**
     *Processing output file argument
     */

    String getOutFile() throws Exception {
        for (String commandLineArgument : commandLineArguments) {
            if (!commandLineArgument.startsWith("-")) return commandLineArgument;
        }

        throw new Exception("Выходной файл не указан");
    }

    /**
     *Processing input file argument
     */

    String[] getInFiles() throws Exception {
        boolean skipOut = false;
        List<String> result = new ArrayList<>();

        for (String commandLineArgument : commandLineArguments) {
            if (!commandLineArgument.startsWith("-")) {
                if (!skipOut) {
                    skipOut = true;

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

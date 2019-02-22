package ru.cft.aksenov;

import java.util.Arrays;
import java.util.Collections;

import static ru.cft.aksenov.GetTestFile.checkOutFile;
import static ru.cft.aksenov.GetTestFile.getTestFiles;

class Launch {
    Launch(String[] args) throws Exception {
        LaunchArgs commandlineArguments = new LaunchArgs(args);
        if (commandlineArguments.needToCreate()) {
            getTestFiles(3, 5000, 100);
        }
        int sortDir = commandlineArguments.getSortDirection();

        if (commandlineArguments.isOperatingStrings()) {
            sortString(commandlineArguments, sortDir);
        } else sortInteger(commandlineArguments, sortDir);

        checkOutFile("E:\\mergeSortTest\\out.txt",
                s -> Integer.parseInt(s), (i1, i2) -> i2.compareTo(i1), 1);
    }

    void sortString(LaunchArgs commandlineArguments, int sortDir) throws Exception {
        FileSorter<String> fileSorter = new FileSorter<>(
                commandlineArguments.getInFiles(),
                commandlineArguments.getOutFile(),
                (s1, s2) -> {
                    if (s1 == null && s2 == null) return 0;
                    if (s1 == null) return sortDir;
                    if (s2 == null) return -sortDir;
                    return s1.compareTo(s2) * sortDir;

                },
                s -> s
        );
        fileSorter.outWrite();

    }

    void sortInteger(LaunchArgs commandlineArguments, int sortDir) throws Exception {
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

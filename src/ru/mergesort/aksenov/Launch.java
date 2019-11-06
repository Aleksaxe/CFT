package ru.mergesort.aksenov;
import static ru.mergesort.aksenov.GetTestFile.checkOutFile;
import static ru.mergesort.aksenov.GetTestFile.getTestFiles;

class Launch {
    Launch(String[] args) throws Exception {
        LaunchArgs commandlineArguments = new LaunchArgs(args);
        if (commandlineArguments.needToCreate()) {
            GetTestFile.getTestFiles(2, 5000,commandlineArguments.getDir());
        }
        int sortDir = commandlineArguments.getSortDirection();

        if (commandlineArguments.isOperatingStrings()) {
            sortString(commandlineArguments, sortDir);
        } else sortInteger(commandlineArguments, sortDir);

        GetTestFile.checkOutFile(commandlineArguments.getOutFile(),
                Integer::parseInt, (i1, i2) -> i2.compareTo(i1), 1);
    }

    private void sortString(LaunchArgs commandlineArguments, int sortDir) throws Exception {
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

    private void sortInteger(LaunchArgs commandlineArguments, int sortDir) throws Exception {
        FileSorter<Integer> fileSorter = new FileSorter<>(
                commandlineArguments.getInFiles(),
                commandlineArguments.getOutFile(),
                (i1, i2) -> {
                    if (i1 == null && i2 == null) return 0;
                    if (i1 == null) return sortDir;
                    if (i2 == null) return -sortDir;
                    return (i2 - i1) * sortDir;
                },
                Integer::parseInt
        );
        fileSorter.outWrite();

    }
}

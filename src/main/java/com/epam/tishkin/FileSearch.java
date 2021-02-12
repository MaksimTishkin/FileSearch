package com.epam.tishkin;

import java.nio.file.Path;
import java.util.List;

public interface FileSearch {
    Path getSearchByFileName(String directory, String fileName);
    Path getFullPathFromRootDirectory(String directory, String fileName);
    List<Path> showFilesInDirectory(String directory);
}

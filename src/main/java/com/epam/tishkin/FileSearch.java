package com.epam.tishkin;

import java.nio.file.Path;

public interface FileSearch {
    Path getSearchByFileName(String directory, String fileName);
    Path getFullPathFromRootDirectory(String directory, String fileName);
}

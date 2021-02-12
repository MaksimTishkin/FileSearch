package com.epam.tishkin;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchAbsolutePathForFile extends SearchWithVisitFile {

    public SearchAbsolutePathForFile(String fileName) {
        super(fileName);
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) {
        if (currentFile.getFileName().toString().equals(fileName)) {
            file = currentFile.toAbsolutePath();
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }
}

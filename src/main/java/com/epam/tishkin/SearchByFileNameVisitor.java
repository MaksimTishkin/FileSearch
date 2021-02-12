package com.epam.tishkin;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchByFileNameVisitor extends SearchWithVisitFile {

    public SearchByFileNameVisitor(String fileName) {
        super(fileName);
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) {
        if (currentFile.getFileName().toString().equals(fileName)) {
            file = currentFile;
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }
}

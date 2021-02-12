package com.epam.tishkin;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchByFileNameVisitor extends SimpleFileVisitor<Path> {
    private final String fileName;
    private Path file;

    public SearchByFileNameVisitor(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) {
        if (currentFile.getFileName().toString().equals(fileName)) {
            file = currentFile;
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    public Path getFile() {
        return file;
    }
}

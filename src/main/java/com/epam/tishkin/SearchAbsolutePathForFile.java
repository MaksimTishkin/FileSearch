package com.epam.tishkin;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchAbsolutePathForFile extends SimpleFileVisitor<Path> {
    private final String fileName;
    private Path file;

    public SearchAbsolutePathForFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) {
        if (currentFile.getFileName().toString().equals(fileName)) {
            file = currentFile.toAbsolutePath();
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    public Path getFile() {
        return file;
    }
}

package com.epam.tishkin;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFilesInDirectory extends SimpleFileVisitor<Path> {
    private final List<Path> listOfFilesInDirectory;

    public SearchFilesInDirectory() {
        listOfFilesInDirectory = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path currentFile, BasicFileAttributes attrs) {
        listOfFilesInDirectory.add(currentFile.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getListOfFilesInDirectory() {
        return listOfFilesInDirectory;
    }
}

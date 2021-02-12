package com.epam.tishkin;

import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public abstract class SearchWithVisitFile extends SimpleFileVisitor<Path> {
    String fileName;
    Path file;

    public SearchWithVisitFile(String fileName) {
        this.fileName = fileName;
    }

    public Path getFile() {
        return file;
    }
}

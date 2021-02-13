package com.epam.tishkin;

import java.nio.file.Path;

public class Request {
    private final String prefix;
    private final Path file;

    public Request(String prefix, Path file) {
        this.prefix = prefix;
        this.file = file;
    }

    public String getPrefix() {
        return prefix;
    }

    public Path getFile() {
        return file;
    }
}

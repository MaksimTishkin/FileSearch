package com.epam.tishkin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class FileSearchTest {
    private static String folder;
    private static String fileName;
    private static Path expectedPathForSearchInFolder;

    @BeforeAll
    static void initAll() {
        folder = "C:\\Module\\";
        fileName = "saveGift.ser";
        expectedPathForSearchInFolder = Paths.get("C:\\Module\\saveGift.ser");
    }

    @Test
    public void testGetSearchByFileName() throws IOException {
        FileSearch fileSearchMock = Mockito.mock(FileSearch.class);
        Mockito.when(fileSearchMock.getSearchByFileName(folder, fileName))
                .thenReturn(expectedPathForSearchInFolder);
        SearchByFileNameVisitor fileVisitor = new SearchByFileNameVisitor(fileName);
        Files.walkFileTree(Paths.get(folder), fileVisitor);
        Assertions.assertEquals(expectedPathForSearchInFolder, fileVisitor.getFile());
    }
}

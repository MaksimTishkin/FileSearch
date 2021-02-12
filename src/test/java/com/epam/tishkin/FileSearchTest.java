package com.epam.tishkin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileSearchTest {
    private static FileSearch fileSearchMock;
    private static String folder;
    private static String validFileName;
    private static String invalidFileName;
    private static String expectedAbsolutePathForFile;
    private static List<Path> listOfFilesInDirectory;

    @BeforeAll
    static void initAll() {
        fileSearchMock = Mockito.mock(FileSearch.class);
        folder = "C:\\Module\\";
        validFileName = "saveGift.ser";
        invalidFileName = "Solution.class";
        expectedAbsolutePathForFile = "C:\\Module\\out\\production\\saveGift.ser";
        listOfFilesInDirectory = new ArrayList<>();
        listOfFilesInDirectory.add(Paths.get("saveGift.ser"));
        listOfFilesInDirectory.add(Paths.get("saveBouquet.ser"));
        listOfFilesInDirectory.add(Paths.get("saveHome.ser"));
    }

    @Test
    public void testGetSearchByFileName() throws IOException {
        Mockito.when(fileSearchMock.getSearchByFileName(folder, validFileName))
                .thenReturn(Paths.get(validFileName));
        Path expectedFileForSearchInFolder = fileSearchMock.getSearchByFileName(folder, validFileName);
        SearchByFileNameVisitor fileVisitor = new SearchByFileNameVisitor(validFileName);
        Files.walkFileTree(Paths.get(folder), fileVisitor);
        Assertions.assertEquals(expectedFileForSearchInFolder, fileVisitor.getFile().getFileName());
        Mockito.when(fileSearchMock.getSearchByFileName(folder, invalidFileName))
                .thenReturn(null);
        expectedFileForSearchInFolder = fileSearchMock.getSearchByFileName(folder, invalidFileName);
        fileVisitor = new SearchByFileNameVisitor(invalidFileName);
        Files.walkFileTree(Paths.get(folder), fileVisitor);
        Assertions.assertEquals(expectedFileForSearchInFolder, fileVisitor.getFile());
    }

    @Test
    public void testGetFullPathFromRootDirectory() throws IOException {
        Mockito.when(fileSearchMock.getFullPathFromRootDirectory(folder, validFileName))
                .thenReturn(Paths.get(expectedAbsolutePathForFile));
        SearchAbsolutePathForFile fileVisitor = new SearchAbsolutePathForFile(validFileName);
        Files.walkFileTree(Paths.get(folder), fileVisitor);
        Assertions.assertEquals(Paths.get(expectedAbsolutePathForFile), fileVisitor.getFile().toAbsolutePath());
    }

    @Test
    public void testSearchFilesInDirectory() throws IOException {
        Mockito.when(fileSearchMock.showFilesInDirectory(folder))
                .thenReturn(listOfFilesInDirectory);
        SearchFilesInDirectory fileVisitor = new SearchFilesInDirectory();
        Files.walkFileTree(Paths.get(folder), fileVisitor);
        Assertions.assertEquals(listOfFilesInDirectory, fileVisitor.getListOfFilesInDirectory());
    }
}

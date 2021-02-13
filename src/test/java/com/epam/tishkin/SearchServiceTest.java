package com.epam.tishkin;

import com.epam.tishkin.exceptions.InvalidRequestPrefixException;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SearchServiceTest {
    private static FileSearch fileSearchMock;
    private static SearchService searchService;
    private static Request requestForFileSearch;
    private static Request requestForShowDirectory;
    private static Request requestForGetFullPath;
    private static Request requestWithInvalidPrefix;
    private static List<Path> filesInDirectory;
    private static Path foundFile;
    private static Path absolutePath;

    @BeforeAll
    static void initAll() {
        fileSearchMock = Mockito.mock(FileSearch.class);
        searchService = new SearchService(fileSearchMock);
        requestForFileSearch = new Request("file", Paths.get("saveGift.ser"));
        requestForShowDirectory = new Request("directory", Paths.get("C:\\Module\\"));
        requestForGetFullPath = new Request("path", Paths.get("saveCar.ser"));
        requestWithInvalidPrefix = new Request("", Paths.get("readme.txt"));
        filesInDirectory = Arrays.asList(Paths.get("saveBouquet.ser"), Paths.get("saveHome.ser"));
        foundFile = Paths.get("saveGift.ser");
        absolutePath = Paths.get("C:\\Module\\out\\production\\saveCar.ser");
    }

    @Before
    public void setUp() {
        Mockito.when(fileSearchMock.getSearchByFileName(requestForFileSearch.getFile()))
                .thenReturn(foundFile);
        Mockito.when(fileSearchMock.showFilesInDirectory(requestForShowDirectory.getFile()))
                .thenReturn(filesInDirectory);
        Mockito.when(fileSearchMock.getFullPathFromRootDirectory(requestForGetFullPath.getFile()))
                .thenReturn(absolutePath);
    }

    @Test
    public void testRequestWithFilePrefix() throws InvalidRequestPrefixException {
        searchService.searchByRequest(requestForFileSearch);
        Mockito.verify(fileSearchMock).getSearchByFileName(requestForFileSearch.getFile());
        Mockito.verify(fileSearchMock, Mockito.never()).showFilesInDirectory(requestForFileSearch.getFile());
        Mockito.verify(fileSearchMock, Mockito.never()).getFullPathFromRootDirectory(requestForFileSearch.getFile());
    }

    @Test
    public void testRequestWithDirectoryPrefix() throws InvalidRequestPrefixException {
        searchService.searchByRequest(requestForShowDirectory);
        Mockito.verify(fileSearchMock).showFilesInDirectory(requestForShowDirectory.getFile());
        Mockito.verify(fileSearchMock, Mockito.never()).getSearchByFileName(requestForShowDirectory.getFile());
        Mockito.verify(fileSearchMock, Mockito.never()).getFullPathFromRootDirectory(requestForShowDirectory.getFile());
    }

    @Test
    public void testRequestWithPathPrefix() throws InvalidRequestPrefixException {
        searchService.searchByRequest(requestForGetFullPath);
        Mockito.verify(fileSearchMock).getFullPathFromRootDirectory(requestForGetFullPath.getFile());
        Mockito.verify(fileSearchMock, Mockito.never()).getSearchByFileName(requestForGetFullPath.getFile());
        Mockito.verify(fileSearchMock, Mockito.never()).showFilesInDirectory(requestForGetFullPath.getFile());
    }

    @Test
    public void testRequestWithInvalidPrefix() {
        Assertions.assertThrows(InvalidRequestPrefixException.class, () ->
                searchService.searchByRequest(requestWithInvalidPrefix));
    }
}

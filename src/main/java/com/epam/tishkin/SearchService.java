package com.epam.tishkin;

import com.epam.tishkin.exceptions.InvalidRequestPrefixException;

public class SearchService {
    FileSearch fileSearch;

    public SearchService(FileSearch fileSearch) {
        this.fileSearch = fileSearch;
    }

    public void searchByRequest(Request request) throws InvalidRequestPrefixException {
        switch (request.getPrefix()) {
            case "directory":
                fileSearch.showFilesInDirectory(request.getFile());
                break;
            case "file":
                fileSearch.getSearchByFileName(request.getFile());
                break;
            case "path":
                fileSearch.getFullPathFromRootDirectory(request.getFile());
                break;
            default:
                throw new InvalidRequestPrefixException("Invalid prefix");
        }
    }
}

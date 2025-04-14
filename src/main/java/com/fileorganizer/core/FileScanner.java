package com.fileorganizer.core;

import com.fileorganizer.exceptions.DirectoryNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileScanner {
    public List<Path> scanFiles(Path dir) throws DirectoryNotFoundException {
        validateDirectory(dir);
        try (Stream<Path> paths = Files.list(dir)) {
            return paths.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            throw new DirectoryNotFoundException("Error accessing directory: " + dir, e);
        }
    }

    public List<Path> scanDirectories(Path dir) throws DirectoryNotFoundException {
        validateDirectory(dir);
        try (Stream<Path> paths = Files.list(dir)) {
            return paths.filter(Files::isDirectory).collect(Collectors.toList());
        } catch (IOException e) {
            throw new DirectoryNotFoundException("Error accessing directory: " + dir, e);
        }
    }

    private void validateDirectory(Path dir) throws DirectoryNotFoundException {
        if (!Files.isDirectory(dir)) {
            throw new DirectoryNotFoundException("Not a directory: " + dir);
        }
    }
}
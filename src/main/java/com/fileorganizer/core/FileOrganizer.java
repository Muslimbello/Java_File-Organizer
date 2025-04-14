package com.fileorganizer.core;

import com.fileorganizer.exceptions.DirectoryNotFoundException;
import com.fileorganizer.exceptions.FileMoveException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileOrganizer {
    private final FileScanner fileScanner = new FileScanner();
    private final FileMover fileMover = new FileMover();

    public void organize(Path targetDir) {
        try {
            if (!Files.isDirectory(targetDir)) {
                throw new DirectoryNotFoundException("Target path is not a directory: " + targetDir);
            }

            Path unsortedDir = targetDir.resolve("Unsorted");
            Files.createDirectories(unsortedDir);

            List<Path> existingDirs = fileScanner.scanDirectories(targetDir).stream()
                    .filter(path -> !path.getFileName().toString().equalsIgnoreCase("Unsorted"))
                    .collect(Collectors.toList());

            List<Path> files = fileScanner.scanFiles(targetDir);
            FileCategorizer categorizer = new FileCategorizer(targetDir);

            for (Path file : files) {
                Path targetCategory = categorizer.categorize(file, existingDirs);
                try {
                    fileMover.moveFile(file, targetCategory);
                    System.out.println("Moved " + file.getFileName() + " to " + targetCategory.getFileName());
                } catch (FileMoveException e) {
                    System.err.println("Failed to move file: " + file.getFileName() + " - " + e.getMessage());
                }
            }
        } catch (IOException | DirectoryNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
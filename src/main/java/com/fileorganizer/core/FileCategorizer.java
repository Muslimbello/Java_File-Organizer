package com.fileorganizer.core;

import com.fileorganizer.utils.FileSimilarity;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class FileCategorizer {
    private final Path unsortedDir;
    private final Scanner scanner;

    public FileCategorizer(Path targetDir) {
        this.unsortedDir = targetDir.resolve("Unsorted");
        this.scanner = new Scanner(System.in);
    }

    public Path categorize(Path file, List<Path> existingDirs) {
        String fileName = getBaseName(file.getFileName().toString());
        double maxSimilarity = -1;
        Path bestMatch = null;

        for (Path dir : existingDirs) {
            double similarity = FileSimilarity.calculateSimilarity(fileName, dir.getFileName().toString());
            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                bestMatch = dir;
            }
        }

        if (maxSimilarity >= 0.4) {
            System.out.println("File: " + file.getFileName());
            System.out.println("Proposed Category: " + bestMatch.getFileName());
            System.out.print("Confirm move? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes")) {
                return bestMatch;
            }
        }


        return unsortedDir;
    }

    private String getBaseName(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        return (lastDot == -1) ? fileName : fileName.substring(0, lastDot);
    }
}

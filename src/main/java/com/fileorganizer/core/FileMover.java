package com.fileorganizer.core;

import com.fileorganizer.exceptions.FileMoveException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileMover {
    public void moveFile(Path source, Path targetDir) throws FileMoveException {
        try {
            Files.createDirectories(targetDir);
            Path target = resolveUniqueName(source, targetDir);
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileMoveException("Failed to move file: " + source, e);
        }
    }

    private Path resolveUniqueName(Path source, Path targetDir) {
        String fileName = source.getFileName().toString();
        Path target = targetDir.resolve(fileName);
        int counter = 1;

        while (Files.exists(target)) {
            String base = getBaseName(fileName);
            String ext = getFileExtension(fileName);
            String newName = base + "_" + counter + (ext.isEmpty() ? "" : "." + ext);
            target = targetDir.resolve(newName);
            counter++;
        }
        return target;
    }

    private String getBaseName(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        return (lastDot == -1) ? fileName : fileName.substring(0, lastDot);
    }

    private String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        return (lastDot == -1) ? "" : fileName.substring(lastDot + 1);
    }
}
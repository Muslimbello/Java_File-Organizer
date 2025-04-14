package com.fileorganizer;

import com.fileorganizer.core.FileOrganizer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the directory to organize: ");
        String dirPath = scanner.nextLine().trim();
        Path targetDir = Paths.get(dirPath);

        FileOrganizer organizer = new FileOrganizer();
        try {
            organizer.organize(targetDir);
            System.out.println("Organization completed successfully.");
        } catch (Exception e) {
            System.err.println("Error during organization: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
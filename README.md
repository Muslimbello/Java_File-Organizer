# File Organizer

A Java application that helps organize files in a directory by automatically sorting them into appropriate folders based on file types.

## Overview

File Organizer is a command-line utility that scans a specified directory, categorizes files based on their extensions, and moves them into designated folders. If the appropriate folder doesn't exist, the application creates it automatically.

## Project Structure

src/main/java/com/fileorganizer/
├── core/ # Core functionality for file organization
├── exceptions/ # Custom exceptions for error handling
├── utils/ # Utility classes for file operations
└── Main.java # Entry point of the application

## Requirements

- Java 8 or higher
- Maven (for dependency management)

## Installation

1. Clone the repository:

2. Navigate to the project directory:
   cd fileorganizer

3. Build the project:
   mvn clean install

## Usage

1. Run the `Main.java` file:
   java -cp target/fileorganizer-1.0.jar com.fileorganizer.Main
2. When prompted, provide the full path to the directory you want to organize:
   Please enter the path of the directory you want to organize:
   /path/to/your/directory
3. The application will scan all files in the provided directory, categorize them by file type, and move them to appropriate folders.

## How It Works

1. The application scans the specified directory for files.
2. Each file is categorized based on its extension (e.g., .pdf, .jpg, .docx).
3. The application creates folders for each file type if they don't already exist.
4. Files are moved to their corresponding folders.
5. A summary of the organization process is displayed upon completion.

## File Categories

The application organizes files into the following default categories:

- **Documents**: pdf, docx, doc, txt, rtf, odt
- **Images**: jpg, jpeg, png, gif, bmp, svg
- **Audio**: mp3, wav, aac, flac, ogg
- **Video**: mp4, avi, mkv, mov, wmv
- **Archives**: zip, rar, 7z, tar, gz
- **Others**: Any file extension not in the above categories

## Customization

You can customize the categorization rules by modifying the appropriate classes in the `core` package.

## Troubleshooting

If you encounter any issues:

1. Check that you have sufficient permissions to read from and write to the specified directory.
2. Ensure no files are in use or locked by other applications.
3. For any application-specific errors, check the error messages for guidance.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

package com.fileorganizer.exceptions;

public class DirectoryNotFoundException extends FileOrganizerException {
    public DirectoryNotFoundException(String message) { super(message); }
    public DirectoryNotFoundException(String message, Throwable cause) { super(message, cause); }
}

package com.example.demo;

public class PhotoDTO {
    private Long id;
    private String fileName;

    public PhotoDTO(Long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }
}
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "http://localhost:3000")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            Photo photo = photoService.storePhoto(file);
            return ResponseEntity.ok("Photo uploaded successfully: " + photo.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Could not upload the photo: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) {
        Photo photo = photoService.getPhoto(id);
        if (photo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(photo.getFileType()))
            .body(photo.getData());
    }

    @GetMapping("/all")
    public ResponseEntity<List<PhotoDTO>> getAllPhotos() {
        List<Photo> photos = photoService.getAllPhotos();
        List<PhotoDTO> photoDTOs = photos.stream()
            .map(photo -> new PhotoDTO(photo.getId(), photo.getFileName()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(photoDTOs);
    }
}

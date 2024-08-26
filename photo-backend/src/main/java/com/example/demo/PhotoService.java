package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @SuppressWarnings("null")
    public Photo storePhoto(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : "unknown");
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setFileType(file.getContentType());
        photo.setData(file.getBytes());
        return photoRepository.save(photo);
    }

    public Photo getPhoto(Long id) {
        return photoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Photo not found"));
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}
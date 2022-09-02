package com.eventusapp.eventus.services;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static sun.plugin.security.JDK11ClassFileTransformer.init;

@Service
public class FileManagerService {
    //cls@Value("${upload.path}") //not working
    private final String imageUploadPath = ".\\image-uploads";

    private String getFileExtension(String filename) {
        if (filename == null) {
            return null;
        }
        String[] parts = filename.split("\\.");
        return parts[parts.length - 1];
    }

    public String generateNewFileName(String filename){
        UUID uuid=UUID.randomUUID(); //Generates random uuid that will be used for a random and unique file name
        String generatedName = uuid.toString();
        return String.format("%s.%s",generatedName,getFileExtension(filename));
    }

    public String saveFile(MultipartFile file){
        /**
         * Saves a provided file to the application file storage directory.
         * @param  File Accepts a Multipart file object.
         * @return String UUID generated file name.
         */
        String newFileName = generateNewFileName(file.getOriginalFilename());
        try {
            Path root = Paths.get(imageUploadPath);
            if (!Files.exists(root)) {
                init();
            }
            Files.copy(file.getInputStream(), root.resolve(newFileName));
            return newFileName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Resource load(String filename) throws IOException {
        try {
            Path file = Paths.get(imageUploadPath)
                    .resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
       }
        //Path file = Paths.get(imageUploadPath).resolve(filename);
        //return Files.readAllBytes(file);

    }

    public void removeFile(String filename){
        Path root = Paths.get(imageUploadPath);
        if (!Files.exists(root)) {
            init();
        }
        try {
            Files.delete( root.resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

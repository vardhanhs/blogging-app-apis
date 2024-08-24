package com.blogging_apis.blog.service.impl;

import com.blogging_apis.blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage (String path, MultipartFile file) throws IOException {

        // File name
        String fileName = file.getOriginalFilename();
        String randomId= UUID.randomUUID().toString();
        String uniqueFileName=randomId.concat(fileName.substring(fileName.lastIndexOf('.')));

        // full path
        String filePath = path + File.separator+uniqueFileName;

        // create folder if not created
        File dest = new File(path);

        if (!dest.exists()) {
           boolean created= dest.mkdirs();
        }

        // File copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return uniqueFileName;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        String filePath = path + File.separator + filename;
        InputStream is=new FileInputStream(filePath);
        return is;
    }
}

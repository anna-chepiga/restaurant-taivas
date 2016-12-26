package ua.goit.services;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ImageService {
    public String saveImage(HttpSession session, CommonsMultipartFile photo, String location, String filename) {
        String path = session.getServletContext().getRealPath("/") + location;
        String extension = photo.getOriginalFilename().split("\\.")[1];

        try {
            byte[] bytes = photo.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path
                    + File.separator + filename + "." + extension));
            stream.write(bytes);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to load your file. File should not be empty or without an extension");
        }

        return location + filename + "." + extension;
    }

    public void updateImage(CommonsMultipartFile photo, String path) {
        try {
            byte[] bytes = photo.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
            stream.write(bytes);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to load your file. File should not be empty or without an extension");
        }
    }
}

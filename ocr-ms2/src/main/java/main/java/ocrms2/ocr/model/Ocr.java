package main.java.ocrms2.ocr.model;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

public class Ocr {
    private MultipartFile Image;
    private String DestinationLanguage;
    public Ocr() {
    }

    public Ocr(MultipartFile image, String destinationLanguage) {
        Image = image;
        DestinationLanguage = destinationLanguage;
    }

    public MultipartFile getImage() {
        return Image;
    }

    public void setImage(MultipartFile image) {
        Image = image;
    }

    public String getDestinationLanguage() {
        return DestinationLanguage;
    }

    public void setDestinationLanguage(String destinationLanguage) {
        DestinationLanguage = destinationLanguage;
    }
}

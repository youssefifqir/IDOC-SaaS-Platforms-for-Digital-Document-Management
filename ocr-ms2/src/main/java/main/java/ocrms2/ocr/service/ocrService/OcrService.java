package main.java.ocrms2.ocr.service.ocrService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface OcrService {
    public String extractTextFromImage(MultipartFile image , String destinationLanguage);
    public String extractTextFromPDF(MultipartFile pdfFile) throws IOException;

}

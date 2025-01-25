package main.java.ocrms2.ocr.ws;

import main.java.ocrms2.ocr.service.ocrService.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ocr")
public class OcrWs {

    /*@PostMapping ("/extract/img")
    public String extractTextFromImage(@RequestParam("DestinationLanguage") String destinationLanguage,
                                       @RequestParam("Image") MultipartFile image) throws IOException {
        return ocrService.extractTextFromImage(image, destinationLanguage);
    }*/
    @PostMapping("/extract/img")
    public ResponseEntity<Map<String, String>> extractTextFromImage(
            @RequestParam("DestinationLanguage") String destinationLanguage,
            @RequestParam("Image") MultipartFile image) throws IOException {
        String extractedText = ocrService.extractTextFromImage(image, destinationLanguage);
        Map<String, String> response = new HashMap<>();
        response.put("text", extractedText);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/extract/pdf")
    public String extractTextFromPDF(@RequestParam("Pdf") MultipartFile multipartFile) throws IOException {
        return ocrService.extractTextFromPDF(multipartFile);
    }

    @Autowired
    private OcrService ocrService;


}

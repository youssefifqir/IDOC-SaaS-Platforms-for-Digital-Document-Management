package main.java.ocrms2.ocr.service.ocrServiceImpl;

import main.java.ocrms2.ocr.model.Ocr;
import main.java.ocrms2.ocr.service.ocrService.OcrService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class OcrServiceImpl implements OcrService {
    @Override
    public String extractTextFromImage(MultipartFile image, String destinationLanguage) {
        Ocr request = new Ocr();
        request.setDestinationLanguage(destinationLanguage);
        request.setImage(image);

        ITesseract instance = new Tesseract();

        try {

            BufferedImage in = ImageIO.read(convertMutilpartFileToFile(image));

            BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = newImage.createGraphics();
            g.drawImage(in, 0, 0, null);
            g.dispose();

            instance.setLanguage(request.getDestinationLanguage());
            instance.setDatapath("C:\\Users\\youssef\\Desktop\\TP5\\junior\\ocr-ms2\\tessdata");

            String result = instance.doOCR(newImage);

            return result;

        } catch (TesseractException | IOException e) {
            System.err.println(e.getMessage());
            return "Error while reading image";
        }
    }

    public static File convertMutilpartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public String extractTextFromPDF(MultipartFile pdfFile) throws IOException {
        StringBuilder textBuilder = new StringBuilder();

        try (PDDocument document = PDDocument.load(pdfFile.getInputStream())) {
            PDFTextStripper textStripper = new PDFTextStripper();

            int numberOfPages = document.getNumberOfPages();
            for (int i = 0; i < numberOfPages; i++) {
                textStripper.setStartPage(i + 1);
                textStripper.setEndPage(i + 1);

                String pageText = textStripper.getText(document);
                textBuilder.append(pageText).append("\n");
            }
        }

        return textBuilder.toString();
    }

}










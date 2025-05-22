package com.AffordMed.Problem2.Controller;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/ocr")
public class OCRController {

    @PostMapping("/extract")
    public String extractText(@RequestParam("file") MultipartFile file) throws IOException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata"); 
        tesseract.setLanguage("eng");

        File temp = File.createTempFile("upload", ".png");
        file.transferTo(temp);

        try {
            return tesseract.doOCR(temp);
        } catch (TesseractException e) {
            return "Error while reading image: " + e.getMessage();
        } finally {
            temp.delete();
        }
    }
}

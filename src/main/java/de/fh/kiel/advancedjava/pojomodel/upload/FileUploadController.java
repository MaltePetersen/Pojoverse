package de.fh.kiel.advancedjava.pojomodel.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * In order to upload class and jar files you may choose to either encode binary data in base64
 * or instead upload this jar's / classes using a form. This class shows you the server side - the "client"
 * part is located in the upload.html file. In order to upload a file you have to start the application
 * and hit http://localhost:8080/upload and choose a file for upload and upload it. This class is only an example
 * and not feature complete.
 */
@Controller
public class FileUploadController {

	Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@GetMapping("/upload")
	public String listUploadedFiles(Model model) throws IOException {

		return "upload";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestPart(value = "file", required = true)  MultipartFile file, RedirectAttributes redirectAttributes) {

		logger.info(file.getOriginalFilename());

		return "redirect:/upload";
	}
}
package in.rk.learn.web.java.springbootrest.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class FileUpload {

	@RequestMapping(value = "/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		FileOutputStream fileOutputStream = null;
		System.out.println("calling file upload");
		try {
			String tmpFolder = System.getenv("tmp");
			File uploadedFilePath = new File(tmpFolder + "/" + file.getOriginalFilename());
			System.out.println("saving the file - "+uploadedFilePath);
			uploadedFilePath.createNewFile();
			fileOutputStream = new FileOutputStream(uploadedFilePath);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
			return "File is upload successfully";
		} finally {
			fileOutputStream.close();
		}
	}
}

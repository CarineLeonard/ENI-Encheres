package fr.eni.encheres.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {
	
    @Value("${upload.dir}")
    private String uploadDir;

	@ResponseBody
	@RequestMapping(value = "/upload/{fileName:.*}", method = RequestMethod.GET)
	public byte[] serveFile(@PathVariable("fileName") String fileName) throws IOException {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
	    
	    File dir = new File(uploadDir);
    	if (dir.exists()) {
		    for(File file : dir.listFiles()) {
	        	if(file.getName().equals(fileName)) {
					FileInputStream fileInputStream = new FileInputStream(file);
					outputStream.write(fileInputStream);
		        }
		    }
    	}
	    
    	outputStream.close();
	    return outputStream.toByteArray();
	} 
	
}

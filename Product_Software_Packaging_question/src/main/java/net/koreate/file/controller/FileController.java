package net.koreate.file.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.koreate.common.util.FileUtils;

@RestController
public class FileController {

	@Inject
	ServletContext context;

	@PostMapping("/uploadFile")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile[] files){
		ResponseEntity<Object> entity = null;
		  List<String> list;
		try {
			list = FileUtils.getInstance(context).uploadFile(files);
			entity = new ResponseEntity<>(list, HttpStatus.CREATED);
		} catch (IOException e) {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type","text/plain;charset=utf-8");
			entity = new ResponseEntity<>(e.getMessage(),header,HttpStatus.BAD_REQUEST);
		} 
		  return entity;
	}

	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws IOException {
		return new ResponseEntity<byte[]>(
				FileUtils.getInstance(context).getBytes(fileName),
				FileUtils.getInstance(context).getHeader(fileName), HttpStatus.OK);
	}

	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName) throws IOException {
		return new ResponseEntity<>(
					FileUtils.getInstance(context).deleteFile(fileName),
					HttpStatus.OK
				);
	}
	
	@PostMapping("/deleteAllFiles")
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") List<String> files){
		return new ResponseEntity<>(
					FileUtils.getInstance(context).deleteAllFiles(files),
					HttpStatus.OK
				);
	}
}
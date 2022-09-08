package net.koreate.common.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import net.koreate.file.utils.FileUtils;

@RestController
@RequiredArgsConstructor
public class FileController {
	
	private final String uploadFolder;
	
	private final ServletContext context;
	
	private String realPath;
	
	@PostConstruct
	public void initPath() {
		realPath = context.getRealPath(File.separator + uploadFolder);
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println("File Controller init");
	}
	
	@PostMapping("/uploadfile")
	public ResponseEntity<Object> uploadFile(
			List<MultipartFile> file) {
		List<String> fileList = new ArrayList<>();
		
		ResponseEntity<Object> entity = null;
		try {
			for(MultipartFile f : file) {
				String uploadedName = FileUtils.uploadFile(realPath, f);
				fileList.add(uploadedName);
			}			// CREATED : 리소스 정상적으로 전달해줬다는 상태코드  | 201
			entity = new ResponseEntity<>(fileList, HttpStatus.CREATED); 
		} catch (Exception e) {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "text/plain;charset=utf-8");
			entity = new ResponseEntity<Object>("파일 업로드 실패",header, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(
			String fileName) throws Exception{
		byte[] bytes = FileUtils.getBytes(realPath, fileName);
		HttpHeaders header = FileUtils.getOctetHeaders(fileName);
		return new ResponseEntity<byte[]>(bytes, header, HttpStatus.OK);
	}
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> delteFile(
			String fileName) throws Exception{
		boolean isDeleted = FileUtils.deleteFile(realPath, fileName);
		
		if(isDeleted) {
			return new ResponseEntity<>("DELETED", HttpStatus.OK);
		}
		return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/deleteAllFiles")
	public ResponseEntity<String> deleteAllFiles(
			@RequestParam("files[]") String files[]
			) throws Exception{
		boolean isDeleted = false;
		for(String file : files) {
			System.out.println("deleteAllFiles : " + file);
			isDeleted = FileUtils.deleteFile(realPath, file);
		}
		if(isDeleted) {
			return new ResponseEntity<>("DELETE",HttpStatus.OK);
		}
		return new ResponseEntity<>("FAILED",HttpStatus.OK);
	}
	
}

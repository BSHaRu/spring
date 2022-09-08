package net.koreate.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	ServletContext context;

	String uploadPath;

	private static FileUtils utils;

	private FileUtils() {}

	private FileUtils(ServletContext context) {
		this.context = context;
		createUploadPath();
	}

	public static FileUtils getInstance(ServletContext context) {
		if (utils == null) {
			utils = new FileUtils(context);
		}
		return utils;
	}

	public void createUploadPath() {
		uploadPath = context.getRealPath(File.separator + "upload");
		File file = new File(uploadPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	public String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

	public List<String> uploadFile(MultipartFile[] files) throws IOException{
		List<String> fileList = new ArrayList<>();
		try {
			for (MultipartFile file : files) {
				String fileName = uploadFile(file);
				System.out.println(fileName);
				fileList.add(fileName);
			}
		} catch (IOException e) {
			deleteAllFiles(fileList);
			throw new IOException("형식이 올바르지 않은 파일이 포함되어 있습니다.");
		}

		return fileList;
	}

	public String uploadFile(MultipartFile file) throws IOException {
		String originalName = file.getOriginalFilename();
		byte[] fileData = file.getBytes();
		String uploadFileName = "";
		UUID uuid = UUID.randomUUID();
		String savedName = uuid.toString().replace("-", "") + "_" + originalName;
		String uploadFolderPath = getFolder();
		File uploadFolder = new File(uploadPath, uploadFolderPath);
		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}
				
		File f = new File(uploadPath + File.separator + uploadFolderPath, savedName);

		FileCopyUtils.copy(fileData, f);

		uploadFileName = makeFileUploadName(uploadFolderPath, savedName);

		return uploadFileName;
	}

	public String makeFileUploadName(String calcPath, String savedName) throws IOException {

		String ex = FilenameUtils.getExtension(savedName);
		
		String thumnail = uploadPath  + File.separator +  calcPath + File.separator + savedName;
		if (MediaUtils.getMediaType(ex) != null) {

			try {
				BufferedImage fileImage = ImageIO.read(new File(uploadPath  + File.separator +  calcPath, savedName));

				BufferedImage sourceImage = Scalr.resize(fileImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

				thumnail = uploadPath  + File.separator +  calcPath + File.separator + "s_" + savedName;

				File file = new File(thumnail);
				ImageIO.write(sourceImage, ex, file);
			} catch (Exception e) {
				e.printStackTrace();
				deleteFile(File.separator + calcPath+File.separator+savedName);
				throw new IOException();
			}
		}
		thumnail = thumnail.substring(uploadPath.length()).replace(File.separatorChar, '/');
		return thumnail;
	}

	public byte[] getBytes(String fileName) throws IOException {
		InputStream in = null;
		String path = uploadPath + (fileName).replace('/', File.separatorChar);
		byte[] fileData = null;
		try {
			in = new FileInputStream(path);
			fileData = IOUtils.toByteArray(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return fileData;
	}

	public HttpHeaders getHeader(String fileName) {

		HttpHeaders header = null;
		try {
			header = new HttpHeaders();

			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

			MediaType mType = MediaUtils.getMediaType(formatName);

			if (mType != null) {
				header.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				header.add("content-disposition",
						"attachment;fileName=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return header;
	}

	public String deleteFile(String fileName) {

		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		MediaType mType = MediaUtils.getMediaType(formatName);
		File file = null;
		if (mType != null) {
			String name = fileName.replace("s_", "");
			file = new File(uploadPath + (name).replace('/', File.separatorChar));
			file.delete();
		}

		file = new File(uploadPath + (fileName).replace('/', File.separatorChar));
		file.delete();

		return "DELETED";
	}
	
	public String deleteAllFiles(List<String> files) {
		for(String file : files) {
			String format = file.substring(file.lastIndexOf(".")+1);
			if(MediaUtils.getMediaType(format) != null) {
				String name = file.replace("s_", "");
				new File(uploadPath+(name).replace('/', File.separatorChar)).delete();
			}
			new File(uploadPath+(file).replace('/', File.separatorChar)).delete();
		}
		return "DELETED";
	}

	public void getRemoveList(List<String> list, String path) throws Exception{
		
		if(!list.isEmpty()) {
			String realPath = path.replace('/',File.separatorChar);
			
			File file = new File(uploadPath,realPath);
			
			List<File> files = Arrays.asList(file.listFiles());
			System.out.println();
			List<String> removeFiles = new ArrayList<>();
			for(File f : files) {
				String removeFilePath = path+f.getName();
				String thumnail = path+"s_"+f.getName();
				if(!list.contains(removeFilePath) && !list.contains(thumnail)) {
					removeFiles.add(removeFilePath);
				}
			}
			if(!removeFiles.isEmpty())System.out.println(deleteAllFiles(removeFiles));
		}
	}
}

package net.koreate.common.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.koreate.board.dao.AttachmentDAO;

@Component
@RequiredArgsConstructor
public class FileCheckTask {

	private final String uploadFolder;
	private final ServletContext context;
	
	private final AttachmentDAO dao;
	
	// cron schedule
	// cron : 일반적으로 유닉스에서 반복된 작업을 담당해주는 친구
	// 	-> 해당 프로젝트 task.md 내용 참고
	@Scheduled(cron="* * * * * *") // 어떻게 사용되는지만 확인하는 용도였음
	public void testTask() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()));
	}

	@Scheduled(cron="0 0 4 * * *")	// 매일 4시 0분 0초
//	@Scheduled(cron="0 * * * * *")
	public void fileCheck() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		long time = 1000 * 60 * 60 * 24;
		String datePath = sdf.format(new Date(System.currentTimeMillis()-time)); 
		datePath = datePath.replace('/', File.separatorChar); // 운영체제마다 파일 인코딩이 다르니깐 
		String realPath = context.getRealPath(File.separator+uploadFolder); // 해당 운영체제에 맞게 인코딩해줌
		
		List<String> list = dao.getTrashAttach();
		System.out.println(list);
		
		removeList(realPath, datePath, list);
	}
	
	
	// upladFolder | 년/월/일 | 테이블에 저장된 업로드 파일 리스트
	public void removeList(
			String realPath, 
			String datePath, 
			List<String> list ) {
		// 삭제할 파일 목록
		List<String> removeFiles = new ArrayList<>();
		
		// 비교할 폴더 정보 - 어제 날짜 폴더
		File file = new File(realPath, datePath);
		if(file.exists()) {
			List<File> files = Arrays.asList(file.listFiles()); // 배열로 가져오면 귀찮다고 list로 가져온데
			for(File f : files) {
				String fileName = f.getName();
				System.out.println(fileName);
				datePath = datePath.replace(File.separatorChar, '/');
				String filePath = datePath + fileName;
				String thumbnail = datePath + "s_" + fileName;
				
				// db에 저장되지 않은 파일이 존재 하면 삭제 리스트에 추가
				if(!list.contains(filePath) && !list.contains(thumbnail)) {
					removeFiles.add(filePath);
				}
			} // for end
			
			for(String s : removeFiles) {
				System.out.println("remove file : " + s);
				new File(realPath+(s).replace('/', File.separatorChar)).delete();
			}
			
		} // if end
		
	}
}

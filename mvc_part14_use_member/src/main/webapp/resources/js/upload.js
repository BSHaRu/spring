/**
 * 
 */
 	function getFileInfo(fullName){
		var imgSrc, fileName, getLink;	// 이미지 경로 | 원본 파일 이름 | 원본 파일 경로
		// 이미지 인지 아닌지 확인
		if(fullName.indexOf("s_") >= 0){
			// 이미지 파일
			imgSrc = contextPath+"/displayFile?fileName="+fullName;
			// 다운로드 요청
			getLink = contextPath+"/displayFile?fileName="+fullName.replace("s_","");
		}else{
			// 일반 파일
			imgSrc = contextPath+"/resources/img/file.png";
			// 다운로드 요청
			getLink = contextPath+"/displayFile?fileName="+fullName;
		}
		// 업로드 원본 파일 이름
		fileName = fullName.substr(fullName.lastIndexOf("_")+1);
		return {
			// key : value 의 object type으로 리턴해주고 있음
			fileName : fileName,
			imgSrc : imgSrc,
			fullName : fullName,
			getLink : getLink
		};
	}
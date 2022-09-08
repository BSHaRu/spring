/**
 *  file Upload
 */

function checkImageType(fileName){
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

function getFileInfo(fullName){
	
	var imgSrc,fileName,getLink;
	
	if(checkImageType(fullName)){
		// Image
		console.log("contextPath : " + contextPath);
		imgSrc = contextPath+"/displayFile?fileName="+fullName;
		getLink = contextPath+"/displayFile?fileName="+fullName.replace("s_","");
	}else{
		// 일반 파일
		console.log("contextPath : " + contextPath);
		imgSrc = contextPath+"/resources/img/file.png";
		getLink = contextPath+"/displayFile?fileName="+fullName;
	}
	
	fileName = fullName.substr(fullName.lastIndexOf("_")+1);
	return {fileName : fileName, imgSrc : imgSrc, fullName : fullName, getLink : getLink};
}





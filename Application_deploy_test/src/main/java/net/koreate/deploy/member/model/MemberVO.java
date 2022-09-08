package net.koreate.deploy.member.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * bigdata schema mvc_member table  
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberVO {
	
	private int num;
	@NonNull
	private String id;
	@NonNull
	private String pass;
	private String name;
	private int age;
	private String gender;
	private Date regdate;
	private Date updatedate;
	

}

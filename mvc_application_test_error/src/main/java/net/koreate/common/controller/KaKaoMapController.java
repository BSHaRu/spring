package net.koreate.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KaKaoMapController {
	
	@GetMapping("/KaKaoMap")
	public String kakaoMap() throws Exception{
		return "map/kakaoMap";
	}
}

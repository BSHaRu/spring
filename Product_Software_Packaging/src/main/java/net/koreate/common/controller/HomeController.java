package net.koreate.common.controller;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.koreate.common.util.FileUtils;

@Controller
public class HomeController {
	
	@Inject
	ServletContext context;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println(FileUtils.getInstance(context));
		return "home";
	}
	
}

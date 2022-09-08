package net.koreate.deploy.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import net.koreate.deploy.notice.model.NoticeBoard;
import net.koreate.deploy.notice.service.NoticeService;
import net.koreate.deploy.util.Criteria;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService ns;

	@GetMapping("/list")
	public void noticeList(
			Criteria cri, Model model) {
		model.addAllAttributes(ns.getList(cri));
	}
	
	@GetMapping("/write")
	public void write() {}
	
	@PostMapping("/write")
	public String write(NoticeBoard board) {
		ns.write(board);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/detail")
	public void detail(int notice_num, Model model) {
		model.addAttribute("notice",ns.read(notice_num));
	}
	
	@GetMapping("/update")
	public void update(int notice_num, Model model) {
		model.addAttribute("notice",ns.read(notice_num));
	}
	
	@PostMapping("/update")
	public String update(NoticeBoard board) {
		ns.update(board);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/delete")
	public String delete(int notice_num) {
		ns.delete(notice_num);
		return "redirect:/notice/list";
	}
	
	
	
}

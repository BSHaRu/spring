package net.koreate.notice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import net.koreate.common.utils.Criteria;
import net.koreate.notice.model.NoticeBoard;
import net.koreate.notice.service.NoticeService;

@Controller
@RequestMapping("/notice/*")
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService ns;
	
	@GetMapping("notice_list")
	public void list(Criteria cri, Model model)throws Exception{
		List<NoticeBoard> noticeList = ns.getList(cri, model);
		model.addAttribute("noticeList",noticeList);
	}
	
	@GetMapping("notice_detail")
	public void detail(int notice_num, Model model)throws Exception{
		model.addAttribute("notice",ns.read(notice_num));
	}
	
	@GetMapping("notice_write")
	public void write()throws Exception{}
	
	@PostMapping("notice_write")
	public String write(NoticeBoard board,RedirectAttributes rttr)throws Exception{
		String message = ns.write(board);
		rttr.addFlashAttribute("msg",message);
		return "redirect:/notice/notice_list";
	}
	
	@GetMapping("notice_update")
	public void update(int notice_num, Model model)throws Exception{
		model.addAttribute("notice",ns.read(notice_num));
	}
	
	@PostMapping("notice_update")
	public String update(NoticeBoard board,RedirectAttributes rttr)throws Exception{
		boolean isUpdated = ns.update(board);
		String message = "게시물 수정 성공";
		String redirectPage = "redirect:/notice/notice_detail";
		if(!isUpdated) {
			message = "게시물 수정 실패";
			redirectPage = "redirect:/notice/notice_update";
		}
		rttr.addAttribute("notice_num",board.getNotice_num());
		rttr.addFlashAttribute("msg",message);
		return redirectPage;
	}
	
	
	@GetMapping("notice_delete")
	public String delete(int notice_num,
			RedirectAttributes rttr)throws Exception{
		boolean isDeleted = ns.delete(notice_num);
		String message = "게시물 삭제 성공";
		String redirectPage = "redirect:/notice/notice_list";
		if(!isDeleted) {
			message = "게시물 삭제 실패";
			rttr.addAttribute("notice_num",notice_num);
			redirectPage = "redirect:/notice/notice_detail";
		}
		rttr.addFlashAttribute("msg",message);
		return redirectPage;
	}
	
	
}	

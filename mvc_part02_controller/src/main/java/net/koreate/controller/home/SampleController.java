package net.koreate.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.koreate.vo.ProductVO;

@Controller
public class SampleController {
	
	// void는 return 값이 없기 때문에 요청값이 view 타입의 이름이 됨
	// /WEB-INF/views/doA.jsp
	@RequestMapping("doA")
	public void doA() {
		System.out.println("doA call");
	}
	
	// /WEB-INF/views/doB.jsp
	@RequestMapping("doB")
	public void doB() {
		System.out.println("doB call");
	}
	
	@RequestMapping("doC")
	public String doC(Model model) { // Model : view 화면에 출력할 data
		model.addAttribute("msg","doC model data");
		return "result"; // model을 return 안해도 되는 이유는 DispatcherServlet이 해당 Model 정보를 제공함
						// -> 해당 메소드가 종료되어도 Model의 정보는 request에 값을 저장되어 있음.
	}
	
	@RequestMapping(value="doD", method=RequestMethod.GET)
	public String doD(			// required - true : null값 허용함 | false : null 허용 안함
			@RequestParam(name="msg", required=true) 
			String message,
			Model model) {
		System.out.println("msg : " + message);
		model.addAttribute("msg", message);
		return "result";
	}
	
	@RequestMapping(value="doD", method=RequestMethod.POST)
	public String doD(
			String msg, // request.getParameter("msg");
			int age,	//  request.getParameter("age") -> int age = Integer.parseInt("age");
			Model model) {		// -> 변수명과 param name이 동일하면 @생략 가능 
		System.out.println(msg);
		System.out.println(age);
		model.addAttribute("msg", msg+" : "+age);
		return "result";
	}
	
	@RequestMapping("product")
	public void product(Model model) {
		ProductVO productVO = new ProductVO("TV", 100);
		model.addAttribute("product",productVO);
		
		ProductVO vo = new ProductVO("AUDIO", 50);
		model.addAttribute(vo);
	}
	
	@RequestMapping("doH")
	public ModelAndView doH() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(new ProductVO("Sample1",10000)); // key 값이 없어도 첫문자를 소문자로 전달 가능 -> productVO
								
		ProductVO product = new ProductVO("Sample2",30000);
		mav.addObject("product",product);
		
		mav.setViewName("product");
		return mav;
	}
	
	@RequestMapping(value="productWrite", method=RequestMethod.POST)
	public ModelAndView productWrite(
			String name,
			int price,
			ModelAndView mav, // ModelAndView mav = new ModelAndView(); 해준거랑 같음
			ProductVO vo	// 필드 이름과 변수이름이 같으면 알아서 set 해줌
			) {				//-> <jsp:useBean id="productVO" class="ProductVO">
							// <jsp:setProperty property="*" name="productVO"/> 해준거랑 같음?
		mav.addObject(new ProductVO(name,price));
		mav.addObject("product",vo);
		mav.setViewName("product");
		
		return mav;
	}
	
	@RequestMapping("redirect")
	public String redirect() {
		return "redirect:main.home";
	}
}










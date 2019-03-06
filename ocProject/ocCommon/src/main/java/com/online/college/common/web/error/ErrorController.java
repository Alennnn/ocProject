package com.online.college.common.web.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@RequestMapping("/101")
	public ModelAndView error101(){
		return new ModelAndView("error/101");
	}
	
	@RequestMapping("/403")
	public ModelAndView error403(){
		return new ModelAndView("error/403");
	}

	@RequestMapping("/404")
	public ModelAndView error404(){
		return new ModelAndView("error/404");
	}
	
	@RequestMapping("/500")
	public ModelAndView error500(){
		return new ModelAndView("error/500");
	}
	
	@RequestMapping("/405")
	public ModelAndView error405(){
		return new ModelAndView("error/405");
	}
	
}

package com.aws.cmpe281.Project1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class loginController {
	    @RequestMapping( method=RequestMethod.GET, value="/login")
	    public String login(){
	    	return "login";
	    }
}

package com.aws.cmpe281.Project1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class webController {
	@RequestMapping(value="/")
    public String homepage(){
        return "index";
    }
}



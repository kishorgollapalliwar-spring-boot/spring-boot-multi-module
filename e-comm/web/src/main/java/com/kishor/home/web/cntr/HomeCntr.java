package com.kishor.home.web.cntr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCntr {

	@GetMapping(path = {"/", "/home"})
	public String home() {
		return "index";
	}
}

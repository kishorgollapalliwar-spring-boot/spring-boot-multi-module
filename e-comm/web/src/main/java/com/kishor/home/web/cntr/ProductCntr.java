package com.kishor.home.web.cntr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductCntr {
	@GetMapping(path = {"/products"})
	public String products() {
		return "products";
	}
}

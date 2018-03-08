package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("refreshController")
public class RefreshController {
	
	@GetMapping("/")
	public String root() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/home")
	public String home() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/register")
	public String register() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/pending")
	public String pending() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/reset-password")
	public String resetPassword() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/reservation/history")
	public String reservationHistory() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/flight/details")
	public String flightDetails() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/checkin")
	public String checkin() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/feedback")
	public String feedback() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/reservation/confirm")
	public String reservationConfirm() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/flightsearch")
	public String flightsearch() {
		return "redirect:/refresh/index.html";
	}
	
	
	
	@GetMapping("/admin/users")
	public String adminUsers() {
		return "redirect:/refresh/index.html";
	}
	
	@GetMapping("/admin/feedback")
	public String adminFeedback() {
		return "redirect:/refresh/index.html";
	}

}

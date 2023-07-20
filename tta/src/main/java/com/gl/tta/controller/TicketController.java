package com.gl.tta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.tta.model.Ticket;
import com.gl.tta.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping(value = "/tickets")
	public String listTickets(Model model) {

		List<Ticket> ticketList = ticketService.listAllTickects();
		model.addAttribute("tickets", ticketList);

		return "tickets";

	}

	@GetMapping("/tickets/new")
	public String addTicketStep1(Model model) {

		Ticket ticket = new Ticket();

		model.addAttribute("ticket", ticket);

		return "add_ticket";
	}

	@PostMapping("/tickets")
	public String addTicketStep2(@ModelAttribute("ticket") Ticket ticket) {

		ticketService.saveTicket(ticket);
		return "redirect:/tickets";
	}

	@GetMapping("/tickets/edit/{id}")
	public String updateTicketStep1(@PathVariable Long id, Model model) {

		Ticket existingTicketObj = ticketService.getTicketDetails(id);

		model.addAttribute("ticket", existingTicketObj);
		return "update_ticket";
	}

	@PostMapping("/tickets/{id}")
	public String updateTicketStep2(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticketObjWithUserValues) {

		Ticket existingTicketObj = ticketService.getTicketDetails(id);

		existingTicketObj.setTicketTitle(ticketObjWithUserValues.getTicketTitle());
		existingTicketObj.setTicketShortDescription(ticketObjWithUserValues.getTicketShortDescription());
		existingTicketObj.setTicketContent(ticketObjWithUserValues.getTicketContent());

		ticketService.saveTicket(existingTicketObj);
		return "redirect:/tickets";
	}

	@GetMapping("/tickets/view/{id}")
	public String viewTicket(@PathVariable Long id, Model model) {

		Ticket existingTicketObj = ticketService.getTicketDetails(id);

		model.addAttribute("ticket", existingTicketObj);
		return "show_ticket";
	}

	@GetMapping("/tickets/{id}")
	public String deleteTicket(@PathVariable Long id) {

		ticketService.deleteTicket(id);
		return "redirect:/tickets";
	}

	@RequestMapping("/tickets/search")
	public String searchTickets(@RequestParam(value = "ticketTitle") String ticketTitle,
			@RequestParam(value = "ticketShortDescription") String ticketShortDescription, Model model) {

		if (ticketTitle.trim().isEmpty() && ticketShortDescription.trim().isEmpty()) {
			return "redirect:/tickets";
		} else {
			List<Ticket> tickets = ticketService.searchBy(ticketTitle, ticketShortDescription);
			model.addAttribute("tickets", tickets);
			return "tickets";
		}

	}

}

package com.gl.tta.service;

import java.util.List;

import com.gl.tta.model.Ticket;

public interface TicketService {

	List<Ticket> listAllTickects();

	void saveTicket(Ticket ticket);

	Ticket getTicketDetails(Long id);

	void deleteTicket(Long id);

	List<Ticket> searchBy(String ticketTitle, String ticketShortDescription);

}

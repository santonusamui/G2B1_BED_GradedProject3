package com.gl.tta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.tta.dao.TicketDao;
import com.gl.tta.model.Ticket;
import com.gl.tta.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDao ticketDao;

	@Override
	public List<Ticket> listAllTickects() {

		return ticketDao.findAll();
	}

	@Override
	public void saveTicket(Ticket ticket) {
		ticketDao.save(ticket);

	}

	@Override
	public Ticket getTicketDetails(Long id) {
		return ticketDao.findById(id).get();

	}

	@Override
	public void deleteTicket(Long id) {
		ticketDao.deleteById(id);

	}

	@Override
	public List<Ticket> searchBy(String ticketTitle, String ticketShortDescription) {

		return ticketDao.findByTicketTitleContainsIgnoreCaseAndTicketShortDescriptionContainsIgnoreCase(ticketTitle,
				ticketShortDescription);
	}

}
package com.gl.tta.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.tta.model.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Long> {

	List<Ticket> findByTicketTitleContainsIgnoreCaseAndTicketShortDescriptionContainsIgnoreCase(String ticketTitle,
			String ticketShortDescription);

}

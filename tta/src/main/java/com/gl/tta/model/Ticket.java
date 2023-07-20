package com.gl.tta.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "Ticket_Title")
	private String ticketTitle;

	@Column(name = "Ticket_Short_Description")
	private String ticketShortDescription;

	@Column(name = "Ticket_Created_On", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date ticketCreatedOn;

	@PrePersist
	private void onCreate() {
		ticketCreatedOn = new Date();

	}

	@Column(name = "Ticket_Content")
	private String ticketContent;

}

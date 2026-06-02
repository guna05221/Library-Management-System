package com.lms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_generator")
	@SequenceGenerator(name = "book_id_generator", initialValue = 201, allocationSize = 1)
	private int BookId;
	private String title;
	private String author;
	private boolean borrowed;
	private LocalDateTime borrowedTime;
	private LocalDateTime returnTime;

	//many book can borrowed by one user
	@ManyToOne
	private User user;
}

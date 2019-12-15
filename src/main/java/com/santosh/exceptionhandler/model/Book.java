package com.santosh.exceptionhandler.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book {

	private Long id;
	@NotEmpty(message = "First name is required")
	private String name;
	@NotNull(message = "Price must be valid")
	private BigDecimal price;

	public Book() {
		super();
	}

	public Book(Long id, String name, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Book(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}

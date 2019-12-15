package com.santosh.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.santosh.model.Book;

@Repository
public class JdbcBookRepository implements BookRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from books", Integer.class);
	}

	@Override
	public int save(Book book) {
		return jdbcTemplate.update("insert into books (name, price) values(?,?)", book.getName(), book.getPrice());
	}

	@Override
	public int update(Book book) {
		return jdbcTemplate.update("update books set price = ? where id = ?", book.getPrice(), book.getId());
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update("delete books where id = ?", id);
	}

	@Override
	public List<Book> findAll() {
		return jdbcTemplate.query("select * from books", new BookRowMapper());
	}

	// jdbcTemplate.queryForObject, populates a single object
	@Override
	public Book findById(Long id) {
		return jdbcTemplate.queryForObject("select * from books where id = ?", new Object[] { id },
				(rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price")));
	}

	@Override
	public List<Book> findByNameAndPrice(String name, BigDecimal price) {
		return jdbcTemplate.query("select * from books where name like ? and price <= ?",
				new Object[] { "%" + name + "%", price }, new BookRowMapper());
	}

	@Override
	public String getNameById(Long id) {
		return jdbcTemplate.queryForObject("select name from books where id = ?", new Object[] { id }, String.class);
	}

	class BookRowMapper implements RowMapper<Book> {
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book employee = new Book();
			employee.setId(rs.getLong("id"));
			employee.setName(rs.getString("name"));
			employee.setPrice(rs.getBigDecimal("price"));
			return employee;
		}
	}

}

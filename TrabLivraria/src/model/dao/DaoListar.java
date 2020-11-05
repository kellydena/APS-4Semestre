package model.dao;

import java.util.Collection;

import entities.*;

public interface DaoListar {
	
	public Collection<Book> listarLivro();	
	public Collection<Publisher> listarEditora();
	public Collection<Author> listarAutor();
	public Collection<BooksAuthors> listarAutorLivro();
	
}

package model.dao;

import entities.Author;
import entities.Book;
import entities.BooksAuthors;
import entities.Publisher;

public interface DaoAlterar {
	
	public void alterarLivro(Book livro);
	public void alterarAutor(Author autor);
	public void alterarEditora(Publisher editora);
	public void alterarBooksAuthors(BooksAuthors ba);

}


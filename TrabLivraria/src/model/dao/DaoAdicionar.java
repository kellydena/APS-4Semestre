package model.dao;

import entities.*;

public interface DaoAdicionar {
	
	public void adicionarLivro(Book livro);
	public void adicionarAutor(Author autor);
	public void adicionarEditora(Publisher editora);
	public void adicionaBookAuthor(BooksAuthors ba);

}

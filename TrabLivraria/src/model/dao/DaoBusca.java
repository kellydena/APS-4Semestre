package model.dao;

import java.util.ArrayList;

import entities.Author;
import entities.Book;
import entities.BookAndPublisher;
import entities.BooksANDAuthors;
import entities.BooksAuthors;
import entities.Publisher;


public interface DaoBusca {
		
	public ArrayList<Book> buscaLivros(String title);
	public Book buscaLivroPorISBN(String isbn);
	public ArrayList<Publisher> buscaEditora (String nome);
	public ArrayList<Author> buscaAutor (String nome, String sobrenome);
	public ArrayList<BooksANDAuthors> buscaAutorANDLivro(String titulo, String nomeAutor);
	public ArrayList<BookAndPublisher> buscaLivroComEditora(String isbn);
	public ArrayList<BooksAuthors> buscaLivrosAutores(int author_id);
	public ArrayList<Book> buscaLivroPorEditora(Publisher editora);
}

package model.dao;

import java.util.ArrayList;

import entities.Author;
import entities.Book;
import entities.PublisherWithBook;
import entities.AuthorWithBook;
import entities.BooksAuthors;
import entities.Publisher;


public interface DaoBusca {
		
	public ArrayList<Book> buscaLivros(String title);
	public Book buscaLivroPorISBN(String isbn);
	public ArrayList<Publisher> buscaEditora (String nome);
	public ArrayList<Author> buscaAutor (String nome, String sobrenome);
	public ArrayList<AuthorWithBook> buscaAuthorWithBook(String titulo, String nomeAutor);
	public ArrayList<PublisherWithBook> buscaLivroComEditora(String isbn, String editora);
	public ArrayList<BooksAuthors> buscaLivrosAutores(int author_id);
	public ArrayList<Book> buscaLivroPorEditora(Publisher editora);
}

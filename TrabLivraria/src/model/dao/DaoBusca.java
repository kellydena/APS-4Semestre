package model.dao;

import java.util.ArrayList;
import java.util.Collection;

import entities.Author;
import entities.BooksAuthors;
import entities.Book;
import entities.BooksANDAuthors;
import entities.Publisher;


public interface DaoBusca {
		
	public ArrayList<Book> buscaLivros(String title);
	public ArrayList<Publisher> buscaEditora (String nome);
	public ArrayList<Author> buscaAutor (String nome, String sobrenome);
	public ArrayList<BooksANDAuthors> buscaAutorANDLivro(String titulo, String nomeAutor);

}

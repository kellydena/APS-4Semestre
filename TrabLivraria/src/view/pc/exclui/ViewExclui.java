package view.pc.exclui;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Author;
import entities.Book;
import entities.Publisher;

public interface ViewExclui {

	public void setAutores(ArrayList<Author> autores);
	public void setEditoras(ArrayList<Publisher> editoras);
	public void setLivros(ArrayList<Book> livros);
	
	public void addSubmitBehavior(ActionListener al);
	
	public Author getAutor();
	public Publisher getEditora();
	public Book getLivro();
	
	public Object getComboBoxSelected();
	public void disposeFrame();
}

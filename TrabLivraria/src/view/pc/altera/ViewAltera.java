package view.pc.altera;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Author;
import entities.Book;
import entities.Publisher;

public interface ViewAltera {
	
	public void setAutores(ArrayList<Author> a);
	public void setEditoras(ArrayList<Publisher> p);
	public void setLivros(ArrayList<Book> b);
	
	public Author getAuthor();
	public String getFirstName();
	public String getLastName();
	
	public Publisher getPublisher();
	public String getEditoraName();
	public String getUrl();
	
	public Book getBook();
	public String getTitleBook();
	public double getPrice();
	public Publisher getPublisherBook();
	public ArrayList<Author> getAuthorsBook();
	
	public void addSubmitBehavior(ActionListener al);
	public Object getComboBoxSelected();
	public void disposeFrame();
	
}

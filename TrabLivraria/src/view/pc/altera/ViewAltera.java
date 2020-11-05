package view.pc.altera;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Author;
import entities.Publisher;

public interface ViewAltera {
	
	public void setAutores(ArrayList<Author> a);
	public void setEditoras(ArrayList<Publisher> p);
	
	public void addSubmitBehavior(ActionListener al);
	
	public Object getComboBoxSelected();
	
	public void setAutoresEscolherApenasUm(ArrayList<Author> a);
	public void addVerificaNomeBehavior(ActionListener al);
	public Author getAuthor();
	public String getFirstName();
	public String getLastName();
	
	public String getEditoraName();
	public String getUrl();
	
	public String getISBN();
	public String getTitleBook();
	public double getPrice();
	public Publisher getPublisherBook();
	public ArrayList<Author> getAuthorsBook();
	
	
}

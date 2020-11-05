package view.pc.inclui;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Author;
import entities.Publisher;

public interface ViewInclui {

	public void setAutores(ArrayList<Author> a);
	public void setEditoras(ArrayList<Publisher> p);

	public void addSubmitBehavior(ActionListener al);
	
	public Object getComboBoxSelected();
	
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

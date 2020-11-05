package view.pc.busca;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import entities.Author;
import entities.Book;
import entities.Publisher;

public interface ViewBusca{

	public void addSubmitBehavior(ActionListener al);
	
	public void mostrarListaEditora(ArrayList<Publisher> editoras);
	public void mostrarListaAutor(ArrayList<Author> autores);
	public void mostrarListaLivro(ArrayList<Book> livros);
	
	public String getJRadioButton();
	
	public String getJTextFieldText();
}

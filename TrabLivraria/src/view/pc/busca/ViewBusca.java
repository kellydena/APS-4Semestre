package view.pc.busca;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Author;
import entities.AuthorWithBook;
import entities.Publisher;
import entities.PublisherWithBook;

public interface ViewBusca{

	public void addSubmitBehavior(ActionListener al);
	
	public void mostrarListaEditora(ArrayList<Publisher> editoras);
	public void mostrarListaAutor(ArrayList<Author> autores);
	public void mostrarListaLivro(ArrayList<PublisherWithBook> livros);
	public void mostrarListaAuthorWithBook(ArrayList<AuthorWithBook> autorComLivro);
	public void mostarListaEditoraComLivros(ArrayList<PublisherWithBook> editoraComLivro);
	
	public String getJRadioButton();
	
	public String getJTextFieldText();


}

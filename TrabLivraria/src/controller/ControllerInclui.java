package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Author;
import entities.Book;
import entities.BooksAuthors;
import entities.Publisher;
import model.dao.DaoAdicionar;
import model.dao.DaoBusca;
import view.pc.inclui.ViewInclui;
import view.pc.util.messages.FrameMessage;
import view.pc.util.messages.FrameMessage2;

public class ControllerInclui {

	ViewInclui view;
	DaoAdicionar daoInclui;
	DaoBusca daoBusca;
	
	public ControllerInclui(ViewInclui viewInclui, DaoAdicionar daoInclui, DaoBusca daoBusca) {
	
		this.view = viewInclui;
		this.daoInclui = daoInclui;
		this.daoBusca = daoBusca;		
		init();
	}
	
	public void init(){
		view.setAutores(daoBusca.buscaAutor("", ""));
		view.setEditoras(daoBusca.buscaEditora(""));
		view.addSubmitBehavior(new submitBehavior());
	}
	
	class submitBehavior implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object texto = view.getComboBoxSelected();
			boolean doesAlreadyExists = false;
			if(texto.equals("Editoras")) {
				String nome = view.getEditoraName();
				String url = view.getUrl();
				for(Publisher p : daoBusca.buscaEditora("")) {
					if(p.getName().equals(nome))
						doesAlreadyExists = true;
				}
				if(nome.equals("") || url.equals("")) { //Caso o usuario nao tenha preenchido todos os campos
					new FrameMessage2();
				} else if(doesAlreadyExists) { //Caso o item ja exista no banco de dados
					new FrameMessage("editora");
				} else{
					daoInclui.adicionarEditora(new Publisher(0, view.getEditoraName(), view.getUrl()));
				}
			} else if(texto.equals("Autores")) {
				String primeiroNome = view.getFirstName();
				String ultimoNome = view.getLastName();
				for(Author a : daoBusca.buscaAutor("", "")) {
					if(a.getFname().equals(primeiroNome) && a.getName().equals(ultimoNome))
						doesAlreadyExists = true;
				}
				if(primeiroNome.equals("") || ultimoNome.equals("")) {
					new FrameMessage2();
				} else if(doesAlreadyExists) {
					new FrameMessage("autor(a)");
				} else{
					daoInclui.adicionarAutor(new Author(0, view.getFirstName(), view.getLastName()));
				}
			} else if(texto.equals("Livros")) {
				String ISBN = view.getISBN();
				String bookName = view.getTitleBook();
				Double price = view.getPrice();
				Publisher editora = view.getPublisherBook();
				ArrayList<Author> autores = view.getAuthorsBook();
				for(Book b : daoBusca.buscaLivros("")) {
					if(b.getIsbn().equals(ISBN))
						doesAlreadyExists = true;
				}
				if(bookName.equals("") || ISBN.equals("") || editora == null) {
					new FrameMessage2();
				} else if(doesAlreadyExists) {
					new FrameMessage("autor(a)");
				} else{
					daoInclui.adicionarLivro(new Book(bookName, ISBN, editora.getId(), price));
					for(int i= 0; i < autores.size(); i++) { //Adiciona quantas os autores ao livro
						daoInclui.adicionaBookAuthor(new BooksAuthors(ISBN, autores.get(i).getId(), i+1));
					}
				}
				
			}
			
		}
	}
	
	
	
}

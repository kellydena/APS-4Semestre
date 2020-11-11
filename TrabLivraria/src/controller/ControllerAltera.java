package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import entities.Author;
import entities.Book;
import entities.BooksAuthors;
import entities.Publisher;
import model.dao.DaoAdicionar;
import model.dao.DaoAlterar;
import model.dao.DaoBusca;
import model.dao.DaoExcluir;
import view.pc.altera.ViewAltera;
import view.pc.util.messages.FrameMessage2;
import view.pc.util.messages.FrameReturnToUser;

public class ControllerAltera {
	
	private ViewAltera view;
	private DaoBusca daoBusca;
	private DaoAlterar daoAltera;
	private DaoAdicionar daoAdiciona;
	private DaoExcluir daoExclui;
	
	public ControllerAltera(ViewAltera viewAltera, DaoBusca daoBusca,DaoAlterar daoAltera, DaoAdicionar daoAdiciona, DaoExcluir daoExclui) {
		this.view = viewAltera;
		this.daoBusca = daoBusca;
		this.daoAltera = daoAltera;
		this.daoAdiciona = daoAdiciona;
		this.daoExclui = daoExclui;
		init();
	}

	private void init() {
		view.addSubmitBehavior(new submitBehavior());
		view.setAutores(daoBusca.buscaAutor("", ""));
		view.setEditoras(daoBusca.buscaEditora(""));
		view.setLivros(daoBusca.buscaLivros(""));
	}

	class submitBehavior implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object tipo = view.getComboBoxSelected();
			String msg = "";
			if(tipo.equals("Livros")) {
				Book book = view.getBook();
				String title = view.getTitleBook();
				double price = view.getPrice();
				ArrayList<Author> autores = view.getAuthorsBook();
				Publisher editora = view.getPublisherBook();
				String isbn = book.getIsbn();
				
				if(book == null || title.equals("") || autores == null || editora == null ) {
					new FrameMessage2();
				} else {
					daoExclui.excluirLivroAutor(0, book.getIsbn());
					daoExclui.excluiLivro(book.getIsbn());
					daoAdiciona.adicionarLivro(new Book(title, isbn, editora.getId(), price));
					msg = "O livro " + book.getTitulo() + " de ISBN: " + book.getIsbn() +
							"\nTeve titulo alterado para " + title + 
							"\nPreco para " + price + 
							"\nEditora para " + editora.getName() +
							"\nAutores: ";		
					int i = 0;
					for(Author a : autores) {
						i++;
						daoAdiciona.adicionaBookAuthor(new BooksAuthors(book.getIsbn(), a.getId(), i));
						msg = msg + "\n" + a.getFname() + " " + a.getName();
					}
					
				}
				
			} else if(tipo.equals("Autores")) {
				Author author = view.getAuthor();
				String nome = view.getFirstName();
				String ultimoNome = view.getLastName();
				
				if(author == null || nome.equals("") || ultimoNome.equals("")) { //Caso o usuario nao tenha preenchido todos os campos
					new FrameMessage2();
				}else{
					daoAltera.alterarAutor(new Author(author.getId(), ultimoNome, nome));
					msg = "O(a) Author(a) " + author.getFname() + " " + author.getName() + "\n"
							+ "Foi alterado para " + nome + " " + ultimoNome;
				}
			} else if(tipo.equals("Editoras")) {
				Publisher editora = view.getPublisher();
				String nome = view.getEditoraName();
				String url = view.getUrl();
				if(editora == null || nome.equals("") || url.equals("")) {
					new FrameMessage2();
				} else {
					daoAltera.alterarEditora(new Publisher(editora.getId(), nome, url));
					msg = "A editora " + editora.getName() + " com url " + editora.getUrl() + "\n"
							+ "Foi alterada para " + nome + " com url " + url;
				}
			}
		//No final do alterar, caso exista mensagem, ou seja, deu tudo certo, iremos chamar uma janela com os dados alterados e em seguida um dispose
		if(!msg.equals("")) {	
			new FrameReturnToUser(msg);
			view.disposeFrame();
		}
	}
	}

}

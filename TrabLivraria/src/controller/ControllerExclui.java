package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Author;
import entities.Book;
import entities.BooksAuthors;
import entities.Publisher;
import model.dao.DaoBusca;
import model.dao.DaoExcluir;
import view.pc.exclui.ViewExclui;
import view.pc.util.list.JOptionFrameConfirmacao;
import view.pc.util.messages.FrameMessage2;
import view.pc.util.messages.FrameReturnToUser;

public class ControllerExclui {
	
	private DaoBusca daoBusca;
	private DaoExcluir daoExclui;
	private ViewExclui view;
	
	public ControllerExclui(ViewExclui view, DaoBusca daoBusca, DaoExcluir daoExclui) {
		this.view = view;
		this.daoBusca = daoBusca;
		this.daoExclui = daoExclui;
		
		init();
	}
	
	private void init() {
		view.addSubmitBehavior(new SubmitBehavior());
		view.setAutores(daoBusca.buscaAutor("", ""));
		view.setEditoras(daoBusca.buscaEditora(""));
		view.setLivros(daoBusca.buscaLivros(""));
	}
	
	class SubmitBehavior implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Object tipo = view.getComboBoxSelected();
			String msg = "";
			JOptionFrameConfirmacao confirmacao;
			
			if(tipo.equals("Livros")) {
				Book book = view.getLivro();
				if(book == null) {
					new FrameMessage2();
				} else {
					daoExclui.excluirLivroAutor(0, book.getIsbn());
					daoExclui.excluiLivro(book.getIsbn());
					msg = "O livro " + book.getTitulo() + " de ISBN: " + book.getIsbn() +
							"\nFoi excluido";						
				}
				
			} else if(tipo.equals("Autores")) {
				Author author = view.getAutor();
				if(author == null) { //Caso o usuario nao tenha preenchido todos os campos
					new FrameMessage2();
				}else{
					confirmacao = new JOptionFrameConfirmacao("Caso esse autor esteja relacionado a um livro, o livro também será excluido.\\nDeseja continuar?\"");
					if(confirmacao.getInput() == 0) {
						daoExclui.excluirLivroAutor(author.getId(), "");
						ArrayList<BooksAuthors> livrosAutores = daoBusca.buscaLivrosAutores(author.getId());
						for(BooksAuthors b : livrosAutores) { //Antes de excluir o autor, precisamos excluir as relacões do livro com autores
							daoExclui.excluiLivro(b.getISBN());
						}					
						daoExclui.excluirAutor(author.getId());
						msg = "O(a) Author(a) " + author.getFname() + " " + author.getName() + "\n"
								+ "Foi excluido(a)";
					} else {view.disposeFrame();}
				}
			} else if(tipo.equals("Editoras")) {
				Publisher editora = view.getEditora();
				if(editora == null) {
					new FrameMessage2();
				} else {
					confirmacao = new JOptionFrameConfirmacao("Caso essa editora esteja relacionado a um livro, ele também será excluido.\\nDeseja continuar?");
					if(confirmacao.getInput() == 0) {
						ArrayList<Book> livros = daoBusca.buscaLivroPorEditora(editora);
						for(Book b : livros) {
							daoExclui.excluirLivroAutor(0, b.getIsbn());
							daoExclui.excluiLivro(b.getIsbn());
						}
						//daoExclui.excluirEditora(editora.getId());
						msg = "A editora " + editora.getName() + " com url " + editora.getUrl() + "\n"
								+ "Foi excluida";
					} else {view.disposeFrame();}	
				}
			}
		//No final do excluir, caso exista mensagem, ou seja, deu tudo certo, iremos chamar uma janela com os dados excluidos e em seguida um dispose
		if(!msg.equals("")) {	
			new FrameReturnToUser(msg);
			view.disposeFrame();
		}	    
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

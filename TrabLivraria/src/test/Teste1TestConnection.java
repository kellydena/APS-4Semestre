package test;

import entities.Book;
import model.dao.DaoBusca;
import model.dao.buscar.Busca;
import view.pc.util.list.JFrameListLivros;

public class Teste1TestConnection {
	public static void main(String[] args) {
//		DaoJDBC dao = new DaoJDBC();
//		
//		dao.testConnection();
//		
//		Author felipe = new Author(5698, "Scherer", "Felipe");
//		
//		System.out.println(felipe);
		
		
		DaoBusca dao = new Busca();
		Book livroTeste = null;
		try{
			livroTeste = dao.buscaLivroPorISBN("0-201-89685-3");
			System.out.println(livroTeste.getTitulo());
		}catch(Exception e) {
			System.out.println("Livro não existe");
		}
		
		new JFrameListLivros(dao.buscaLivros(""));
		
		
	}
}

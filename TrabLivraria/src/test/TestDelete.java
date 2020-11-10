package test;

import model.dao.DaoExcluir;
import model.dao.excluir.Excluir;

public class TestDelete {
	public static void main(String[] args) {
		DaoExcluir dao = new Excluir();
		dao.excluiLivro("123-456-78");
		
	}

}

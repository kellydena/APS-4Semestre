package model.dao;

import entities.Author;
import entities.Publisher;

public interface DaoAlterar {
	//Por questoes de integridade, o banco de dados nao deixa dar update nos livros, pois eles sao
	//dependentes de uma editora, ou seja, para alterar os livros, temos que exclui-lo e em seguida
	//adicionar outro com os dados que iam ser alterados
	
	public void alterarAutor(Author autor);
	public void alterarEditora(Publisher editora);

}


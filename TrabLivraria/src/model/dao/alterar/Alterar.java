package model.dao.alterar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Author;
import entities.Book;
import entities.BooksAuthors;
import entities.Publisher;
import model.dao.DaoAlterar;

public class Alterar implements DaoAlterar{
	
	private static final String USER = "root";
    private static final String PASS = "";
    private static final String URL = "jdbc:mysql://localhost:3306/Livraria?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";{

}

	@Override
	public void alterarLivro(Book livro) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			System.out.println("Conexão Feita");
			
			final String query = "INSERT Books VALUE (?,?,?,?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, livro.getTitulo());
			pstm.setString(2, livro.getIsbn());
			pstm.setInt(3, livro.getEditoraIdFk());
			pstm.setDouble(4, livro.getPreco());
			
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterarAutor(Author autor) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			System.out.println("Conexão Feita");
			
			final String query = "UPDATE Authors SET name = (?), fname = (?) WHERE author_id = (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, autor.getName());
			pstm.setString(2, autor.getFname());
			pstm.setInt(3, autor.getId());

			
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterarEditora(Publisher editora) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarBooksAuthors(BooksAuthors ba) {
		// TODO Auto-generated method stub
		
	}
}
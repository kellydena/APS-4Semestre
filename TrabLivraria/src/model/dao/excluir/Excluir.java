package model.dao.excluir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.dao.DaoExcluir;

public class Excluir implements DaoExcluir {
	private static final String USER = "root";
    private static final String PASS = "Br@sil2020";
    private static final String URL = "jdbc:mysql://localhost:3306/Livraria?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

	@Override
	public void excluiLivro(String key) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			System.out.println("Conexão Feita");
			
			final String query = "DELETE FROM Books WHERE isbn = (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, key);
			
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}		
	

	@Override
	public void excluirAutor(int key) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			System.out.println("Conexão Feita");
			
			final String query = "DELETE FROM Authors WHERE author_id = (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setInt(1, key);
			
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void excluirEditora(int key) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			System.out.println("Conexão Feita");
			
			final String query = "DELETE FROM Publishers WHERE  publisher_id = (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setInt(1, key);
			
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}				
	}

	@Override
	public void excluirLivroAutor(int autorID, String isbn) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			System.out.println("Conexão Feita");
			
			final String query = "DELETE FROM Publishers WHERE  isbn_books_fk = (?) OR authorID_authors_fk = (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, isbn);
			pstm.setInt(2, autorID);
			
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}			
	}

}

package model.dao.excluir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import model.dao.DaoExcluir;
import model.dao.DaoUtilConnection;

public class Excluir implements DaoExcluir {
	private static String USER = "";
    private static String PASS = "";
    private static String URL = "";
    
    public Excluir() {
        Properties propri = DaoUtilConnection.readProperties("src/db.properties");
        
    	USER = propri.getProperty("USER");
        PASS = propri.getProperty("PASSWORD");
        URL = propri.getProperty("URL");
    }
    
	@Override
	public void excluiLivro(String key) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){		
			
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
			
			final String query = "DELETE FROM Publishers WHERE publisher_id = (?)";
			
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
			
			final String query = "DELETE FROM BooksAuthors WHERE isbn = (?) OR author_id = (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, isbn);
			pstm.setInt(2, autorID);
			
			pstm.execute();		
			
		}catch(SQLException e) {
			e.printStackTrace();
		}			
	}

}

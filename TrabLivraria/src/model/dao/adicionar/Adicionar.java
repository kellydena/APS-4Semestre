package model.dao.adicionar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import entities.Author;
import entities.Book;
import entities.BooksAuthors;
import entities.Publisher;
import model.dao.DaoAdicionar;
import model.dao.DaoUtilConnection;

public class Adicionar implements DaoAdicionar{
	
	private static String USER = "";
    private static String PASS = "";
    private static String URL = "";
    
    public Adicionar(){
        Properties propri = DaoUtilConnection.readProperties("src/db.properties");
        
    	USER = propri.getProperty("USER");
        PASS = propri.getProperty("PASSWORD");
        URL = propri.getProperty("URL");
	}
    
	@Override
	public void adicionarLivro(Book livro) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
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
	public void adicionaBookAuthor(BooksAuthors ba) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){

			final String query = "INSERT BooksAuthors VALUE (?,?,?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, ba.getISBN());
			pstm.setInt(2, ba.getAuthorID());
			pstm.setInt(3, ba.getSeqNo());
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void adicionarAutor(Author autor) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "INSERT Authors VALUE (?,?,?)";		
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setInt(1, autor.getId());
			pstm.setString(2, autor.getFname());
			pstm.setString(3, autor.getName());
			pstm.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void adicionarEditora(Publisher editora) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "INSERT Publishers VALUE (?,?,?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setInt(1, editora.getId());
			pstm.setString(2, editora.getName());
			pstm.setString(3, editora.getUrl());
			
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

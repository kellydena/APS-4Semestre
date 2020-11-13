package model.dao.alterar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import entities.Author;
import entities.Publisher;
import model.dao.DaoAlterar;
import model.dao.DaoUtilConnection;

public class Alterar implements DaoAlterar{
	
	private static String USER = "";
    private static String PASS = "";
    private static String URL = "";
    
    public Alterar() {
        Properties propri = DaoUtilConnection.readProperties("src/db.properties");
        
    	USER = propri.getProperty("USER");
        PASS = propri.getProperty("PASSWORD");
        URL = propri.getProperty("URL");
	}

	@Override
	public void alterarAutor(Author autor) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
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
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			
			final String query = "UPDATE Publishers SET name = (?), url = (?) WHERE publisher_id = (?)";
			
			PreparedStatement pstm = con.prepareStatement(query); 
			
			pstm.setString(1, editora.getName());
			pstm.setString(2, editora.getUrl());
			pstm.setInt(3, editora.getId());
			
			pstm.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
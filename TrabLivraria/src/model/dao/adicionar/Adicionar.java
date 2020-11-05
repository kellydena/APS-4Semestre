package model.dao.adicionar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import entities.Author;
import entities.Book;
import entities.BooksAuthors;
import entities.Publisher;
import model.dao.DaoAdicionar;

public class Adicionar implements DaoAdicionar{
	
	private static final String USER = "root";
    private static final String PASS = "";
    private static final String URL = "jdbc:mysql://localhost:3306/Livraria?autoReconnect=true&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    

	@Override
	public void adicionarLivro(Book livro) {
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
	public void adicionaBookAuthor(BooksAuthors ba) {
		try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
			System.out.println("Conexão Feita");
			
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
			System.out.println("Conexão Feita");
			
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
			System.out.println("Conexão Feita");
			
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

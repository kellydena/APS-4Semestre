package view.pc.busca.tables;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Author;
import entities.Publisher;

public class JTableAutores extends JFrame{
	JScrollPane panelScroll;
	JTable table;
	DefaultTableModel dtm;
	

	Collection<Author> autores;
	
	public JTableAutores(Collection<Author> autores) {
		super("Autores");
		
		this.autores = autores;
		
		Object[] colunas = new Object[] {"Primeiro Nome", "Ultimo Nome"};
		
		Object[][] data = new Object[autores.size()][2];
		int n = 0;
		for(Author autor: autores) {
			data[n][0] = autor.getFname();
			data[n][1] = autor.getName();
			n++;
		}
		
		dtm = new DefaultTableModel(data, colunas);
		table = new JTable(dtm) {
			@Override
			public boolean isCellEditable(int row, int cell) {return false;}
		};
		panelScroll = new JScrollPane(table);
		add(panelScroll);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

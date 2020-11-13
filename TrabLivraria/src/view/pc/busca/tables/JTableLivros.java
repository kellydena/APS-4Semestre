package view.pc.busca.tables;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.PublisherWithBook;

public class JTableLivros extends JFrame{
	static final long serialVersionUID = 1L;
	
	private JScrollPane panelScroll;
	private JTable table;
	private DefaultTableModel dtm;

	public JTableLivros(Collection<PublisherWithBook> livros) {
		super("Livros");
		
		Object[] colunas = new Object[] {"ISBN", "Titulo", "Editora", "Preco"};
		
		Object[][] data = new Object[livros.size()][4];
		int n = 0;
		for(PublisherWithBook livro: livros) {
			data[n][0] = livro.getIsbn();
			data[n][1] = livro.getTitulo();
			data[n][2] = livro.getEditora();
			data[n][3] = livro.getPreco();
			n++;
		}
		
		dtm = new DefaultTableModel(data, colunas);
		table = new JTable(dtm) {
			private static final long serialVersionUID = 1L;
			
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

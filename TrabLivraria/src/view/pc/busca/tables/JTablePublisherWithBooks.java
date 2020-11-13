package view.pc.busca.tables;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.PublisherWithBook;

public class JTablePublisherWithBooks extends JFrame{
	private static final long serialVersionUID = 1L;
	
	JScrollPane panelScroll;
	JTable table;
	DefaultTableModel dtm;
	

	Collection<PublisherWithBook> editoraComLivros;
	
	public JTablePublisherWithBooks(Collection<PublisherWithBook> editoraComLivros) {
		super("Editoras");
		
		this.editoraComLivros = editoraComLivros;
		
		Object[] colunas = new Object[] {"Nome da editora", "Nome do Livro", "ISBN", "Preco"};
		
		Object[][] data = new Object[editoraComLivros.size()][4];
		int n = 0;
		for(PublisherWithBook editoraComLivro: editoraComLivros) {
			data[n][0] = editoraComLivro.getEditora();
			data[n][1] = editoraComLivro.getTitulo();
			data[n][2] = editoraComLivro.getIsbn();
			data[n][3] = editoraComLivro.getPreco();
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

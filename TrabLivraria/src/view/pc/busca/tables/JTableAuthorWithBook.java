package view.pc.busca.tables;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.AuthorWithBook;

public class JTableAuthorWithBook extends JFrame{
	private static final long serialVersionUID = 1L;
	
	JScrollPane panelScroll;
	JTable table;
	DefaultTableModel dtm;
	Collection<AuthorWithBook> AutorComLivro;
	
	public JTableAuthorWithBook(Collection<AuthorWithBook> AutorComLivro) {
		super("Autores e seus livros ou Livros e seus autores");
		
		this.AutorComLivro = AutorComLivro;
		
		Object[] colunas = new Object[] {"ISBN", "Nome do Livro", "Nome do(a) Autor(a)"};
		
		Object[][] data = new Object[AutorComLivro.size()][3];
		int n = 0;
		for(AuthorWithBook ab: AutorComLivro) {
			data[n][0] = ab.getIsbnLivro();
			data[n][1] = ab.getTitullo();
			data[n][2] = ab.getAutorNome();
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

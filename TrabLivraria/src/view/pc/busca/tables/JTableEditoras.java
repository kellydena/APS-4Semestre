package view.pc.busca.tables;

import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Publisher;

public class JTableEditoras extends JFrame{
	private static final long serialVersionUID = 1L;
	
	JScrollPane panelScroll;
	JTable table;
	DefaultTableModel dtm;
	

	Collection<Publisher> editoras;
	
	public JTableEditoras(Collection<Publisher> editoras) {
		super("Editoras");
		
		this.editoras = editoras;
		
		Object[] colunas = new Object[] {"Nome", "URL"};
		
		Object[][] data = new Object[editoras.size()][2];
		int n = 0;
		for(Publisher editora: editoras) {
			data[n][0] = editora.getName();
			data[n][1] = editora.getUrl();
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













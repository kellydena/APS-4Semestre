package view.pc.busca.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Publisher;

public class JTableEditoras extends JFrame{
	
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













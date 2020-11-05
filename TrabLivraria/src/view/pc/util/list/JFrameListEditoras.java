package view.pc.util.list;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;


import entities.Author;
import entities.Publisher;

public class JFrameListEditoras extends JFrame  {
	JComboBox<String> lista;
	JPanel panelEscolhas;
	
	JComboBox cb;
    private JList list;
	//JTable table;
    private DefaultListModel listModel;
 
    private static final String finishString = "Terminar";
    private JButton buttonFinish;
    
	ArrayList<Publisher> editoras;
	Publisher editoraEscolhida;
    
	
	public JFrameListEditoras(ArrayList<Publisher> editoras) {
		super("Escolhe Editoras");
		setSize(400,400);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setResizable(false);
		//setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		
		this.editoras = editoras;
		listModel = new DefaultListModel();

		
		for(Publisher a : this.editoras) {
			listModel.addElement(a.getName());
		}
 
        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        //list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        //list.setCellRenderer(new AuthorRender()); //Mostra as celulas bonitinhas na lista 
// 
        buttonFinish = new JButton(finishString);
        //buttonFinish.addActionListener(TerminaListener);
 
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        
        
        buttonPane.add(buttonFinish);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
 
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
	
	public void addEscolheEditora(ActionListener escolheEditora) {
        buttonFinish.addActionListener(escolheEditora);
	}
    
	public Publisher getEditora() {
		int index = list.getSelectedIndex();
		dispose();
		return editoras.get(index);
		
		
	}
    
	
}



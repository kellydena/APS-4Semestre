package view.pc.util.list;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.Author;

public class JFrameListAutores extends JFrame  {
	JComboBox<String> lista;
	protected JPanel panelEscolhas;
	
	JComboBox cb;
    private JList<Author> list;
    private DefaultListModel<String> listModel;
 
    private static final String finishString = "Terminar";
    private static final String addAutorString = "Adicionar Autor";
    private JButton buttonAddAutor;
    private JButton buttonFinish;
    private JLabel labelAutores;
    
	ArrayList<Author> arrayAutoresEscolhidos;
	ArrayList<Author> arrayAutores;
	ArrayList<Integer> indexes;
	
	public JFrameListAutores(ArrayList<Author> arrayAutores) {
		super("Escolhe Autores");
		
		this.arrayAutores = arrayAutores;
		setSize(500,500);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//setResizable(false);
		//setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		
		listModel = new DefaultListModel<String>();
//
		indexes = new ArrayList<>();
		arrayAutoresEscolhidos = new ArrayList<>();
		
		for(Author a : this.arrayAutores) {
			listModel.addElement(a.getFname() + " " + a.getName());
		}
 
        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

// 
        buttonFinish = new JButton(finishString);
        buttonFinish.setEnabled(false);
// 
        buttonAddAutor = new JButton(addAutorString);
        buttonAddAutor.setActionCommand(addAutorString);
        buttonAddAutor.addActionListener(new AdicionaListener());
 
        labelAutores = new JLabel();
 
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(buttonAddAutor);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(labelAutores);
        buttonPane.add(buttonFinish);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
 
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    class AdicionaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	boolean isIndexViable = true; //Variavel que verifica se o item ja foi adicionado
            int index = list.getSelectedIndex();
            
        	for(Integer i : indexes) { //Fazemos um for para ver se o item ja foi adicionado
        		if(i == index) {
        			isIndexViable = false;
        		}
        	}
        	
    		if(isIndexViable) { 
    			//Ainda nao foi adicionado, precisamos adiciona-lo
    			labelAutores.setText(labelAutores.getText() + list.getSelectedValue() + "; ");
    			indexes.add(index);
    			pack();
    		}
        	//Quando adicionar um item, o botão terminar fica ativado de novo
            if(!buttonFinish.isEnabled()) {
            	buttonFinish.setEnabled(true);
            }
            
            }
        }
    
    public void addEscolheAutores(ActionListener al) {
    	buttonFinish.addActionListener(al);
    }
    
    public ArrayList<Author> getAutores() {
    	
		for(Integer i : indexes) {
			arrayAutoresEscolhidos.add(arrayAutores.get(i));
		}
		System.out.println(arrayAutoresEscolhidos);
		dispose();
		return arrayAutoresEscolhidos;
    	
    }
    
    
	
}


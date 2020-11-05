package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import entities.Author;
import entities.Publisher;
import model.dao.DaoBusca;
import view.pc.busca.ViewBusca;



public class ControllerBusca {

	ArrayList<Publisher> arrayEditora;
	ArrayList<Author> arrayAutores;
	
	//private DAOBusca dao;
	private ViewBusca view;
	private DaoBusca dao;
	
	public ControllerBusca(ViewBusca viewBusca, DaoBusca daoBusca) {
		this.view = viewBusca;
		this.dao = daoBusca;
		init();
	}
	
	private void init() {
		this.view.addSubmitBehavior(new SubimitBehavior());;
	}
	
	
	
	class SubimitBehavior implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String texto = view.getJRadioButton();
			String jtxtText = view.getJTextFieldText();
			
			if(texto.equals("Todas Editoras")) {
				//Usuario escolheu mostrar editoras
				view.mostrarListaEditora(dao.buscaEditora(jtxtText));
			} else if(texto.equals("Todos Autores")) {
				//Usuario escolheu autores
				view.mostrarListaAutor(dao.buscaAutor(jtxtText,jtxtText));
			}
			
			
			
			
			
		}
		
	}
	
	
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.dao.adicionar.Adicionar;
import model.dao.alterar.Alterar;
import model.dao.buscar.Busca;
import view.pc.View;
import view.pc.altera.JFrameAlterar;
import view.pc.busca.JFrameBuscar;
import view.pc.exclui.JFrameExcluir;
import view.pc.inclui.JFrameIncluir;

public class Controller {

	//private DaoJDBC dao;
	private View view;
	
	public Controller(View viewPC) {
		this.view = viewPC;
		
		init();
	}
	
	private void init() {
		this.view.EscolheJanelaBehavior(new EscolheJanela());;
	}
	
	class EscolheJanela implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == view.getButtonExclui()) {
				new ControllerExclui(new JFrameExcluir());
			}
			else if(e.getSource() == view.getButtonAltera()) {
				new ControllerAltera(new JFrameAlterar(), new Busca(), new Alterar());
			}
			else if(e.getSource() == view.getButtonInclui()) {
				new ControllerInclui(new JFrameIncluir(), new Adicionar(), new Busca());
			}
			else if(e.getSource() == view.getButtonBusca()) {
				new ControllerBusca(new JFrameBuscar(), new Busca());
				
			}
		}
		
	}
	
}

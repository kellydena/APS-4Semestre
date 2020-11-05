package view.pc;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public interface View{
	
	public void EscolheJanelaBehavior(ActionListener al);
	
	public JButton getButtonBusca();

	public JButton getButtonAltera();

	public JButton getButtonInclui();
	
	public JButton getButtonExclui();
	
	
}

package view.pc.util.messages;

import javax.swing.JOptionPane;

public class FrameMessage extends JOptionPane{

	private static final long serialVersionUID = 1L;

	public FrameMessage(String tipo) {
		showMessageDialog(null, "Esse(a) " + tipo + " ja esta cadastrado");
	}
	
}

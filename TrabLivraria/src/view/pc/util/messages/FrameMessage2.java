package view.pc.util.messages;

import javax.swing.JOptionPane;

public class FrameMessage2 extends JOptionPane{
	private static final long serialVersionUID = 1L;

	public FrameMessage2() {
		showMessageDialog(null, "Complete todos os campos antes de enviar");
	}
}

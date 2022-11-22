package tecnofenix.ui;




import java.awt.Dimension;
import javax.swing.JFrame;

import javax.swing.JPanel;

public class UIEvento {


	public JFrame frame;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {


		frame = new JFrame("Eventos");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel);
	
		panel.setLayout(null);

		frame.pack();
	}
}

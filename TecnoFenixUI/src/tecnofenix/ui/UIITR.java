package tecnofenix.ui;



import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class UIITR {


	public JFrame frame;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar() {


		frame = new JFrame("ITR");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
	}
}

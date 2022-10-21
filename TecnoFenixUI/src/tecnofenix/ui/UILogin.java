package tecnofenix.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Usuario;
import tecnofenix.interfaces.UsuarioBeanRemote;

//import tecnocanarios.dao.DAOPersona;
//import tecnocanarios.entidades.Persona;
//import tecnocanarios.mensajes.MensajePopUp;
//import tecnocanarios.mensajes.Mensajes;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UILogin {

	public JFrame frame;
	private JTextField txtEmail;
	private JTextField txtPass;
	private Usuario usuario;
	EJBUsuarioRemoto ejbUsuario;
//	MensajePopUp msj = new MensajePopUp();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Principal window) {

		frame = new JFrame("Login");
//		daoPersona = new DAOPersona();
		JPanel panel = new JPanel();
		// definimos un layout
		panel.setPreferredSize(new Dimension(400, 400));// changed it to preferredSize, Thanks!

		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 150, 97, 13);
		panel.add(lblUsuario);

		txtEmail = new JTextField();
		txtEmail.setText("");
		txtEmail.setBounds(117, 147, 222, 19);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPswd = new JLabel("Contrase\u00F1a:");
		lblPswd.setBounds(10, 187, 97, 13);
		panel.add(lblPswd);

		txtPass = new JTextField();
		txtPass.setText("");
		txtPass.setBounds(117, 184, 222, 19);
		panel.add(txtPass);
		txtPass.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtEmail.getText().equals("") && !txtPass.getText().equals("")) {
					usuario = new Usuario();
					usuario = ejbUsuario.login(txtEmail.getText(), txtPass.getText());
					if (usuario != null) {
						JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombres() + " " + usuario.getApellidos(),
								"Bienvenido", JOptionPane.INFORMATION_MESSAGE);
						window.inicializar();
						window.frame.setVisible(true);
						frame.setVisible(false);
//
					} else {
						JOptionPane.showMessageDialog(null, "Error ",
								"Error", JOptionPane.ERROR_MESSAGE);
						txtEmail.setText("");
						txtPass.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usuario y contraseña no validos, intente nuevamente",
							"Intente nuevamente", JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});
		btnLogin.setBounds(254, 227, 85, 21);
		panel.add(btnLogin);
		frame.pack();

		frame.setVisible(true);

	}

}

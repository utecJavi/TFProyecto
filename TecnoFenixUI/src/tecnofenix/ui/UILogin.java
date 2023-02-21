package tecnofenix.ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Analista;
import tecnofenix.entidades.Estudiante;

import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;

import tecnofenix.interfaces.UsuarioBeanRemote;


import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import tecnocanarios.dao.DAOPersona;
//import tecnocanarios.entidades.Persona;
//import tecnocanarios.mensajes.MensajePopUp;
//import tecnocanarios.mensajes.Mensajes;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Color;

public class UILogin {

	public JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtPass;
	private EJBUsuarioRemoto ejbUsuario;
	private JLabel lblServerON;
//
//	@EJB
//	UsuarioBeanRemote usuarioRemote;


	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Principal window) {

		ejbUsuario = new EJBUsuarioRemoto();
//		try {
//			InitialContext ctx = new InitialContext();
//			// Instanciamos las interfaces remotas con el lookup
//
//			usuarioRemote = (UsuarioBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/UsuarioBean!tecnofenix.interfaces.UsuarioBeanRemote");
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
		txtEmail.setBounds(90, 147, 249, 19);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPswd = new JLabel("Contrase\u00F1a:");
		lblPswd.setBounds(10, 187, 97, 13);
		panel.add(lblPswd);

		txtPass = new JPasswordField();
		txtPass.setBounds(90, 184, 249, 19);
		panel.add(txtPass);
		txtPass.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtEmail.getText().equals("") && !String.valueOf(txtPass.getPassword()).equals("")) {
					if(serverON()) {
					Usuario user;
					user = ejbUsuario.login(txtEmail.getText(), String.valueOf(txtPass.getPassword()));
					if(user !=null) {
					if (user.getId() != null) {
						if (user.getValidado()) {
							if (user.getActivo()) {
								JOptionPane.showMessageDialog(null,
										"Bienvenido " + user.getNombres() + " " + user.getApellidos(), "Bienvenido",
										JOptionPane.INFORMATION_MESSAGE);
								if (user instanceof Analista)
									window.inicializar((Analista) user);
								if (user instanceof Tutor)
									window.inicializar((Tutor) user);
								if (user instanceof Estudiante)
									window.inicializar((Estudiante) user);

								window.frame.setVisible(true);
								frame.setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null,
										"El usuario " + user.getNombres() + " " + user.getApellidos()
												+ " fue dado de baja del sistema.",
										"Comuniquese con info@sistemas.utec.edu.uy", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {

							JOptionPane.showMessageDialog(null,
									"El usuario " + user.getNombres() + " " + user.getApellidos()
											+ " todavia no fue validado por un Analista.",
									"Comuniquese con info@sistemas.utec.edu.uy", JOptionPane.INFORMATION_MESSAGE);

						}

					} else {
						JOptionPane.showMessageDialog(null, "Error ", "Error", JOptionPane.ERROR_MESSAGE);
						txtEmail.setText("");
						txtPass.setText("");
					}
					}else {
						JOptionPane.showMessageDialog(null, "La clave o el usuario no son validos! ", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}	
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese Usuario y contraseña para validar",
							"Intente nuevamente", JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});

		JCheckBox hidePasswordCheckbox = new JCheckBox("Ver");
		hidePasswordCheckbox.setBounds(345, 183, 93, 21);

		hidePasswordCheckbox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txtPass.setEchoChar((char) 0);
				} else {
					txtPass.setEchoChar('*');

				}
			}
		});
		panel.add(hidePasswordCheckbox);

		btnLogin.setBounds(254, 227, 85, 21);
		panel.add(btnLogin);

		JButton btnRunConfig = new JButton("Crear nuevo usuario");
		btnRunConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIUsuarioNuevo windowsUIusNuevo = new UIUsuarioNuevo();
				windowsUIusNuevo.inicializar();
				windowsUIusNuevo.frame.setVisible(true);

			}
		});
		btnRunConfig.setBounds(90, 227, 154, 21);
		panel.add(btnRunConfig);
		
		JLabel lblServerStatus = new JLabel("Estado del servidor:");
		lblServerStatus.setBounds(10, 10, 122, 13);
		panel.add(lblServerStatus);
		
		lblServerON = new JLabel("");
		lblServerON.setForeground(Color.BLACK);
		lblServerON.setBounds(142, 10, 182, 13);
		panel.add(lblServerON);

		//verificar estado del servidor
		serverON();
		frame.pack();
		frame.setVisible(true);

	}
	
	public Boolean serverON() {
		Boolean retorno =ejbUsuario.ping();
		if(retorno) {
			lblServerON.setForeground(Color.GREEN);
			lblServerON.setText("[Online]");
		}else {
			lblServerON.setForeground(Color.RED);
			lblServerON.setText("[Offline]");
			JOptionPane.showMessageDialog(null, "El servidor no se encuentra disponible",
					"Intente nuevamente", JOptionPane.INFORMATION_MESSAGE);
		}
		return true;
	}
}

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
import tecnofenix.entidades.Itr;
import tecnofenix.entidades.Tutor;
import tecnofenix.entidades.Usuario;
import tecnofenix.exception.ServiciosException;
import tecnofenix.interfaces.UsuarioBeanRemote;
import tecnofenix.servicios.ConexionClienteJNDIRemote;

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
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class UILogin {

	public JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtPass;
//	private Estudiante usuario;
	@EJB
	UsuarioBeanRemote usuarioRemote;
//	private JPasswordField passwordField;
	
//	MensajePopUp msj = new MensajePopUp();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Principal window) {

		EJBUsuarioRemoto ejbUsuario = new EJBUsuarioRemoto();
		try {
			InitialContext ctx = new InitialContext();
			// Instanciamos las interfaces remotas con el lookup
			
			usuarioRemote= (UsuarioBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/UsuarioBean!tecnofenix.interfaces.UsuarioBeanRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		txtEmail.setText("ggonzalez");
		txtEmail.setBounds(117, 147, 222, 19);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPswd = new JLabel("Contrase\u00F1a:");
		lblPswd.setBounds(10, 187, 97, 13);
		panel.add(lblPswd);

		txtPass = new JPasswordField();
		txtPass.setText("123456");
		txtPass.setBounds(117, 184, 222, 19);
		panel.add(txtPass);
		txtPass.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				window.inicializar();
//				window.frame.setVisible(true);
//				frame.setVisible(false);
				if (!txtEmail.getText().equals("") && !String.valueOf(txtPass.getPassword()).equals("")) {
					Usuario user;
					user = ejbUsuario.login(txtEmail.getText(),String.valueOf(txtPass.getPassword()));
					if (user.getId() != null) {
						JOptionPane.showMessageDialog(null, "Bienvenido " + user.getNombres() + " " + user.getApellidos(),
								"Bienvenido", JOptionPane.INFORMATION_MESSAGE);
						if(user instanceof Analista)window.inicializar((Analista)user);
						if(user instanceof Tutor)window.inicializar((Tutor)user);
						if(user instanceof Estudiante)window.inicializar((Estudiante)user);
						
						window.frame.setVisible(true);
						frame.setVisible(false);
//
					} else {
						JOptionPane.showMessageDialog(null, "Error ","Error", JOptionPane.ERROR_MESSAGE);
						txtEmail.setText("");
						txtPass.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Usuario y contraseña no validos, intente nuevamente",
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
		btnRunConfig.setBounds(117, 227, 85, 21);
		panel.add(btnRunConfig);
			


		frame.pack();
		frame.setVisible(true);

	}
}

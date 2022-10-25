package tecnofenix.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.Itr;
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
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class UILogin {

	public JFrame frame;
	private JTextField txtEmail;
	private JTextField txtPass;
//	private Estudiante usuario;
	@EJB
	UsuarioBeanRemote usuarioRemote;
	
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
					Usuario usuario = new Estudiante();
					usuario = ejbUsuario.login(txtEmail.getText(),txtPass.getText());
//					System.out.println(usuario.getId());
//					System.out.println(usuario.getNombres());
//					System.out.println(usuario.getApellidos());
//					System.out.println(usuario.getDocumento());
//					System.out.println(usuario.getDepartamento());
//					System.out.println(usuario.getMail());
//					System.out.println(usuario.getTelefono());
//					System.out.println(usuario.toString());
					if (usuario.getId() != null) {
						JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombres() + " " + usuario.getApellidos(),
								"Bienvenido", JOptionPane.INFORMATION_MESSAGE);
						window.inicializar();
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
		btnLogin.setBounds(254, 227, 85, 21);
		panel.add(btnLogin);
		
		JButton btnLogin_1 = new JButton("Login");
		btnLogin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estudiante usu = new Estudiante(null, 45829221, "jasuaga", "123456", "Asuaga", "Javier", new Date(System.currentTimeMillis()), "jasuaga@gmail.com", "098195890", new Date(System.currentTimeMillis()), new Itr(null, "Durazno", "UTEC Durazno"));
				usu=(Estudiante)ejbUsuario.crearUsuario(usu);
				System.err.println(usu.toString());
				System.out.println("Se creo el usuario");
			}
		});
		btnLogin_1.setBounds(78, 295, 85, 21);
		panel.add(btnLogin_1);
		frame.pack();

		frame.setVisible(true);

	}
}

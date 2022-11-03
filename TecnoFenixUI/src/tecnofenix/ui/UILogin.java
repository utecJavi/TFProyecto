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
//				window.inicializar();
//				window.frame.setVisible(true);
//				frame.setVisible(false);
				if (!txtEmail.getText().equals("") && !txtPass.getText().equals("")) {
					Usuario user;
					user = ejbUsuario.login(txtEmail.getText(),txtPass.getText());
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
					JOptionPane.showMessageDialog(null, "Usuario y contrase�a no validos, intente nuevamente",
							"Intente nuevamente", JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});
		btnLogin.setBounds(254, 227, 85, 21);
		panel.add(btnLogin);
		
		JButton btnRunConfig = new JButton("RunConfigs");
		btnRunConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Itr itr=new Itr(null, "SanPetersburg", "UTEC Peter");
				ejbUsuario.crearITR(itr);
				Itr itr2=new Itr(null, "Duraxno", "UTEC Duraxno");
				ejbUsuario.crearITR(itr2);
				Itr itr3=new Itr(null, "Miami", "UTEC Miami");
				ejbUsuario.crearITR(itr3);
//				Estudiante usu = new Estudiante(null, 45829221, "jasuaga", "123456", "Asuaga", "Javier", new Date(System.currentTimeMillis()), "jasuaga@gmail.com", "098195890", new Date(System.currentTimeMillis()), new Itr(null, "Durazno", "UTEC Durazno"));
//				usu=(Estudiante)ejbUsuario.crearUsuario(usu);
//				System.err.println(usu.toString());
//				System.out.println("Se creo el usuario");

//				Estudiante usu1 = new Estudiante(null, 12345678, "pepe", "123456", "Gutierrez", "Danny", new Date(System.currentTimeMillis()), "dani@gmail.com", "099123456", new Date(System.currentTimeMillis()), itr);
//				usu1=(Estudiante)ejbUsuario.crearUsuario(usu1);
//				System.err.println(usu1.toString());
//				System.out.println("Se creo el usuario");
//
//				Estudiante usu2 = new Estudiante(null, 87654321, "hola", "123456", "Orlando", "Maximiliano", new Date(System.currentTimeMillis()), "maxi@gmail.com", "099321654", new Date(System.currentTimeMillis()), itr);
//				usu2=(Estudiante)ejbUsuario.crearUsuario(usu2);
//				System.err.println(usu2.toString());
//				System.out.println("Se creo el usuario");

//				Tutor tut = new Tutor(null, 45829222, "jasuaga", "123456", "Asuaga", "Javier", new Date(System.currentTimeMillis()), "jasuaga@gmail.com", "098195890",itr,1,1);
//				tut.setIdItr(itr);
//				tut=(Tutor)ejbUsuario.crearUsuario(tut);
//				System.err.println(tut.toString());
//				System.out.println("Se creo el usuario Tutor");


			}
		});
		btnRunConfig.setBounds(10, 10, 85, 21);
		panel.add(btnRunConfig);


		frame.pack();
		frame.setVisible(true);

	}
}

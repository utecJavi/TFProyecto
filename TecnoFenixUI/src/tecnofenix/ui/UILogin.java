package tecnofenix.ui;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.*;
import tecnofenix.exception.UsuarioNoEncontradoException;
import tecnofenix.interfaces.UsuarioBeanRemote;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

            usuarioRemote = (UsuarioBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/UsuarioBean!tecnofenix.interfaces.UsuarioBeanRemote");
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
                    usuario = ejbUsuario.login(txtEmail.getText(), txtPass.getText());
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
                        JOptionPane.showMessageDialog(null, "Error ", "Error", JOptionPane.ERROR_MESSAGE);
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

        JButton btnLogin_1 = new JButton("Login");
        btnLogin_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // creaciones
                Estudiante estudiante = new Estudiante(1234567, "test2", "contra", "Testito", "El Nuevo Nombre", new Date(System.currentTimeMillis()), "mail@mail.com", "123123123", new Itr(1), new Date(System.currentTimeMillis()));
                Analista analista = new Analista(1234567, "test3", "contra","Testito", "El Nuevo Nombre", new Date(System.currentTimeMillis()), "mail@mail.com", "123123123", new Itr(1));
                Tutor tutor = new Tutor(1234567, "test4", "contra", "Testito", "El Nuevo Nombre", new Date(System.currentTimeMillis()), "mail@mail.com", "123123123", new Itr(1), 1, 2);


                // modificaciones
//                Estudiante estudiante = new Estudiante(1, 1234567, "test", "Testito", "El Nuevo Nombre", new Date(System.currentTimeMillis()), "mail@mail.com", "123123123", new Date(System.currentTimeMillis()));
//                Analista analista = new Analista(1, 1234567, "test", "Testito", "El Nuevo Nombre", new Date(System.currentTimeMillis()), "mail@mail.com", "123123123", new Date(System.currentTimeMillis()));
//                Tutor tutor = new Tutor(1, 1234567, "test", "Testito", "El Nuevo Nombre", new Date(System.currentTimeMillis()), "mail@mail.com", "123123123", new Date(System.currentTimeMillis()));
                try {
                    System.out.println("ESTUDIANTE 1: "+estudiante);
                    Estudiante estudiante1 = (Estudiante) ejbUsuario.crearUsuario(estudiante);
                    Analista analista1 = (Analista) ejbUsuario.crearUsuario(analista);
                    Tutor tutor1 = (Tutor) ejbUsuario.crearUsuario(tutor);

                    System.out.println("----- LLEGO ACA");
                    System.out.println(estudiante1);
                    System.out.println(analista1);
                    System.out.println(tutor1);

//                    estudiante = ejbUsuario.modificarEstudiantePropio(estudiante);

                } catch (UsuarioNoEncontradoException ex) {
                    System.out.println("Usuario NO ENCONTRADO!!!");
                    ex.printStackTrace();
                } /*catch (ServiciosException e1) {
                    System.out.println("Usuario NO ENCONTRADO 2222 !!!");
                    e1.printStackTrace();
                }*/
                System.out.println("Se encontro el usuario: " + estudiante.getNombres());
            }
        });
        btnLogin_1.setBounds(78, 295, 85, 21);
        panel.add(btnLogin_1);
        frame.pack();

        frame.setVisible(true);

    }
}

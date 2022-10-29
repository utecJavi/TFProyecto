package tecnofenix.ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tecnofenix.entidades.AccionConstancia;
import tecnofenix.interfaces.AccionConstanciaBeanRemote;
import tecnofenix.interfaces.UsuarioBeanRemote;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.swing.JButton;

public class bajaConstancia extends JFrame {

	private JPanel contentPane;
	private JTable listaSolicitudes;
	@EJB
	UsuarioBeanRemote usuarioRemote;
	@EJB
	AccionConstanciaBeanRemote accionRemote;
	protected Object frame;
	
	
	public bajaConstancia(JFrame framePadre) {
		try {
			InitialContext ctx = new InitialContext();
			accionRemote= (AccionConstanciaBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/ConstanciaBean!tecnofenix.interfaces.ConstanciaBeanRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 69, 838, 338);
		contentPane.add(scrollPane);
		
		listaSolicitudes = new JTable();
		scrollPane.setViewportView(listaSolicitudes);
		this.listaSolicitudes = this.cargarTablaConstancias();
		JLabel lblNewLabel = new JLabel("LISTA DE CONSTANCIAS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(261, 46, 279, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("BORRAR ");
		btnNewButton.setBounds(344, 504, 210, 38);
		contentPane.add(btnNewButton);
		
		this.initalizeFrame(framePadre);
	}
	
	private void initalizeFrame(JFrame framePadre) {
		
		JFrame frame = new JFrame("Listado de Mascotas");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private JTable cargarTablaConstancias() {

		ArrayList<AccionConstancia> constancias = (ArrayList<AccionConstancia>) accionRemote.listarTodas();

		String[] nombreColumnas = { "Nro Cosntancia", "DETALLE", "FECHA", "ID ANALISTA", "ID CONSTANCIA"};
		Object[][] datos = new Object[constancias.size()][5];

		int fila = 0;

		for (AccionConstancia m : constancias) {

			datos[fila][0] = m.getId();
			datos[fila][1] = m.getDetalle();
			datos[fila][2] = m.getFecha();
			datos[fila][3] = m.getAnalistaId();
			datos[fila][4] = m.getConstanciaId();
			fila++;

		}

		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}
		};

		JTable table = new JTable(model);
		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(600, 600);

		this.listaSolicitudes = table;

		return table;

	}
}

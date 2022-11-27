package tecnofenix.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tecnofenix.EJBRemotos.EJBUsuarioRemoto;
import tecnofenix.entidades.Funcionalidad;
import tecnofenix.entidades.Rol;
import tecnofenix.entidades.Usuario;


public class UIRolHasFuncionalidad {
	
	public JFrame frame;
	private JComboBox<Rol> comboBoxRol;
	private JLabel lblRol;
	private JLabel lblSeleccioneFuncionalidad;
	private JComboBox<Funcionalidad> comboBoxFuncionalidad;
	private JButton btnAgregar;
	private java.awt.List list;
	EJBUsuarioRemoto usuarioRemote;
	private String[] listaTmp;
	private JButton btnAgregar_1;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	
	public void inicializar(Usuario user) {
		usuarioRemote = new EJBUsuarioRemoto();
		frame = new JFrame("Rol tiene funcionalidades");
		
		JPanel panel = new JPanel();
		// definimos un layout
		panel.setPreferredSize(new Dimension(900, 500));// changed it to preferredSize, Thanks!

		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		/*LISTTAAA
		 * 
		 * 
		 * */
		list = new java.awt.List();
//		list.add("Hola");
//		list.removeAll();
//		list.add("Chau");
		listaTmp=list.getItems();
		
		list.setBounds(419, 66, 321, 238);
		panel.add(list);
		
		
		lblRol = new JLabel("Seleccione Rol:");
		lblRol.setBounds(31, 43, 152, 13);
		panel.add(lblRol);
		
//		DAORol daoRol= new DAORol();
		List<Rol> rolCollection= new ArrayList<Rol>();
		rolCollection=usuarioRemote.listarRoles();
		
		List<Funcionalidad> listFuncionalidades =  new ArrayList<Funcionalidad>();;
		listFuncionalidades = usuarioRemote.listarFuncionalidades();
//		rolfun=DAORolFuncion.getAllFunDeRol(rolCollection.get(0).getId());
//		if (rolfun != null && rolfun.getFunCollection()!= null) {
			for (Funcionalidad fun : listFuncionalidades) {
				String nom = fun.getNombre();
				list.add(nom);
			}
//		}
		
		comboBoxRol = new JComboBox<Rol>();
		
		for(Rol rol:rolCollection) {
//			String nom =rol.getNombre();
			comboBoxRol.addItem(rol);
		}
		
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
		comboBoxRol.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(comboBoxRol.getSelectedItem().toString());
						Rol rol = (Rol)comboBoxRol.getSelectedItem();
						list.removeAll();
//						List<String> tmp;
//						tmp=DAORolFuncion.getNomFunByRolId(DAORol.getByName(comboBoxRol.getSelectedItem().toString()).getId());
//						for (String fun : tmp) {
//							
//							list.add(fun);
//						}
//						rolfun=DAORolFuncion.getAllFunDeRol(DAORol.getByName(comboBoxRol.getSelectedItem().toString()).getId());
					
						if (rol != null && rol.getFuncionalidades()!= null) {
						for (Funcionalidad fun : rol.getFuncionalidades()) {
							String nom = fun.getNombre();
							list.add(nom);
						}
					}
					}
				});
		
		comboBoxRol.setBounds(31, 66, 360, 21);
		panel.add(comboBoxRol);
		

		
		lblSeleccioneFuncionalidad = new JLabel("Seleccione Funcionalidad:");
		lblSeleccioneFuncionalidad.setBounds(31, 107, 152, 13);
		panel.add(lblSeleccioneFuncionalidad);
		
				
		comboBoxFuncionalidad = new JComboBox<Funcionalidad>();
		
		for(Funcionalidad f:listFuncionalidades) {
			String nom =f.getNombre();
			comboBoxFuncionalidad.addItem(f);
		}
		
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
		comboBoxFuncionalidad.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(comboBoxFuncionalidad.getSelectedItem().toString());
					}
				});
		comboBoxFuncionalidad.setBounds(31, 130, 360, 21);
		panel.add(comboBoxFuncionalidad);
		
		btnAgregar = new JButton("Agregar >>>");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				listaTmp =list.getItems();
				boolean flag=true;
				for (int i = 0; i < list.getItems().length; i++) {
					System.out.println(i);
					System.out.println(list.getItems()[i]);
					if(list.getItems()[i].equals(comboBoxFuncionalidad.getSelectedItem().toString())) {
						flag=false;
					}
				}
				if(flag) {
					list.add(comboBoxFuncionalidad.getSelectedItem().toString());
					Rol rol = (Rol) comboBoxRol.getSelectedItem();
					rol.getFuncionalidades().add((Funcionalidad)comboBoxFuncionalidad.getSelectedItem());
					rol=usuarioRemote.modificarRol(rol);
//					DAORolFuncion.insert(comboBoxRol.getSelectedItem().toString(),comboBoxFuncionalidad.getSelectedItem().toString());
				}else {
					JOptionPane.showMessageDialog(null, "La funcionalidad ya se encuentra presente en el rol",
							"Ya existe", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnAgregar.setBounds(278, 97, 114, 21);
		panel.add(btnAgregar);
		
		btnAgregar_1 = new JButton("<<< Eliminar Seleccionado");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				list.getSelectedItem();
				if(borrarRow(list.getSelectedItem())) {
					Rol rol = (Rol) comboBoxRol.getSelectedItem();
					Funcionalidad faeliminar=null;
					for(Funcionalidad funT : rol.getFuncionalidades()) {
						if(funT.getNombre().equals(list.getSelectedItem())) {
							faeliminar=funT;	
						}
						
					}
					if(faeliminar!=null) {
					rol.getFuncionalidades().remove(faeliminar);
					}
					rol = usuarioRemote.modificarRol(rol);
//					list.getSelectedItem()
//					Integer id =DAORolFuncion.getIDByRolFuncionalidad(comboBoxRol.getSelectedItem().toString(), list.getSelectedItem());
//					DAORolFuncion.delete(id);
					list.remove(list.getSelectedItem());
				}
				
			}
		});
		btnAgregar_1.setBounds(278, 283, 114, 21);
		panel.add(btnAgregar_1);
		
		
		
		
		
		frame.pack();

		frame.setVisible(true);

	}
	
	
	public boolean borrarRow(String mensaje) {
//		msj.mostrarMensaje(Mensajes.BAJA);
		
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro quieres borrar: "+mensaje,"Warning",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}
	
}

package tecnofenix.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import tecnofenix.entidades.Estudiante;
import tecnofenix.entidades.EscolaridadDTO;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tecnofenix.interfaces.EstudianteBeanRemote;
import tecnofenix.exception.ServiciosException;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.*; 

public class UIEscolaridad {


	public JFrame frame;
	
	private JTable table;
	private DefaultTableModel modelo;
	
	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
         
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	
    private List<ejbModule.tecnofenix.entidades.EscolaridadDTO> escolaridadDTOs; 
    
	@EJB
	EstudianteBeanRemote estudianteBeanRemote;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void inicializar(Estudiante estudiante) {
		
		try {
			InitialContext ctx = new InitialContext();
			estudianteBeanRemote = (EstudianteBeanRemote) ctx.lookup("ejb:/TecnoFenixEJB/EstudianteBean!tecnofenix.interfaces.EstudianteBeanRemote");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		
		frame = new JFrame("Escolaridad");

		JPanel panel = new JPanel();
		// definimos un layout

		panel.setPreferredSize(new Dimension(800, 800));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JLabel lblNombreEstudiante = new JLabel("Escolaridad del estudiante " + estudiante.getNombres() + " " + estudiante.getApellidos());
		lblNombreEstudiante.setBounds(150, 50, 500, 20);
		panel.add(lblNombreEstudiante);
		
		modelo = new DefaultTableModel();

		table = new JTable(modelo);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setForeground(Color.GREEN);
		table.setBackground(Color.BLACK);
		table.setBounds(93, 215, 100, 100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		final String[] columnNames = {"Evento","Tipo","Modalidad","Fecha","ITR","Calificacion"};
		for (int column = 0; column < columnNames.length; column++) {
			modelo.addColumn(columnNames[column]);
		}

		Object[] filas = new Object[columnNames.length];
		
		JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds(10, 169, 780, 302);
        panel.add(scrollPanel);
        
        cargarTabla(estudiante, filas);
        
        autoAjustarTabla();
        
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(20, 507, 90, 13);
		panel.add(lblDestino);
		
		JTextField txtDestino = new JTextField();
		txtDestino.setBounds(20, 520, 720, 21);
		panel.add(txtDestino);
		txtDestino.setColumns(100);
		
        JButton btnExportarEscolaridad = new JButton("Exportar escolaridad a PDF");
        btnExportarEscolaridad.setBounds(286, 481, 250, 19);
        btnExportarEscolaridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtDestino.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Debe ingresar la ruta destino del PDF", "Exportar a PDF", JOptionPane.ERROR_MESSAGE);
				} else {
					
					String ruta = txtDestino.getText() + "EscolaridadEstudiante.pdf";
					
					// Llamada a la creación del PDF
					// como parámetro pasar la ruta donde se guarda el PDF
			        createPDF(new File(ruta), estudiante);
				}
			}
			
		});
        //btnExportarEscolaridad.setVisible(false);
		panel.add(btnExportarEscolaridad);        
		
	}
	
	private void cargarTabla(Estudiante estudiante, Object[] filas) {
		//Borro lo previamente cargado en la tabla
		modelo.getDataVector().removeAllElements();
		modelo.fireTableDataChanged();
		
		try {
			escolaridadDTOs = estudianteBeanRemote.obtenerEscolaridad(estudiante.getId());
			
			for(EscolaridadDTO escolaridadDTO : escolaridadDTOs) {
				
				filas[0] = escolaridadDTO.getEvento();
				filas[1] = escolaridadDTO.getTipo();
				filas[2] = escolaridadDTO.getModalidad();
				filas[3] = escolaridadDTO.getFecha();
				filas[4] = escolaridadDTO.getItr();
				filas[5] = escolaridadDTO.getCalificacion();
				
				modelo.addRow(filas);
			}
			
		} catch (ServiciosException se) {
			System.out.println("Error al consultar escolaridad: " + se.getMessage());
		}
	}
	
	private void autoAjustarTabla() {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15;
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width + 1 , width);
	        }
	        
	        if (width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	private void createPDF(File pdfNewFile, Estudiante estudiante) {
		try {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No se encontró el fichero para generar el pdf " + fileNotFoundException);
            }
            
            document.open();
            document.addTitle("Escolaridad PDF");
            document.addSubject("Escolaridad estudiante");
            
            Anchor anchor = new Anchor("Escolaridad del estudiante " + estudiante.getNombres() + " " + estudiante.getApellidos(), categoryFont);
            anchor.setName("Escolaridad del estudiante " + estudiante.getNombres() + " " + estudiante.getApellidos());            
            Chapter chapTitle = new Chapter(new Paragraph(anchor), 1);
            Paragraph paragraph = new Paragraph("Documento: " + estudiante.getDocumento(), subcategoryFont);
            Section paragraphMore = chapTitle.addSection(paragraph);
            
            Integer numColumns = 6;
            
            PdfPTable table = new PdfPTable(numColumns); 
            
            PdfPCell columnHeader1 = new PdfPCell(new Phrase("Evento"));
            columnHeader1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader1);
            
            PdfPCell columnHeader2 = new PdfPCell(new Phrase("Tipo"));
            columnHeader2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader2);
            
            PdfPCell columnHeader3 = new PdfPCell(new Phrase("Modalidad"));
            columnHeader3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader3);
            
            PdfPCell columnHeader4 = new PdfPCell(new Phrase("Fecha"));
            columnHeader4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader4);
            
            PdfPCell columnHeader5 = new PdfPCell(new Phrase("ITR"));
            columnHeader5.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader5);
            
            PdfPCell columnHeader6 = new PdfPCell(new Phrase("Calificacion"));
            columnHeader6.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader6);
            
            table.setHeaderRows(1);
            
            for(EscolaridadDTO escolaridadDTO : escolaridadDTOs) {
            	table.addCell(escolaridadDTO.getEvento());
            	table.addCell(escolaridadDTO.getTipo());
            	table.addCell(escolaridadDTO.getModalidad());
            	table.addCell(escolaridadDTO.getFecha());
            	table.addCell(escolaridadDTO.getItr());
            	table.addCell(escolaridadDTO.getCalificacion());
			}

            paragraphMore.add(table);
            document.add(chapTitle);
            document.close();
            
		} catch (DocumentException documentException) {
            System.out.println("Se ha producido un error al generar un documento: " + documentException);
        }
	}
	
}

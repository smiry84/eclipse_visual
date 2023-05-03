package visual_vero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Ventana_Vero {

	private JFrame frame;
	private JLabel lblNewLabel=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Vero window = new Ventana_Vero();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana_Vero() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 64));
		panel.setBounds(31, 11, 370, 239);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(27, 21, 317, 147);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("CONSULTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		btnNewButton.setBounds(132, 179, 103, 23);
		panel.add(btnNewButton);
	}
	// PROCEDIMIENTO PARA CONECTAR CON LA BASE DE DATOS Y REALIZAR LA CONSULTA
	private void conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String cadena= "jdbc:mysql://localhost:3306/test"; //RUTA A LA BASE DE DATOS DONDE SE ENCUENTRA NUESTRA TABLA 
			try {
				Connection con=DriverManager.getConnection(cadena,"root", ""); //RUTA,USUARIO,CONTRASEÑA
				PreparedStatement ps=con.prepareStatement("select * from clientes"); //CUALQUIER CAMPO DE LA TABLA CLIENTES
				ResultSet rs=ps.executeQuery(); //COGEMOS EL DATO
				StringBuilder sb=new StringBuilder(); //COMO UNA CADENA DE TEXTO PARA CONCATENAR LOS CAMPOS DE LA CONSULTA
				//BUCLE PARA ALMACENAR LOS CAMPOS DE INTERES
				while(rs.next()) {
					sb.append(rs.getString(2));
					sb.append(rs.getString(3));
					sb.append(rs.getString(4));	
				}
				lblNewLabel.setText(sb.toString());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

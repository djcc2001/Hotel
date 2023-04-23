package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.UsuariosController;
import model.Usuarios;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class AgregarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JPasswordField txtPass2;
	private JLabel labelExit;
	private JLabel labelAtras;
	private JPanel btnAtras;
	private JPanel btnguardar;
	private JLabel lblCorrecto;
	private JPanel btnexit;
	int xMouse, yMouse;

	private UsuariosController usuariosController;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { AgregarUsuario frame = new
	 * AgregarUsuario(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public AgregarUsuario() {
		this.usuariosController = new UsuariosController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarUsuario.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MenuUsuario menuUsuario = new MenuUsuario();
				menuUsuario.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JLabel lblTitulo = new JLabel("Registro Usuario");
		lblTitulo.setBounds(606, 55, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setBounds(562, 200, 253, 14);
		lblUser.setForeground(SystemColor.textInactiveText);
		lblUser.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblUser);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtUser.setBounds(560, 215, 285, 33);
		txtUser.setBackground(Color.WHITE);
		txtUser.setColumns(10);
		txtUser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtUser);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 255, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setBounds(560, 270, 255, 14);
		lblPass.setForeground(SystemColor.textInactiveText);
		lblPass.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblPass);

		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtPass.setBounds(560, 285, 285, 33);
		txtPass.setColumns(10);
		txtPass.setBackground(Color.WHITE);
		txtPass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtPass);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 325, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		btnguardar = new JPanel();
		btnguardar.setBounds(723, 560, 122, 35);
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnguardar.setEnabled(false);

		lblCorrecto = new JLabel();
		lblCorrecto.setBounds(560, 410, 255, 14);
		lblCorrecto.setFont(new Font("Roboto", Font.ITALIC, 12));
		contentPane.add(lblCorrecto);

		JLabel lblPass2 = new JLabel("Confirme Contraseña");
		lblPass2.setBounds(560, 340, 255, 14);
		lblPass2.setForeground(SystemColor.textInactiveText);
		lblPass2.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblPass2);

		txtPass2 = new JPasswordField();
		txtPass2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String pass = String.valueOf(txtPass.getPassword());
				String pass2 = String.valueOf(txtPass2.getPassword());

				if (pass2.equals(pass)) {
					lblCorrecto.setForeground(new Color(0, 128, 0));
					lblCorrecto.setText("Las contraseñas coinciden.");
					btnguardar.setEnabled(true);
				} else {
					lblCorrecto.setForeground(Color.red);
					lblCorrecto.setText("Las contraseñas NO coinciden.");
					btnguardar.setEnabled(false);
				}
			}
		});
		txtPass2.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtPass2.setBounds(560, 355, 285, 36);
		txtPass2.setColumns(10);
		txtPass2.setBackground(Color.WHITE);
		txtPass2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtPass2);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 395, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnguardar.isEnabled()) {
					String user = txtUser.getText();
					String pass = String.valueOf(txtPass.getPassword());
					guardarUser(user, pass);
				}
			}
		});

		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(AgregarUsuario.class.getResource("/imagenes/registro.png")));

		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(AgregarUsuario.class.getResource("/imagenes/Ha-100px.png")));

		btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setBackground(Color.white);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
	}

	private void guardarUser(String user, String pass) {
		if (user.isEmpty() || pass.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Los campos son requeridos.");
			return;
		}

		var usuario = new Usuarios(user, pass);
		boolean guardado = this.usuariosController.guardar(usuario);

		if (guardado) {
			dispose();
			Exito exito = new Exito(0);
			exito.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "No se pudo guardar el nuevo usuario.");
		}

	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

}

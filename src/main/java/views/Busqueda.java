package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedesController;
import controller.ReservasController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private JPanel btnAtras;
	private JPanel btnexit;
	int xMouse, yMouse;

	private ReservasController reservasController;
	private HuespedesController huespedesController;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Busqueda() {

		this.reservasController = new ReservasController();
		this.huespedesController = new HuespedesController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		cargarTablaReservas();

		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");

		cargarTablaHuespedes();

		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(false);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
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
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String busca = txtBuscar.getText();
				if (busca.isEmpty()) {
					if (scroll_table.isVisible()) {
						limpiarTbReservas();
						cargarTablaReservas();
					} else {
						limpiarTbHuespedes();
						cargarTablaHuespedes();
					}
				}
			}
		});

		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String busca = txtBuscar.getText();
				if (!busca.isEmpty()) {

					if (scroll_table.isVisible()) {
						limpiarTbReservas();
						buscaReserva(Integer.parseInt(busca));
					} else {
						limpiarTbHuespedes();
						buscaHuesped(busca);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar el campo para buscar.");
				}
			}

		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (scroll_table.isVisible()) {
					modificarReserva();
					limpiarTbReservas();
					cargarTablaReservas();
				} else if (tbHuespedes.isVisible()) {
					modificarHuesped();
					limpiarTbHuespedes();
					cargarTablaHuespedes();
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (scroll_table.isVisible()) {
					eliminarReserva();
					limpiarTbReservas();
					cargarTablaReservas();
				} else if (tbHuespedes.isVisible()) {
					eliminarHuesped();
					limpiarTbHuespedes();
					cargarTablaHuespedes();
				}
			}

		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	private boolean tieneReservaElegida() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}

	private boolean tieneHuespedElegido() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}

	private void limpiarTbHuespedes() {
		modeloHuesped.getDataVector().clear();

	}

	private void limpiarTbReservas() {
		modelo.getDataVector().clear();
	}

	private void cargarTablaReservas() {
		var reservas = this.reservasController.listar();
		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(),
				reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago() }));
	}

	private void cargarTablaHuespedes() {
		var huespedes = this.huespedesController.listar();
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getIdReservas() }));
	}

	private void buscaHuesped(String busca) {
		var huespedes = this.huespedesController.buscar(busca);
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				huesped.getIdReservas() }));
	}

	private void buscaReserva(int buscar) {
		var reservas = this.reservasController.buscar(buscar);
		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(),
				reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago() }));
	}

	private void modificarHuesped() {
		if (tieneHuespedElegido()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item.");
			return;
		}

		int selectedRow = tbHuespedes.getSelectedRow();

		try {
			Object fechaNobj = modeloHuesped.getValueAt(selectedRow, 3);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Integer id = Integer.valueOf(modeloHuesped.getValueAt(selectedRow, 0).toString());
			String nombre = modeloHuesped.getValueAt(selectedRow, 1).toString();
			String apellido = modeloHuesped.getValueAt(selectedRow, 2).toString();
			Date fechaN = sdf.parse(fechaNobj.toString());
			String nacion = modeloHuesped.getValueAt(selectedRow, 4).toString();
			String tel = modeloHuesped.getValueAt(selectedRow, 5).toString();
			Integer idReservas = Integer.valueOf(modeloHuesped.getValueAt(selectedRow, 6).toString());

			int filasModificadas = this.huespedesController.modificar(id, nombre, apellido, fechaN, nacion, tel,
					idReservas);
			if (filasModificadas > 0) {
				JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo modificar el item.");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error en la modificación del item.");
		}

	}

	private void modificarReserva() {
		if (tieneReservaElegida()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item.");
			return;
		}

		int selectedRow = tbReservas.getSelectedRow();

		try {
			Object fechaEobj = modelo.getValueAt(selectedRow, 1);
			Object fechaSobj = modelo.getValueAt(selectedRow, 2);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Integer id = Integer.valueOf(modelo.getValueAt(selectedRow, 0).toString());
			Date fechaE = sdf.parse(fechaEobj.toString());
			Date fechaS = sdf.parse(fechaSobj.toString());
			double valor = Double.valueOf(modelo.getValueAt(selectedRow, 3).toString());
			String formaPago = modelo.getValueAt(selectedRow, 4).toString();

			int filasModificadas = this.reservasController.modificar(id, fechaE, fechaS, valor, formaPago);
			if (filasModificadas > 0) {
				JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo modificar el item.");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error en la modificación del item.");
		}
	}

	private void eliminarReserva() {
		if (tieneReservaElegida()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item.");
			return;
		}

		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					int cantidadEliminada = this.reservasController.eliminar(id);
					modelo.removeRow(tbReservas.getSelectedRow());
					JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item."));

	}

	private void eliminarHuesped() {
		if (tieneHuespedElegido()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item.");
			return;
		}

		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					int cantidadEliminada = this.huespedesController.eliminar(id);
					modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
					JOptionPane.showMessageDialog(this, cantidadEliminada + " item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item."));
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

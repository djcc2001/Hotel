package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Reservas;

public class ReservasDao {

	private Connection con;

	public ReservasDao(Connection con) {
		this.con = con;
	}

	public boolean guardar(Reservas reserva) {
		boolean resultado = false;

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_pago) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				resultado = ejecutaResgistro(reserva, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	private boolean ejecutaResgistro(Reservas reserva, PreparedStatement statement) throws SQLException {
		boolean result = false;

		java.sql.Date fechaE = new java.sql.Date(reserva.getFechaEntrada().getTime());
		java.sql.Date fechaS = new java.sql.Date(reserva.getFechaSalida().getTime());

		statement.setDate(1, fechaE);
		statement.setDate(2, fechaS);
		statement.setDouble(3, reserva.getValor());
		statement.setString(4, reserva.getFormaPago());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				result = true;
			}
		}

		return result;
	}

	public List<Reservas> listar() {
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas");

			try (statement) {
				statement.execute();

				ResultSet rs = statement.getResultSet();

				List<Reservas> resultado = new ArrayList<>();

				while (rs.next()) {
					// Reservas fila = new Reservas(resultSet.getInt("id"),
					// resultSet.getString("nombre"), resultSet.getString("descripcion"),
					// resultSet.getInt("cantidad"));
					int id = rs.getInt("id");
					Date fechaE = rs.getDate("fecha_entrada");
					Date fechaS = rs.getDate("fecha_salida");
					double valor = rs.getDouble("valor");
					String formaPago = rs.getString("forma_pago");

					Reservas fila = new Reservas(id, fechaE, fechaS, valor, formaPago);

					resultado.add(fila);
				}

				return resultado;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reservas> buscar(int buscar) {
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas WHERE id = ?");

			try (statement) {
				statement.setInt(1, buscar);
				statement.execute();

				ResultSet rs = statement.getResultSet();

				List<Reservas> resultado = new ArrayList<>();

				while (rs.next()) {
					int id = rs.getInt("id");
					Date fechaE = rs.getDate("fecha_entrada");
					Date fechaS = rs.getDate("fecha_salida");
					double valor = rs.getDouble("valor");
					String formaPago = rs.getString("forma_pago");

					Reservas fila = new Reservas(id, fechaE, fechaS, valor, formaPago);

					resultado.add(fila);
				}

				return resultado;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(Integer id, Date fechaE, Date fechaS, double valor, String formaPago) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE reservas SET "
					+ " fecha_entrada = ? , fecha_salida = ? , valor = ?, forma_pago = ? WHERE id = ? ");

			try (statement) {
				java.sql.Date fechaE1 = new java.sql.Date(fechaE.getTime());
				java.sql.Date fechaS1 = new java.sql.Date(fechaS.getTime());

				statement.setDate(1, fechaE1);
				statement.setDate(2, fechaS1);
				statement.setDouble(3, valor);
				statement.setString(4, formaPago);
				statement.setInt(5, id);

				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int deletedCount = statement.getUpdateCount();

				return deletedCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Huespedes;

public class HuespedesDao {

	private Connection con;

	public HuespedesDao(Connection con) {
		this.con = con;
	}

	public boolean guardar(Huespedes huesped) {
		boolean resultado = false;

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reservas) VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				resultado = ejecutaResgistro(huesped, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	private boolean ejecutaResgistro(Huespedes huesped, PreparedStatement statement) throws SQLException {
		boolean result = false;

		java.sql.Date fechaN = new java.sql.Date(huesped.getFechaNacimiento().getTime());

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, fechaN);
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReservas());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				result = true;
			}
		}

		return result;
	}

	public List<Huespedes> listar() {
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes");

			try (statement) {
				statement.execute();

				ResultSet rs = statement.getResultSet();

				List<Huespedes> resultado = new ArrayList<>();

				while (rs.next()) {
					// Reservas fila = new Reservas(resultSet.getInt("id"),
					// resultSet.getString("nombre"), resultSet.getString("descripcion"),
					// resultSet.getInt("cantidad"));
					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					Date fechaN = rs.getDate("fecha_de_nacimiento");
					String nacion = rs.getString("nacionalidad");
					String tel = rs.getString("telefono");
					int idReservas = rs.getInt("id_reservas");

					Huespedes fila = new Huespedes(id, nombre, apellido, fechaN, nacion, tel, idReservas);

					resultado.add(fila);
				}

				return resultado;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huespedes> buscar(String busca) {
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes WHERE apellido LIKE ?");

			try (statement) {
				statement.setString(1, "%" + busca + "%");
				statement.execute();

				ResultSet rs = statement.getResultSet();

				List<Huespedes> resultado = new ArrayList<>();

				while (rs.next()) {
					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					Date fechaN = rs.getDate("fecha_de_nacimiento");
					String nacion = rs.getString("nacionalidad");
					String tel = rs.getString("telefono");
					int idReservas = rs.getInt("id_reservas");

					Huespedes fila = new Huespedes(id, nombre, apellido, fechaN, nacion, tel, idReservas);

					resultado.add(fila);
				}

				return resultado;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaN, String nacion, String tel,
			Integer idReservas) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "
					+ " nombre = ? , apellido = ? , fecha_de_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reservas = ? WHERE id = ? ");

			try (statement) {
				java.sql.Date fechaN1 = new java.sql.Date(fechaN.getTime());

				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fechaN1);
				statement.setString(4, nacion);
				statement.setString(5, tel);
				statement.setInt(6, idReservas);
				statement.setInt(7, id);

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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE id = ?");

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

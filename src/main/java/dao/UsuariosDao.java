package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuarios;

public class UsuariosDao {

	final private Connection con;

	public UsuariosDao(Connection con) {
		this.con = con;
	}

	public boolean guardar(Usuarios usuario) {
		boolean resultado = false;
		try {
			final PreparedStatement statement = con
					.prepareStatement("INSERT INTO usuarios(usuario, pass) VALUE (?, ?)");
			try (statement) {
				statement.setString(1, usuario.getUser());
				statement.setString(2, usuario.getPass());

				int filasAfectadas = statement.executeUpdate();

				resultado = filasAfectadas > 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public boolean iniciarSesion(String usuario, String contrasena) {
		boolean resultado = false;

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND pass=?");

			try (statement) {
				statement.setString(1, usuario);
				statement.setString(2, contrasena);

				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					resultado = true;
				}
				resultSet.close();
				statement.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

}

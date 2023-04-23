package controller;

import dao.UsuariosDao;
import factory.Conexion;
import model.Usuarios;

public class UsuariosController {

	private UsuariosDao usuariosDao;

	public UsuariosController() {
		this.usuariosDao = new UsuariosDao(new Conexion().Conectar());
	}

	public boolean guardar(Usuarios usuario) {
		return usuariosDao.guardar(usuario);
	}

	public boolean iniciarSesion(String usuario, String contrasena) {
		return usuariosDao.iniciarSesion(usuario, contrasena);
	}

}

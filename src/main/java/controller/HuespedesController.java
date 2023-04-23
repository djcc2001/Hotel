package controller;

import java.util.Date;
import java.util.List;

import dao.HuespedesDao;
import factory.Conexion;
import model.Huespedes;

public class HuespedesController {

	private HuespedesDao huespedesDao;

	public HuespedesController() {
		this.huespedesDao = new HuespedesDao(new Conexion().Conectar());
	}

	public boolean guardar(Huespedes huesped) {
		return huespedesDao.guardar(huesped);
	}

	public List<Huespedes> listar() {
		return huespedesDao.listar();
	}

	public List<Huespedes> buscar(String busca) {
		return huespedesDao.buscar(busca);
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaN, String nacion, String tel,
			Integer idReservas) {
		return huespedesDao.modificar(id, nombre, apellido, fechaN, nacion, tel, idReservas);
	}

	public int eliminar(Integer id) {
		return huespedesDao.eliminar(id);
	}

}

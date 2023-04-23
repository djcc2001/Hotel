package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import dao.ReservasDao;
import factory.Conexion;
import model.Reservas;

public class ReservasController {

	private ReservasDao reservasDao;

	public ReservasController() {
		this.reservasDao = new ReservasDao(new Conexion().Conectar());
	}

	public double calcularValor(Date fechaE, Date fechaS) {
		LocalDate localDateE = LocalDate.ofInstant(fechaE.toInstant(), ZoneId.systemDefault());
		LocalDate localDateS = LocalDate.ofInstant(fechaS.toInstant(), ZoneId.systemDefault());

		int dias = (int) ChronoUnit.DAYS.between(localDateE, localDateS);

		double valor = dias * 25;
		return valor;
	}

	public boolean guardar(Reservas reserva) {
		return reservasDao.guardar(reserva);
	}

	public List<Reservas> listar() {
		return reservasDao.listar();
	}

	public List<Reservas> buscar(int buscar) {
		return reservasDao.buscar(buscar);
	}

	public int modificar(Integer id, Date fechaE, Date fechaS, double valor, String formaPago) {
		return reservasDao.modificar(id, fechaE, fechaS, valor, formaPago);
	}

	public int eliminar(Integer id) {
		return reservasDao.eliminar(id);
	}

}

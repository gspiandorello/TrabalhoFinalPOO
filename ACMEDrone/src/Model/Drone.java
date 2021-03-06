package Model;

import java.util.List;

public class Drone {

	private int identificador;

	private double cargaMaxima;

	private double autonomiaKm;

	private Localizacao base;

	public Drone(int identificador, double cargaMaxima, double autonomiaKm, Localizacao base) {
		this.identificador = identificador;
		this.cargaMaxima = cargaMaxima;
		this.autonomiaKm = autonomiaKm;
		this.base = base;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public double getCargaMaxima() {
		return cargaMaxima;
	}

	public void setCargaMaxima(double cargaMaxima) {
		this.cargaMaxima = cargaMaxima;
	}

	public double getAutonomiaKm() {
		return autonomiaKm;
	}

	public void setAutonomiaKm(int autonomiaKm) {
		this.autonomiaKm = autonomiaKm;
	}

	public Localizacao getBase() {
		return base;
	}

	public void setBase(Localizacao base) {
		this.base = base;
	}

	public boolean isAvailable(Cliente cliente, double peso){
		return false;
	}

	@Override
	public String toString() {
		return "Drone{" +
				"identificador=" + identificador +
				", cargaMaxima=" + cargaMaxima +
				", autonomiaKm=" + autonomiaKm +
				", base=" + base +
				'}';
	}
}

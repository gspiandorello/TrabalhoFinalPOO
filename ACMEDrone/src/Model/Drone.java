package Model;

import java.util.List;

public class Drone {

	private int identificador;

	private double cargaMaxima;

	private double autonomiaKm;

	private int base = 0;

	public Drone(int identificador, double cargaMaxima, double autonomiaKm, Integer base) {
		this.identificador = identificador;
		this.cargaMaxima = cargaMaxima;
		this.autonomiaKm = autonomiaKm;
		this.base = base;
	}

	public Drone(int identificador, double cargaMaxima, double autonomiaKm) {
		this.identificador = identificador;
		this.cargaMaxima = cargaMaxima;
		this.autonomiaKm = autonomiaKm;
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

	public Integer getBase() {
		return base;
	}

	public void setBase(Integer base) {
		this.base = base;
	}

	public static Drone constructFromStrings(List<String> values) {
		int id = Integer.parseInt(values.get(0));
		double maxCarga = Double.parseDouble(values.get(1));
		double autonomia  = Double.parseDouble(values.get(2));
		int baseEndereco = Integer.parseInt(values.get(3));
		return new Drone(id, maxCarga, autonomia, baseEndereco);
	}
}

package Model;

import java.util.List;

public class Localizacao {
	private int codigo;

	private String logradouro;

	private double latitude;

	private double longitude;

	public Localizacao(int codigo, String logradouro, double latitude, double longitude) {
		this.codigo = codigo;
		this.logradouro = logradouro;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getDistance(Localizacao target){
		final int R = 6371;

		double latDistance = toRad(target.getLatitude()-this.latitude);
		double lonDistance = toRad(target.getLongitude()-this.longitude);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
				Math.cos(toRad(this.latitude)) * Math.cos(toRad(target.getLatitude())) *
						Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return R*c;
	}

	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}

	@Override
	public String toString() {
		return "Localizacao [" +
				" Codigo: " + codigo +
				", Logradouro:'" + logradouro + '\'' +
				", Lat: " + latitude +
				", Long: " + longitude +
				'}';
	}
}

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
		double x = Math.pow(this.getLatitude()- target.getLatitude(), 2);
		double y = Math.pow(this.getLongitude()- target.getLongitude(), 2);
		return Math.sqrt(x + y);
	}

	public static Localizacao constructFromStrings(List<String> values) {
		int codigo = Integer.parseInt(values.get(0));
		String logradouro = values.get(1);
		double lat  = Double.parseDouble(values.get(2));
		double longt = Double.parseDouble(values.get(3));
		return new Localizacao(codigo, logradouro, lat, longt);
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

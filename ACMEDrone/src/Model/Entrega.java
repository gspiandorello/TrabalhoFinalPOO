package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class Entrega {

	public int getNumero() {
		return numero;
	}

	private int numero; //identificador unico

	private String descricao;

	private Date data;

	double peso;

	private int situacao; //cadastrada, entregada ou cancelada

	Localizacao origem;

	Localizacao destino;

	protected Cliente cliente;

	protected Drone drone;

	public double calculaValor() {
		return 0;
	}

	public String getEmail(){ return this.cliente.getEmail(); }

	public Drone getDrone(){ return this.drone; }

	public Entrega(int numero, String descricao, Date data,
				   double peso, int situacao, Localizacao origem, Localizacao destino){
		this.numero = numero;
		this.descricao = descricao;
		this.data = data;
		this.peso = peso;
		this.situacao = situacao;
		this.origem = origem;
		this.destino = destino;
	}

	public Entrega(int numero, String descricao, Date data,
				   double peso, int situacao, Localizacao origem, Localizacao destino, Cliente cliente){
		this.numero = numero;
		this.descricao = descricao;
		this.data = data;
		this.peso = peso;
		this.situacao = situacao;
		this.origem = origem;
		this.destino = destino;
		this.cliente = cliente;
		this.drone = drone;
	}

	public Entrega(int numero, String descricao, Date data,
				   double peso, int situacao, Localizacao origem, Localizacao destino, Cliente cliente, Drone drone){
		this.numero = numero;
		this.descricao = descricao;
		this.data = data;
		this.peso = peso;
		this.situacao = situacao;
		this.origem = origem;
		this.destino = destino;
		this.cliente = cliente;
		this.drone = drone;
	}
	@Override
	public String toString() {
		return "Entrega{" +
				"numero=" + numero +
				", descricao='" + descricao + '\'' +
				", data=" + data +
				", peso=" + peso +
				", situacao=" + situacao +
				", origem=" + origem +
				", destino=" + destino +
				", cliente=" + cliente +
				", drone=" + drone +
				'}';
	}
}

package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class Entrega {

	private int numero;

	private String descricao;

	private Date data;

	double peso;

	private int situacao;

	int origem;

	int destino;

	private Cliente cliente;

	private Drone drone;

	public double calculaValor() {
		return 0;
	}

	public Entrega(int numero, String descricao, Date data,
				   double peso, int situacao,int origem, int destino){
		this.numero = numero;
		this.descricao = descricao;
		this.data = data;
		this.peso = peso;
		this.situacao = situacao;
		this.origem = origem;
		this.destino = destino;
	}

	public Entrega(int numero, String descricao, Date data,
				   double peso, int situacao,int origem, int destino, Cliente cliente, Drone drone){
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

	public static Entrega constructFromStrings(List<String> values) throws ParseException {
		int tipo = Integer.parseInt(values.get(0));
		int num = Integer.parseInt(values.get(1));
		String desc = values.get(2);

		// Parsing date in day/month/year format
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data = sdf.parse(values.get(3));

		double peso = Double.parseDouble(values.get(4));
		String email = values.get(5);
		int origem = Integer.parseInt(values.get(6));
		int destino =  Integer.parseInt(values.get(7));

		switch(tipo) {
			case 1:
				Date validade = sdf.parse(values.get(8));
				return new EntregaPerecivel(num, desc, data, peso, email, origem, destino, validade);
			case 2:
				String descricaoMaterial = values.get(8);
				return new EntregaNaoPerecivel(num, desc, data, peso, email, origem, destino, descricaoMaterial);
		}

		return null;
	}

}

package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nome;

	private String email;

	private String senha;

	private Localizacao endereco;

	private ArrayList<Entrega> entregasDoCliente = new ArrayList<>();

	public Cliente(String nome, String email, String senha, Localizacao endereco){
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}

	public Cliente(String nome, String email, String senha, Localizacao endereco, ArrayList<Entrega> entregasDoCliente){
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
		this.entregasDoCliente = entregasDoCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public Localizacao getLocalizacao() {
		return endereco;
	}

	public void setEndereco(final Localizacao loc) {
		this.endereco = loc;
	}


	public boolean loginSuccessful(String mail, String password){
		return mail.equals(this.email) && password.equals(this.senha);
	}

	public static Cliente constructFromStrings(List<String> values) {
		String name = values.get(0);
		String email = values.get(1);
		String senha  = values.get(2);
		// a partir do codigo da localizacao, retornar a localizacao (objeto)
		//Localizacao loc = Database.getLocalizacaoByID(Integer.parseInt(values.get(3)));
		return null;//new Cliente(name, email, senha,  loc);
	}

	@Override
	public String toString() {
		return "Cliente[ " +
				"Nome: '" + nome + '\'' +
				", E-mail: '" + email + '\'' +
				", Senha: '" + senha + '\'' +
				", Endereco: " + endereco +
				'}';
	}
}

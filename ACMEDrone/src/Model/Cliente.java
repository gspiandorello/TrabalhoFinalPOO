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

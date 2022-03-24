package br.com.chocode.ifood.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.chocode.ifood.dto.EntregadorDTO;
import br.com.chocode.ifood.dto.GeolocalizacaoDTO;
import br.com.chocode.ifood.dto.PedidoDTO;

@Entity
public class Entregador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(unique = true)
	private String email; 
	private String senha;
	
	@Column(name = "url_image")
	private String urlImage;

	@OneToMany(mappedBy = "entregadorG", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Geolocalizacao> geo = new HashSet<>();
	
	@OneToMany(mappedBy = "entregadorP", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Pedido> pedido = new HashSet<>();
	
	
	public Entregador() {}
	
	
	public Entregador(EntregadorDTO entregador) {
		super();
		this.id = entregador.getId();
		this.nome = entregador.getNome();
		this.email = entregador.getEmail();
		this.senha = entregador.getSenha();
		this.urlImage = entregador.getUrlImage();
		for (GeolocalizacaoDTO geoDTO : entregador.getGeo()) {
			this.geo.add(new Geolocalizacao(geoDTO));
		}
		for (PedidoDTO pedidoDTO : entregador.getPedido()) {
			this.pedido.add(new Pedido(pedidoDTO));
		}
		
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Set<Geolocalizacao> getGeo() {
		return geo;
	}



	public Set<Pedido> getPedido() {
		return pedido;
	}


	public void setPedido(Set<Pedido> pedido) {
		this.pedido = pedido;
	}


	public String getUrlImage() {
		return urlImage;
	}


	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}



	
	
}

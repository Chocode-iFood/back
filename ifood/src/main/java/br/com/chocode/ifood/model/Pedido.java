package br.com.chocode.ifood.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.chocode.ifood.dto.GeolocalizacaoDTO;
import br.com.chocode.ifood.dto.PedidoDTO;


@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_restaurante", nullable = false)
	private String nomeRestaurante;

	@Column(name = "produto", nullable = false)
	private String produto;

	@Column(name = "status", nullable = false)
	private String status;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Set<Geolocalizacao> geo = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "entregador_id")
	private Entregador entregadorP;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Pedido () {}

	public Pedido(Long id, String nomeRestaurante, String produto, String status, Set<Geolocalizacao> geo,
			Entregador entregador, Cliente cliente) {
		super();
		this.id = id;
		this.nomeRestaurante = nomeRestaurante;
		this.produto = produto;
		this.status = status;
		this.geo = geo;
		this.entregadorP = entregador;
		this.cliente = cliente;
	}

	public Pedido(PedidoDTO pedido) {
		this.id = pedido.getId();
		this.nomeRestaurante = pedido.getNomeRestaurante();
		this.produto = pedido.getProduto();
		this.status = pedido.getStatus();
		for (GeolocalizacaoDTO geoDTO : pedido.getGeo()) {
			this.geo.add(new Geolocalizacao(geoDTO));
		}
		this.entregadorP = new Entregador(pedido.getEntregador());
		this.cliente = new Cliente(pedido.getCliente());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeRestaurante() {
		return nomeRestaurante;
	}

	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Geolocalizacao> getGeo() {
		return geo;
	}

	public Entregador getEntregador() {
		return entregadorP;
	}

	public void setEntregador(Entregador entregador) {
		this.entregadorP = entregador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}

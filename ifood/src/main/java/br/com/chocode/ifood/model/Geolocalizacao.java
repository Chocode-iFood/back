package br.com.chocode.ifood.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.chocode.ifood.dto.GeolocalizacaoDTO;
import br.com.chocode.ifood.dto.PedidoDTO;

@Entity
public class Geolocalizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "latitude", nullable = false)
	private String latitude;

	@Column(name = "longitude", nullable = false)
	private String longitude;

	@Column(name = "data", nullable = false)
	private LocalDateTime data;
	
	@ManyToOne
	@JoinColumn(name = "entregador_id")
	private Entregador entregadorG;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public Geolocalizacao() {}

	public Geolocalizacao(Long id, String latitude, String longitude, LocalDateTime data, Entregador entregador,
			Pedido pedido) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.data = data;
		this.entregadorG = entregador;
		this.pedido = pedido;
	}
	
	public Geolocalizacao(GeolocalizacaoDTO geo) {
			
			super();
			this.id = geo.getId();
			this.longitude = geo.getLongitude();
			this.latitude = geo.getLatitude();
			this.data = geo.getData();
			this.entregadorG = new Entregador(geo.getEntregador());
			this.pedido = new Pedido(geo.getPedido());
		
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Entregador getEntregador() {
		return entregadorG;
	}

	public void setEntregador(Entregador entregador) {
		this.entregadorG = entregador;
	}

	public Entregador getEntregadorG() {
		return entregadorG;
	}

	public void setEntregadorG(Entregador entregadorG) {
		this.entregadorG = entregadorG;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}

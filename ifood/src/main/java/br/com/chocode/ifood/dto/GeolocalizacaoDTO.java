package br.com.chocode.ifood.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.chocode.ifood.model.Geolocalizacao;
import br.com.chocode.ifood.model.Pedido;

public class GeolocalizacaoDTO {
	
	private Long id;
	private String longitude;
	private String latitude;
	private LocalDateTime data;
	private EntregadorDTO entregador;
	private PedidoDTO pedido;
	
	public GeolocalizacaoDTO(Long id, String longitude, String latitude, LocalDateTime data, EntregadorDTO entregador, PedidoDTO pedido) {
		
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.data = data;
		this.entregador = entregador;
		this.pedido = pedido;
	}
	
	public GeolocalizacaoDTO(Geolocalizacao geo) {
			super();
			this.id = geo.getId();
			this.longitude = geo.getLongitude();
			this.latitude = geo.getLatitude();
			this.data = geo.getData();
			this.entregador = new EntregadorDTO(geo.getEntregador());
			this.pedido = new PedidoDTO(geo.getPedido());
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

	public EntregadorDTO getEntregador() {
		return entregador;
	}

	public void setEntregador(EntregadorDTO entregador) {
		this.entregador = entregador;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}
	
}

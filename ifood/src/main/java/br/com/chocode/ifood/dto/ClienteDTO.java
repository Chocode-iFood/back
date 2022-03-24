package br.com.chocode.ifood.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.chocode.ifood.model.Cliente;
import br.com.chocode.ifood.model.Pedido;

public class ClienteDTO {
	
	private Long id;
	private String nome;
	private String endereco;
	private String longitude;
	private String latitude;
	private Set<PedidoDTO> pedidosDTO = new HashSet<>();
	
	public ClienteDTO(Long id, String nome, String endereco, String longitude, String latitude, Set<PedidoDTO> pedidos) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.longitude = longitude;
		this.latitude = latitude;
		this.pedidosDTO = pedidos;
	}
	
	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.endereco = cliente.getEndereco();
		this.longitude = cliente.getLongitude();
		this.latitude = cliente.getLatitude();
		for (Pedido pedido : cliente.getPedidos()) {
			this.pedidosDTO.add(new PedidoDTO(pedido));
			}
	}
	
	public ClienteDTO() {
		super();
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public Set<PedidoDTO> getPedido() {
		return pedidosDTO;
	}
	

}

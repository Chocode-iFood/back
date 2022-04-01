package br.com.chocode.back.services;

import br.com.chocode.back.dao.ClienteDAO;
import br.com.chocode.back.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteServiceImpl implements IClienteService{
    private static final Logger LOG = LoggerFactory.getLogger(ClienteServiceImpl.class);
    private ClienteDAO dao;

    @Autowired
    public ClienteServiceImpl(ClienteDAO dao) {
        this.dao = dao;
    }

    @Override
    public Cliente save(Cliente cliente) {
        LOG.info("Salvando cliente no banco de dados.");
        return dao.saveAndFlush(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> listaClientes = dao.findAll();
        LOG.info("Listando todos os clientes.");
        return listaClientes;
    }

    @Override
    public Cliente findById(Long id) {
        LOG.info("Resultado da busca do cliente com o id " + id + ".");
        return dao.findById(id).get();
    }
}

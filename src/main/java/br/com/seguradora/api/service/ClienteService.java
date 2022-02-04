package br.com.seguradora.api.service;

import br.com.seguradora.api.dto.ClienteDTO;
import br.com.seguradora.api.model.Cliente;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findByCpf(String cpf);
    Cliente salvar(Cliente cliente);
    void delete(Cliente cliente);
}

package br.com.seguradora.api.service.impl;

import br.com.seguradora.api.dto.ClienteDTO;
import br.com.seguradora.api.model.Cliente;
import br.com.seguradora.api.repository.ClienteRepository;
import br.com.seguradora.api.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findByCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }

    public Cliente salvar(Cliente cliente){
        Optional<Cliente> clientePesquisa = clienteRepository.findByCpf(cliente.getCpf());
        return clientePesquisa.isPresent()?null:clienteRepository.save(cliente);
    }

    public void delete(Cliente cliente){
        clienteRepository.delete(cliente);
    }
}

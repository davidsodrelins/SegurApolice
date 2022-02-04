package br.com.seguradora.api.service.impl;

import br.com.seguradora.api.dto.ApoliceDTO;
import br.com.seguradora.api.model.Apolice;

import br.com.seguradora.api.model.Cliente;
import br.com.seguradora.api.repository.ApoliceRepository;
import br.com.seguradora.api.repository.ClienteRepository;
import br.com.seguradora.api.service.ApoliceService;
import br.com.seguradora.api.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApoliceServiceImpl implements ApoliceService {

    @Autowired
    private ApoliceRepository apoliceRepository;

    public List<Apolice> findAll(){
        return apoliceRepository.findAll();
    }

    public Optional<Apolice> findByNumero(Long numero){
        return apoliceRepository.findByNumero(numero);
    }

    public Apolice salvar(Apolice apolice){
        return apoliceRepository.save(apolice);
    }

    public void delete(Apolice apolice){
        apoliceRepository.delete(apolice);
    }
}

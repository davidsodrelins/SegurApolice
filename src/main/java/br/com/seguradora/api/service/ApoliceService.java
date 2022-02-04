package br.com.seguradora.api.service;

import br.com.seguradora.api.model.Apolice;

import java.util.List;
import java.util.Optional;

public interface ApoliceService {

     List<Apolice> findAll();
     Optional<Apolice> findByNumero(Long numero);
     Apolice salvar(Apolice apolice);
     void delete(Apolice apolice);
}

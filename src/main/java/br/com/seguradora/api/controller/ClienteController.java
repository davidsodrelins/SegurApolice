package br.com.seguradora.api.controller;

import br.com.seguradora.api.model.Cliente;
import br.com.seguradora.api.service.ClienteService;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Optional;
@Controller
@RequestMapping("/api/clientes")
@Api("API SegurApolice - Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @ApiOperation("Obter lista de um clientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Clientes encontrados"),
            @ApiResponse(code = 404, message = "Sem clientes cadastrados")
    })

    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }


    @GetMapping("{cpf}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o cpf informado")
    })
    public ResponseEntity<?> getClienteByCpf(
            @PathVariable
            @ApiParam("cpf do cliente") String cpf){

        Optional<Cliente> client = clienteService.findByCpf(cpf);
        return client.isPresent()?ResponseEntity.ok(client.get()):ResponseEntity.ok("Cliente não encontrado");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity<?> save( @RequestBody @Valid Cliente cliente ){
        boolean clienteCadastrado = clienteService.findByCpf(cliente.getCpf()).isPresent();

        return !clienteCadastrado?ResponseEntity.ok(clienteService.salvar(cliente)):ResponseEntity.ok("CPF Já cadastrado");
    }

    @DeleteMapping("{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable String cpf ){
        clienteService.findByCpf(cpf)
                .map( client -> {
                    clienteService.delete(client);
                    return client;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Cliente cliente ){
        clienteService
                .findByCpf(cliente.getCpf())
                .map( clientExistent -> {
                    cliente.setCpf(clientExistent.getCpf());
                    clienteService.salvar(cliente);
                    return clientExistent;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }
}

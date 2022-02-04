package br.com.seguradora.api.controller;

import br.com.seguradora.api.dto.ApoliceDTO;
import br.com.seguradora.api.dto.ReportApoliceDTO;
import br.com.seguradora.api.model.Apolice;
import br.com.seguradora.api.model.Cliente;
import br.com.seguradora.api.service.ApoliceService;
import br.com.seguradora.api.service.ClienteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;

@Controller
@RequestMapping("/api/apolices")
@Api("API SegurApolice - Apolices")
public class ApoliceController {

    @Autowired
    private ApoliceService apoliceService;

    @GetMapping
    @ApiOperation("Obter lista de apólices")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Apolices encontradas"),
            @ApiResponse(code = 404, message = "Sem apólices cadastradas")
    })

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(apoliceService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva uma nova apólice")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Apólice salva com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity<Apolice> save(@RequestBody @Valid Apolice apolice) {
        return ResponseEntity.ok(apoliceService.salvar(apolice));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long numero) {
        apoliceService.findByNumero(numero)
                .map(apolice -> {
                    apoliceService.delete(apolice);
                    return apolice;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Apólice não encontrada"));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Apolice apolice) {
        apoliceService
                .findByNumero(apolice.getNumero())
                .map(apoliceExistent -> {
                    apolice.setNumero(apoliceExistent.getNumero());
                    apoliceService.salvar(apolice);
                    return apoliceExistent;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Apólice não encontrada"));
    }

    @GetMapping("{numero}")
    @ApiOperation("Verificar a validade de uma apólice")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Apólice encontrado"),
            @ApiResponse(code = 404, message = "Apólice não encontrada para o numero informado")
    })
    public ResponseEntity<?> reportApoliceByNumero(
            @PathVariable
            @ApiParam("Número da apólice") Long numero) {

        Optional<Apolice> apolice = apoliceService.findByNumero(numero);

        if(apolice.isPresent()) {
            ApoliceDTO dto = new ApoliceDTO(apolice.get());
            ReportApoliceDTO reportApoliceDTO = new ReportApoliceDTO();

            boolean apoliceValida = LocalDate.now().isBefore(apolice.get().getVigenciaFim());
            String dataFinalFormatada = apolice.get().getVigenciaFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            reportApoliceDTO.setApolice(dto);
            reportApoliceDTO.setMessage(apoliceValida?
                    "Apólice ainda está na validade, e irá expirar em "+ dataFinalFormatada:
                    "A Apólice expirou em " + dataFinalFormatada);
            reportApoliceDTO.setStatus(apoliceValida);
            return ResponseEntity.ok(reportApoliceDTO);
        }else {
            return ResponseEntity.ok("Apólice não encontrada");
        }
    }
}
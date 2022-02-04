package br.com.seguradora.api.dto;

import br.com.seguradora.api.model.Apolice;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApoliceDTO {

    private Long numero;
    private LocalDate vigenciaInicio;
    private LocalDate vigenciaFim;
    private Double valor;
    private String placa;

    public ApoliceDTO(Apolice apolice){
        this.numero = apolice.getNumero();
        this.vigenciaInicio = apolice.getVigenciaInicio();
        this.vigenciaFim = apolice.getVigenciaFim();
        this.valor = apolice.getValor();
        this.placa = apolice.getPlaca();
    }

    public long getDiasExpirados(){
        LocalDate hoje = LocalDate.now();
        if(hoje.isAfter(this.vigenciaFim)){
            return ChronoUnit.DAYS.between(hoje, this.vigenciaFim);
        }
        return 0;
    }

    public long getDiasParaExpirar(){
        LocalDate hoje = LocalDate.now();
        if(hoje.isBefore(this.vigenciaFim)){
            return ChronoUnit.DAYS.between(hoje, this.vigenciaFim);
        }
        return 0;
    }

}

package br.com.seguradora.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apolice")
public class Apolice {

    @Id
    @Positive(message = "O Número da apólice precisa ser um número positivo")
    @Digits(integer = 10, fraction = 0, message = "O Número da apólice deve ter no máximo 10 dígitos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMERO", unique = true, nullable = false, precision = 10)
    private Long numero;

    @NotNull(message = "{campo.vigencia_inicio.obrigatorio}")
    @Column(name = "vigencia_inicio", nullable = false)
    private LocalDate vigenciaInicio;

    @NotNull(message = "{campo.vigencia_fim.obrigatorio}")
    @Column(name = "vigencia_fim", nullable = false)
    private LocalDate vigenciaFim;

    @NotNull(message = "{campo.valor.obrigatorio}")
    @Column(name = "valor", precision = 20, scale = 2, nullable = false)
    private Double valor;

    @NotEmpty(message = "{campo.placa.obrigatorio}")
    @Column(name = "placa", nullable = false, length = 7)
    private String placa;


}

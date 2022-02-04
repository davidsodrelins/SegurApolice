package br.com.seguradora.api.model;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "cliente")
public class Cliente {

    @Id
    @Length(max = 11, message = "campo.cpf.tamanho_incorreto")
    @Column(name = "CPF", nullable = false, length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    @Length(max = 200, message = "O nome deve ter no máximo 200 caracteres")
    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @NotEmpty(message = "{campo.cidade.obrigatorio}")
    @Length(max = 200, message = "O nome deve ter no máximo 200 caracteres")
    @Column(name = "CIDADE", nullable = false, length = 200)
    private String cidade;

    @NotEmpty(message = "{campo.estado.obrigatorio}")
    @Length(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    @Column(name = "UF", nullable = false, length = 50)
    private String uf;
}

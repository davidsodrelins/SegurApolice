package br.com.seguradora.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportApoliceDTO {
    private String message;
    private ApoliceDTO apolice;
    private boolean status;
}

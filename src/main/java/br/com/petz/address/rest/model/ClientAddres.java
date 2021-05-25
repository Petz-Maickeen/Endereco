package br.com.petz.address.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientAddres extends AddresEmpty {

    @JsonProperty(value = "idClient")
    private UUID idClient;

    @JsonProperty(value = "cep")
    private String cep;

    @JsonProperty(value = "logradouro")
    private String logradouro;

    @JsonProperty(value = "numero")
    private int numero;

    @JsonProperty(value = "bairro")
    private String bairro;

    @JsonProperty(value = "estado")
    private String estado;

    @JsonProperty(value = "pais")
    private String pais;

    @JsonProperty(value = "principal")
    private String principal;
}

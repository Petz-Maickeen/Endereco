package br.com.petz.address.integration.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "endereco")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AddresPK.class)
public class AddresEntity {
    @Id
    @NotNull
    @Column(name = "id_cliente",columnDefinition = "BINARY(16)")
    private UUID idCliente;

    @Id
    @NotNull
    @Column(name = "cep")
    private String cep;


    @NotNull
    @Column(name = "logradouro")
    private String logradouro;

    @NotNull
    @Column(name = "numero")
    private int numero;

    @NotNull
    @Column(name = "bairro")
    private String bairro;

    @NotNull
    @Column(name = "estado")
    private String estado;

    @NotNull
    @Column(name = "pais")
    private String pais;

    @NotNull
    @Column(name = "principal")
    private String principal;
}

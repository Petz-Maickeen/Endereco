package br.com.petz.address.domain.service;

import br.com.petz.address.integration.entity.AddresEntity;
import br.com.petz.address.rest.model.ClientAddres;

public class AddressMapper {
    public static AddresEntity modelToEntity(ClientAddres clientAddres){
        return AddresEntity.builder()
                .idCliente(clientAddres.getIdClient())
                .logradouro(clientAddres.getLogradouro())
                .numero(clientAddres.getNumero())
                .bairro(clientAddres.getBairro())
                .estado(clientAddres.getEstado())
                .pais(clientAddres.getPais())
                .cep(clientAddres.getCep())
                .principal(clientAddres.getPrincipal())
                .build();
    }

    public static ClientAddres entityToModel(AddresEntity clientAddres){
        return ClientAddres.builder()
                .idClient(clientAddres.getIdCliente())
                .logradouro(clientAddres.getLogradouro())
                .numero(clientAddres.getNumero())
                .bairro(clientAddres.getBairro())
                .estado(clientAddres.getEstado())
                .pais(clientAddres.getPais())
                .cep(clientAddres.getCep())
                .principal(clientAddres.getPrincipal())
                .build();
    }
}

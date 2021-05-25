package br.com.petz.address.domain.service;

import br.com.petz.address.domain.adapter.AddressRepository;
import br.com.petz.address.domain.port.AddressPort;
import br.com.petz.address.integration.entity.AddresEntity;
import br.com.petz.address.rest.model.AddresEmpty;
import br.com.petz.address.rest.model.ClientAddres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AddresService implements AddressPort {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<ClientAddres> findAddress(UUID idClient) {

        List<ClientAddres> result = new ArrayList<>();
        if(addressRepository.existsByIdCliente(idClient)){
            List<AddresEntity> aux = addressRepository.findAllByIdCliente(idClient);
            for(AddresEntity entity: aux){
                result.add(AddressMapper.entityToModel(entity));
            }
        }
        return result;
    }

    @Override
    public AddresEmpty insertAddres(ClientAddres clientAddres) {
        if(addressRepository.existsByIdClienteAndCep(clientAddres.getIdClient(),clientAddres.getCep())){
            return new AddresEmpty();
        } else {
            return AddressMapper.entityToModel(addressRepository.save(AddressMapper.modelToEntity(clientAddres)));
        }
    }

    @Override
    public AddresEmpty updateAddres(ClientAddres clientAddres) {
        if(addressRepository.existsByIdClienteAndCep(clientAddres.getIdClient(),clientAddres.getCep())){
            addressRepository.delete(addressRepository.findByIdClienteAndCep(clientAddres.getIdClient(),clientAddres.getCep()));
            return AddressMapper.entityToModel(addressRepository.save(AddressMapper.modelToEntity(clientAddres)));
        } else {
            return new AddresEmpty();
        }
    }

    @Override
    public boolean deleteAddresByIdClientAndAddres(UUID idCliente, String cep) {
        if(addressRepository.existsByIdClienteAndCep(idCliente,cep)){
            addressRepository.delete(addressRepository.findByIdClienteAndCep(idCliente,cep));
            return true;
        } else {
            return false;
        }
    }
}

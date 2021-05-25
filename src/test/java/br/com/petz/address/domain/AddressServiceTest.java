package br.com.petz.address.domain;

import br.com.petz.address.domain.adapter.AddressRepository;
import br.com.petz.address.domain.service.AddresService;
import br.com.petz.address.domain.service.AddressMapper;
import br.com.petz.address.integration.entity.AddresEntity;
import br.com.petz.address.rest.model.AddresEmpty;
import br.com.petz.address.rest.model.ClientAddres;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Autowired
    private AddresService addresService;

    @MockBean
    private AddressRepository addressRepository;

    @Test
    public void shouldFindPhoneByClientID(){

        UUID idCliente = UUID.randomUUID();
        List<ClientAddres> listaPhone = new ArrayList<>();
        List<AddresEntity> listaEntity = new ArrayList<>();
        ClientAddres cliente = ClientAddres.builder()
                .idClient(idCliente)
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .cep("03111-030")
                .principal("principal")
                .build();

        listaPhone.add(cliente);
        listaEntity.add(AddressMapper.modelToEntity(cliente));
        when(addressRepository.existsByIdCliente(idCliente)).thenReturn(true);
        when(addressRepository.findAllByIdCliente(idCliente)).thenReturn(listaEntity);

        assertEquals(addresService.findAddress(idCliente), listaPhone);
    }

    @Test
    public void shouldNotFindPhoneByClientID(){

        UUID idCliente = UUID.randomUUID();
        List<ClientAddres> listaPhone = new ArrayList<>();
        List<AddresEntity> listaEntity = new ArrayList<>();
        ClientAddres cliente = ClientAddres.builder()
                .idClient(idCliente)
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .cep("03111-030")
                .principal("principal")
                .build();

        listaPhone.add(cliente);
        listaEntity.add(AddressMapper.modelToEntity(cliente));
        when(addressRepository.existsByIdCliente(idCliente)).thenReturn(false);
        when(addressRepository.findAllByIdCliente(idCliente)).thenReturn(listaEntity);

        assertTrue(addresService.findAddress(idCliente).isEmpty());
    }

    @Test
    public void shouldInserClient(){
        UUID idCliente = UUID.randomUUID();
        ClientAddres clienteE = ClientAddres.builder()
                .idClient(idCliente)
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .cep("03111-030")
                .principal("principal")
                .build();
        ClientAddres clienteS = ClientAddres.builder()
                .idClient(idCliente)
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .cep("03111-030")
                .principal("principal")
                .build();

        when(addressRepository.existsByIdClienteAndCep(idCliente,clienteE.getCep())).thenReturn(false);
        when(addressRepository.save(AddressMapper.modelToEntity(clienteE))).thenReturn(AddressMapper.modelToEntity(clienteS));

        assertEquals(addresService.insertAddres(clienteE),clienteS);
    }

    @Test
    public void shouldNotInserExistingPhone(){
        UUID idCliente = UUID.randomUUID();
        ClientAddres clienteE = ClientAddres.builder()
                .idClient(idCliente)
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .cep("03111-030")
                .principal("principal")
                .build();

        when(addressRepository.existsByIdClienteAndCep(idCliente, clienteE.getCep())).thenReturn(true);
        assertTrue(addresService.insertAddres(clienteE) instanceof AddresEmpty);
    }

    @Test
    public void shouldUpdateClient(){
        UUID idCliente = UUID.randomUUID();
        ClientAddres clienteE = ClientAddres.builder()
                .idClient(idCliente)
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .cep("03111-030")
                .principal("principal")
                .build();

        when(addressRepository.existsByIdClienteAndCep(idCliente,clienteE.getCep())).thenReturn(true);
        when(addressRepository.save(AddressMapper.modelToEntity(clienteE))).thenReturn(AddressMapper.modelToEntity(clienteE));

        assertEquals(addresService.updateAddres(clienteE),clienteE);
    }

    @Test
    public void shouldNotUpdateInvalidPhone(){
        UUID idCliente = UUID.randomUUID();
        ClientAddres clienteE = ClientAddres.builder()
                .idClient(idCliente)
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .cep("03111-030")
                .principal("principal")
                .build();

        when(addressRepository.existsByIdClienteAndCep(idCliente,clienteE.getCep())).thenReturn(false);

        assertTrue(addresService.updateAddres(clienteE) instanceof AddresEmpty);
    }

    @Test
    public void shouldDeleteClient(){
        UUID idCliente = UUID.randomUUID();
        String cep = "03111-030";

        when(addressRepository.existsByIdClienteAndCep(idCliente,cep)).thenReturn(true);

        assertTrue(addresService.deleteAddresByIdClientAndAddres(idCliente, cep));
    }

    @Test
    public void shouldNotDeleteInvalidClient(){
        UUID idCliente = UUID.randomUUID();
        String cep = "03111-030";

        when(addressRepository.existsByIdClienteAndCep(idCliente,cep)).thenReturn(false);

        assertFalse(addresService.deleteAddresByIdClientAndAddres(idCliente,cep));
    }
}

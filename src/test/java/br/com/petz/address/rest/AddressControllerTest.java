package br.com.petz.address.rest;

import br.com.petz.address.JsonUtil;
import br.com.petz.address.domain.port.AddressPort;
import br.com.petz.address.rest.model.AddresEmpty;
import br.com.petz.address.rest.model.ClientAddres;
import br.com.petz.address.rest.model.DataResponse;
import br.com.petz.address.rest.util.Constantes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressPort addressPort;

    @Test
    public void shouldFindClientByID() throws Exception {
        UUID idCliente = UUID.randomUUID();
        List<ClientAddres> lista = new ArrayList<>();
        ClientAddres cliente = ClientAddres.builder()
                .idClient(idCliente)
                .cep("03111-030")
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .principal("principal")
                .build();
        lista.add(cliente);

        when(addressPort.findAddress(idCliente)).thenReturn(lista);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cliente/" + idCliente + "/endereco"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(lista).build())));
    }

    @Test
    public void shouldNotFindClientByID() throws Exception {
        UUID idCliente = UUID.randomUUID();
        List<ClientAddres> lista = new ArrayList<>();

        when(addressPort.findAddress(idCliente)).thenReturn(lista);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cliente/" + idCliente + "/endereco"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.ENDERECO_NAO_ENCONTRADO).build())));
    }

    @Test
    public void shouldInsertClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        ClientAddres clienteE = ClientAddres.builder()
                .idClient(idCliente)
                .cep("03111-030")
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .principal("principal")
                .build();
        ClientAddres clienteS = ClientAddres.builder()
                .idClient(idCliente)
                .cep("03111-030")
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .principal("principal")
                .build();

        when(addressPort.insertAddres(clienteS)).thenReturn(clienteS);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/cliente/" + idCliente + "/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(clienteS).build())));
    }

    @Test
    public void shouldNotInsertClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        ClientAddres clienteE = ClientAddres.builder()
                .idClient(idCliente)
                .cep("03111-030")
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .principal("principal")
                .build();

        when(addressPort.insertAddres(clienteE)).thenReturn(new AddresEmpty());
        mockMvc
                .perform(MockMvcRequestBuilders.post("/cliente/" + idCliente + "/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.ENDERECO_JA_CADASTRADO).build())));
    }

    @Test
    public void shouldUpdateClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        ClientAddres clienteE = ClientAddres.builder()
                .idClient(idCliente)
                .cep("03111-030")
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .principal("principal")
                .build();

        when(addressPort.updateAddres(clienteE)).thenReturn(clienteE);
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/cliente/"+idCliente+"/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(clienteE).build())));
    }

    @Test
    public void shouldNotUpdateClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        ClientAddres clienteE = ClientAddres.builder()
                .idClient(idCliente)
                .cep("03111-030")
                .logradouro("Rua Ezequiel Ramos")
                .numero(191)
                .bairro("Mooca")
                .estado("São Paulo")
                .pais("Brasil")
                .principal("principal")
                .build();

        when(addressPort.updateAddres(clienteE)).thenReturn(new AddresEmpty());
        mockMvc
                .perform(MockMvcRequestBuilders.patch("/cliente/"+idCliente+"/endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(clienteE)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.ENDERECO_NAO_ENCONTRADO).build())));
    }

    @Test
    public void shouldDeleteClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        String cep = "03111-030";
        when(addressPort.deleteAddresByIdClientAndAddres(idCliente,cep)).thenReturn(true);
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/cliente/" + idCliente + "/endereco/" + cep))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.ENDERECO_EXCLUIDO_SUCESSO).build())));
    }

    @Test
    public void shouldNotDeleteClient() throws Exception {
        UUID idCliente = UUID.randomUUID();
        String cep = "03111-030";
        when(addressPort.deleteAddresByIdClientAndAddres(idCliente,cep)).thenReturn(false);
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/cliente/" + idCliente + "/endereco/" + cep))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(JsonUtil.toJson(DataResponse.builder().data(Constantes.ENDERECO_NAO_ENCONTRADO).build())));
    }
}
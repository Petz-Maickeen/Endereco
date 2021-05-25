package br.com.petz.address.domain.port;

import br.com.petz.address.rest.model.AddresEmpty;
import br.com.petz.address.rest.model.ClientAddres;

import java.util.List;
import java.util.UUID;

public interface AddressPort {
    List<ClientAddres> findAddress(UUID idClient);
    AddresEmpty insertAddres(ClientAddres clientAddres);
    AddresEmpty updateAddres(ClientAddres clientAddres);
    boolean deleteAddresByIdClientAndAddres(UUID idCliente, String cep);
}

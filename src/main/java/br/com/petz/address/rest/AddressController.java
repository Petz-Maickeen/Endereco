package br.com.petz.address.rest;

import br.com.petz.address.domain.port.AddressPort;
import br.com.petz.address.rest.model.AddresEmpty;
import br.com.petz.address.rest.model.ClientAddres;
import br.com.petz.address.rest.model.DataResponse;
import br.com.petz.address.rest.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente/{id_cliente}/endereco")
public class AddressController {

    @Autowired
    private AddressPort addressPort;

    @GetMapping
    private ResponseEntity<?> findClient(@Valid @PathVariable(value = "id_cliente") UUID idClient){
        List<ClientAddres> result = addressPort.findAddress(idClient);
        return result.isEmpty() ?
                ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.ENDERECO_NAO_ENCONTRADO).build()):
                ResponseEntity.ok().body(DataResponse.builder().data(result).build());
    }

    @PatchMapping
    private ResponseEntity<?> updateClient(@Valid @PathVariable(value = "id_cliente")UUID idClient,
                                           @Valid @RequestBody ClientAddres clientAddres){
        clientAddres.setIdClient(idClient);
        AddresEmpty addresEmpty = addressPort.updateAddres(clientAddres);
        return addresEmpty instanceof ClientAddres ?
                ResponseEntity.ok().body(DataResponse.builder().data(addresEmpty).build()) :
                ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.ENDERECO_NAO_ENCONTRADO).build());
    }

    @PostMapping
    private ResponseEntity<?> insertClient(@Valid @PathVariable(value = "id_cliente")UUID idClient,
                                           @Valid @RequestBody ClientAddres clientAddres){
        clientAddres.setIdClient(idClient);
        AddresEmpty addresEmpty = addressPort.insertAddres(clientAddres);
        return addresEmpty instanceof ClientAddres ?
                ResponseEntity.status(201).body(DataResponse.builder().data(addresEmpty).build()):
                ResponseEntity.status(400).body(DataResponse.builder().data(Constantes.ENDERECO_JA_CADASTRADO).build());
    }

    @DeleteMapping("/{cep}")
    private ResponseEntity<?> removeClient(@Valid @PathVariable(value = "id_cliente")UUID idClient,
                                           @Valid @PathVariable(value = "cep") String cep){
        return addressPort.deleteAddresByIdClientAndAddres(idClient, cep) ?
                ResponseEntity.ok().body(DataResponse.builder().data(Constantes.ENDERECO_EXCLUIDO_SUCESSO).build()):
                ResponseEntity.status(404).body(DataResponse.builder().data(Constantes.ENDERECO_NAO_ENCONTRADO).build());
    }
}

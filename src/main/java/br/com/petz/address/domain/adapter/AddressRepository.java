package br.com.petz.address.domain.adapter;

import br.com.petz.address.integration.entity.AddresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddresEntity, String> {
    boolean existsByIdClienteAndCep(UUID idClient, String cep);
    boolean existsByIdCliente(UUID idClient);
    List<AddresEntity> findAllByIdCliente(UUID idClient);
    AddresEntity findByIdClienteAndCep(UUID idCliente, String cep);
}

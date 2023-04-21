package io.github.lofi.msclients.infrastructure.repositories;

import io.github.lofi.msclients.domains.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCpf(String cpf);
}

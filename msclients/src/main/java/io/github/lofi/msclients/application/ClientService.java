package io.github.lofi.msclients.application;

import io.github.lofi.msclients.domains.Client;
import io.github.lofi.msclients.infrastructure.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }

    public Optional<Client> getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}

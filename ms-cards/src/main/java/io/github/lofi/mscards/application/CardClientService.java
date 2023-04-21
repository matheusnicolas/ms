package io.github.lofi.mscards.application;

import io.github.lofi.mscards.domain.CardClient;
import io.github.lofi.mscards.infra.repository.CardClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardClientService {

    private final CardClientRepository repository;

    public List<CardClient> listCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}



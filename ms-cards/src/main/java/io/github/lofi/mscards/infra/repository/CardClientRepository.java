package io.github.lofi.mscards.infra.repository;

import io.github.lofi.mscards.domain.CardClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardClientRepository extends JpaRepository<CardClient, Long> {
    List<CardClient> findByCpf(String cpf);
}

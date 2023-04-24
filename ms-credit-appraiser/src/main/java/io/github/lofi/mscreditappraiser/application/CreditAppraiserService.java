package io.github.lofi.mscreditappraiser.application;


import io.github.lofi.mscreditappraiser.domain.model.ClientData;
import io.github.lofi.mscreditappraiser.domain.model.ClientSituation;
import io.github.lofi.mscreditappraiser.info.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final ClientResourceClient clientsClient;
    public ClientSituation getClientSituation(String cpf) {
        ResponseEntity<ClientData> clientDataResponse = clientsClient.clientData(cpf);
        return ClientSituation
                .builder()
                .client(clientDataResponse.getBody())
                .build();
    }
}



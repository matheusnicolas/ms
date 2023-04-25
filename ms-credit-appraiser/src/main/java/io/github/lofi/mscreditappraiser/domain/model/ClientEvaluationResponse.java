package io.github.lofi.mscreditappraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientEvaluationResponse {
    private List<ApprovedCard> cards;
}

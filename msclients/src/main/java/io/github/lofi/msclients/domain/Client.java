package io.github.lofi.msclients.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client {

    private Long id;
    private String cpf;
    private String name;
    private Integer age;
}

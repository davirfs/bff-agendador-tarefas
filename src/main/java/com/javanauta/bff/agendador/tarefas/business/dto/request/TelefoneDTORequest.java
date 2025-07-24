package com.javanauta.bff.agendador.tarefas.business.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTORequest {

    private String numero;
    private String ddd;
}

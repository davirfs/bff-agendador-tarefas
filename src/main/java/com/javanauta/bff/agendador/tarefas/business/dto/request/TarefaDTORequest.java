package com.javanauta.bff.agendador.tarefas.business.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.bff.agendador.tarefas.infrastructure.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDTORequest {

    private String nomeTarefa;

    private String descricao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
}

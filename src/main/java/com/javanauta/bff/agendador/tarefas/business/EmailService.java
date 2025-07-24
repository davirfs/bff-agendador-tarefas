package com.javanauta.bff.agendador.tarefas.business;

import com.javanauta.bff.agendador.tarefas.business.dto.response.TarefaDTOResponse;
import com.javanauta.bff.agendador.tarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefaDTOResponse tarefaDTOResponse){
        emailClient.enviarEmail(tarefaDTOResponse);
    }
}

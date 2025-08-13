package com.javanauta.bff.agendador.tarefas.business;

import com.javanauta.bff.agendador.tarefas.business.dto.request.TarefaDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.response.TarefaDTOResponse;
import com.javanauta.bff.agendador.tarefas.infrastructure.client.TarefasClient;
import com.javanauta.bff.agendador.tarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasClient tarefasClient;

    public TarefaDTOResponse gravarTarefa(String token, TarefaDTORequest tarefaDTORequest){
        return tarefasClient.gravarTarefas(token, tarefaDTORequest);

    }

    public List<TarefaDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                   LocalDateTime dataFinal,
                                                                   String authorization){

        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial,dataFinal,authorization);
    }

    public List<TarefaDTOResponse> buscarTarefasPorEmail(String token){
        return tarefasClient.buscaListaDeTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String authorization){
        tarefasClient.deletaTarefaPorId(id, authorization);
    }

    public TarefaDTOResponse alteraStatus(Status status, String id, String authorization){
        return tarefasClient.alteraStatusNotificacao(status, id, authorization);
    }

    public TarefaDTOResponse updateDeTarefas(TarefaDTORequest tarefaDTORequest, String id, String authorization){
        return tarefasClient.updateDeTarefas(tarefaDTORequest, id, authorization);
    }
}

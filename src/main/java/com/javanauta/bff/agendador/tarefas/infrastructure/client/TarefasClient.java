package com.javanauta.bff.agendador.tarefas.infrastructure.client;

import com.javanauta.bff.agendador.tarefas.business.dto.request.TarefaDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.response.TarefaDTOResponse;
import com.javanauta.bff.agendador.tarefas.infrastructure.enums.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefaDTOResponse gravarTarefas(@RequestHeader("Authorization") String authorization,
                                    @RequestBody TarefaDTORequest tarefaDTORequest);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String authorization);

    @GetMapping
    List<TarefaDTOResponse> buscaListaDeTarefasPorEmail(@RequestHeader("Authorization") String authorization);


    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String authorization);

    @PatchMapping
    TarefaDTOResponse alteraStatusNotificacao(@RequestParam("status") Status status,
                                              @RequestParam("id") String id,
                                              @RequestHeader("Authorization") String authorization);

    @PutMapping
    TarefaDTOResponse updateDeTarefas(@RequestBody TarefaDTORequest tarefaDTORequest,
                                      @RequestParam("id") String id,
                                      @RequestHeader("Authorization") String authorization);
}

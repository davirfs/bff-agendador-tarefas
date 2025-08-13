package com.javanauta.bff.agendador.tarefas.controller;


import com.javanauta.bff.agendador.tarefas.business.TarefaService;
import com.javanauta.bff.agendador.tarefas.business.dto.request.TarefaDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.response.TarefaDTOResponse;
import com.javanauta.bff.agendador.tarefas.infrastructure.enums.Status;
import com.javanauta.bff.agendador.tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Tarefas", description = "Cadastra tarefas de usuario")
@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController implements SwaggerController{

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTOResponse> gravarTarefas(@RequestHeader(name = "Authorization", required = false) String authorization,
                                                           @RequestBody TarefaDTORequest tarefaDTORequest){
        return ResponseEntity.ok(tarefaService.gravarTarefa(authorization, tarefaDTORequest));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String authorization){

        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, authorization));

    }

    @GetMapping
    public ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmail(authorization));

    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String authorization){
        tarefaService.deletaTarefaPorId(id, authorization);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTOResponse> alteraStatusNotificacao(@RequestParam("status") Status status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, authorization));
    }

    @PutMapping
    public ResponseEntity<TarefaDTOResponse> updateDeTarefas(@RequestBody TarefaDTORequest tarefaDTORequest,
                                                             @RequestParam("id") String id,
                                                             @RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(tarefaService.updateDeTarefas(tarefaDTORequest, id, authorization));
    }
}

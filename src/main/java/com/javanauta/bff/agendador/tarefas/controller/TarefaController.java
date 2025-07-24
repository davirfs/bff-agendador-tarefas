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
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar tarefas de usuario", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefaDTOResponse> gravarTarefas(@RequestHeader(name = "Authorization", required = false) String authorization,
                                                           @RequestBody TarefaDTORequest tarefaDTORequest){
        return ResponseEntity.ok(tarefaService.gravarTarefa(authorization, tarefaDTORequest));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periodo", description = "Busca tarefa de usuario por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String authorization){

        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, authorization));

    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email", description = "Busca lista de tarefas por email de usuario")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmail(authorization));

    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas", description = "Deleta tarefas de usuario por id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String authorization){
        tarefaService.deletaTarefaPorId(id, authorization);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de notificacao", description = "Altera status de notificacao")
    @ApiResponse(responseCode = "200", description = "Status alterado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefaDTOResponse> alteraStatusNotificacao(@RequestParam("status") Status status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, authorization));
    }

    @PutMapping
    @Operation(summary = "Altera tarefas de usuario", description = "Altera uma tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa alteradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefaDTOResponse> updateDeTarefas(@RequestBody TarefaDTORequest tarefaDTORequest,
                                                             @RequestParam("id") String id,
                                                             @RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(tarefaService.updateDeTarefas(tarefaDTORequest, id, authorization));
    }
}

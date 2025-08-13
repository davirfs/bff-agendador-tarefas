package com.javanauta.bff.agendador.tarefas.controller;

import com.javanauta.bff.agendador.tarefas.business.dto.request.TarefaDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.response.TarefaDTOResponse;
import com.javanauta.bff.agendador.tarefas.infrastructure.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Tarefas", description = "Cadastra tarefas de usuario")
public interface SwaggerController {
    @PostMapping
    @Operation(summary = "Salvar tarefas de usuario", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    ResponseEntity<TarefaDTOResponse> gravarTarefas(@RequestHeader(name = "Authorization", required = false) String authorization,
                                                   @RequestBody TarefaDTORequest tarefaDTORequest);

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periodo", description = "Busca tarefa de usuario por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String authorization);

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email", description = "Busca lista de tarefas por email de usuario")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    ResponseEntity<List<TarefaDTOResponse>> buscaListaDeTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String authorization);

    @DeleteMapping
    @Operation(summary = "Deleta tarefas", description = "Deleta tarefas de usuario por id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String authorization);

    @PatchMapping
    @Operation(summary = "Altera status de notificacao", description = "Altera status de notificacao")
    @ApiResponse(responseCode = "200", description = "Status alterado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    ResponseEntity<TarefaDTOResponse> alteraStatusNotificacao(@RequestParam("status") Status status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String authorization);

    @PutMapping
    @Operation(summary = "Altera tarefas de usuario", description = "Altera uma tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa alteradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    ResponseEntity<TarefaDTOResponse> updateDeTarefas(@RequestBody TarefaDTORequest tarefaDTORequest,
                                                             @RequestParam("id") String id,
                                                             @RequestHeader(name = "Authorization", required = false) String authorization);
}

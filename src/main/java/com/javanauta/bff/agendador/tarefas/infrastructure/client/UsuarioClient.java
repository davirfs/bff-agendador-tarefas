package com.javanauta.bff.agendador.tarefas.infrastructure.client;

import com.javanauta.bff.agendador.tarefas.business.dto.request.EnderecoDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.request.TelefoneDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.request.UsuarioDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.response.EnderecoDTOResponse;
import com.javanauta.bff.agendador.tarefas.business.dto.response.TelefoneDTOResponse;
import com.javanauta.bff.agendador.tarefas.business.dto.response.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String authorization);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest);

    @PostMapping("/login")
    String login(@RequestBody UsuarioDTORequest usuarioDTORequest);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization") String authorization);
    @PutMapping
    UsuarioDTOResponse atualizaDados(@RequestBody UsuarioDTORequest usuarioDTORequest,
                                     @RequestHeader("Authorization") String authorization);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String authorization);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String authorization);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastrarEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                          @RequestHeader("Authorization") String authorization);

    @PostMapping("/cadastro")
    TelefoneDTOResponse cadastrarTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                          @RequestHeader("Authorization") String authorization);
}

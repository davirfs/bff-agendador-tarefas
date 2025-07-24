package com.javanauta.bff.agendador.tarefas.controller;

import com.javanauta.bff.agendador.tarefas.business.UsuarioService;
import com.javanauta.bff.agendador.tarefas.business.dto.request.EnderecoDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.request.TelefoneDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.request.UsuarioDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.response.EnderecoDTOResponse;
import com.javanauta.bff.agendador.tarefas.business.dto.response.TelefoneDTOResponse;
import com.javanauta.bff.agendador.tarefas.business.dto.response.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Usuario", description = "Cadastro e login de usuario")
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuario", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Login usuario", description = "Login de usuario")
    @ApiResponse(responseCode = "200", description = "Usuário logado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public String login(@RequestBody UsuarioDTORequest usuarioDTORequest){
        return usuarioService.login(usuarioDTORequest);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuario por email", description = "Busca um usuario por email")
    @ApiResponse(responseCode = "200", description = "Usuário enconstrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, authorization));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuario por Id", description = "Deleta um usuario")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String authorization){
        usuarioService.deletaUsuarioPorEmail(email, authorization);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do usuario",
            description = "Atualiza dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDados(@RequestBody UsuarioDTORequest usuarioDTORequest,
                                                            @RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(usuarioDTORequest, authorization));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuário",
            description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String authorization){

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTORequest, authorization));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone de usuário",
            description = "Atualiza um telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String authorization){

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTORequest, authorization));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salvar endereço de usuário", description = "Cria um novo endereço")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Endereço já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastrarEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                                                 @RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(usuarioService.cadastroEndereco(authorization, enderecoDTORequest));
    }

    @PostMapping("/cadastro")
    @Operation(summary = "Salvar telefone de usuário", description = "Cria um novo telefone")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Telefone já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastrarTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                 @RequestHeader(name = "Authorization", required = false) String authorization){
        return ResponseEntity.ok(usuarioService.cadastroTelefone(authorization, telefoneDTORequest));
    }
}

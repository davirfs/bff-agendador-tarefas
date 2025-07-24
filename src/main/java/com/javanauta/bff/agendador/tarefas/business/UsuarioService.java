package com.javanauta.bff.agendador.tarefas.business;

import com.javanauta.bff.agendador.tarefas.business.dto.request.EnderecoDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.request.TelefoneDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.request.UsuarioDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.response.EnderecoDTOResponse;
import com.javanauta.bff.agendador.tarefas.business.dto.response.TelefoneDTOResponse;
import com.javanauta.bff.agendador.tarefas.business.dto.response.UsuarioDTOResponse;
import com.javanauta.bff.agendador.tarefas.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTORequest){
        return usuarioClient.salvaUsuario(usuarioDTORequest);
    }

    public String login(UsuarioDTORequest usuarioDTORequest){
        return usuarioClient.login(usuarioDTORequest);
    }

    public UsuarioDTOResponse buscaUsuarioPorEmail(String email, String authorization){
        return usuarioClient.buscaUsuarioPorEmail(email, authorization);
    }

    public void deletaUsuarioPorEmail(String email, String authorization){
        usuarioClient.deletaUsuarioPorEmail(email, authorization);
    }
    public UsuarioDTOResponse atualizaDadosUsuario(UsuarioDTORequest usuarioDTORequest, String token){
        return usuarioClient.atualizaDados(usuarioDTORequest, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTORequest, String authorization){
        return usuarioClient.atualizaEndereco(enderecoDTORequest, idEndereco, authorization);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTORequest, String authorization){
        return usuarioClient.atualizaTelefone(telefoneDTORequest, idTelefone, authorization);
    }

    public EnderecoDTOResponse cadastroEndereco(String token, EnderecoDTORequest enderecoDTORequest){
        return usuarioClient.cadastrarEndereco(enderecoDTORequest, token);
    }

    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest telefoneDTORequest){
        return usuarioClient.cadastrarTelefone(telefoneDTORequest, token);
    }
}

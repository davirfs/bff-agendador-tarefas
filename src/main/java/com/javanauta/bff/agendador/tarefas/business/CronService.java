package com.javanauta.bff.agendador.tarefas.business;

import com.javanauta.bff.agendador.tarefas.business.dto.request.UsuarioDTORequest;
import com.javanauta.bff.agendador.tarefas.business.dto.response.TarefaDTOResponse;
import com.javanauta.bff.agendador.tarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value(("${usuario.senha}"))
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaDTOResquest());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMais5 = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefaDTOResponse> listaDeTarefas =
                tarefaService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMais5, token);

        log.info("Tarefas encontradas: " + listaDeTarefas);

        listaDeTarefas.forEach(tarefa -> { emailService.enviarEmail(tarefa);
            log.info("Email enviado para o usuario: " + tarefa.getEmailUsuario());
        tarefaService.alteraStatus(Status.NOTIFICADO, tarefa.getId(), token);});

        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(UsuarioDTORequest usuarioDTORequest){
        return usuarioService.login(usuarioDTORequest);
    }

    public UsuarioDTORequest converterParaDTOResquest(){
        return UsuarioDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}

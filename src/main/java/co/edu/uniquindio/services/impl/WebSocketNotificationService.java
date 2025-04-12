package co.edu.uniquindio.services.impl;
import co.edu.uniquindio.dto.modeloDTO.NotificacionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WebSocketNotificationService {


    private final SimpMessagingTemplate messagingTemplate;


    public void notificarClientes(NotificacionDTO notificacionDTOM) {
        // Envía un mensaje a todos los clientes suscritos al tema "/topic/reports"
        messagingTemplate.convertAndSend("/topic/reports", notificacionDTOM);
    }


    public void notificarClienteEspecifico(String userId, NotificacionDTO notificacion) {
        messagingTemplate.convertAndSend("/topic/user/" + userId + "/reports", notificacion);
    }
}


package co.edu.uniquindio.services.impl;
import co.edu.uniquindio.dto.modeloDTO.NotificacionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class WebSocketNotificationService {
    private final SimpMessagingTemplate messagingTemplate;
    /**
     * Método para notificar a todos los clientes suscritos a un canal determinado.
     * @param notificacionDTOM El objeto de notificación que se quiere enviar a los clientes.
     */
    public void notificarClientes(NotificacionDTO notificacionDTOM) {
        // Envía un mensaje a todos los clientes suscritos al tema "/topic/reports"
        messagingTemplate.convertAndSend("/topic/reports", notificacionDTOM);
    }
    /**
     * Método para notificar a un cliente específico basado en su ID de usuario.*
     * @param userId El ID del usuario al que se quiere enviar la notificación.
     * @param notificacion El objeto de notificación que se quiere enviar al usuario específico.
     */
    public void notificarClienteEspecifico(String userId, NotificacionDTO notificacion) {
        messagingTemplate.convertAndSend("/topic/user/" + userId + "/reports", notificacion);
    }
    public void notificarPorTopic(NotificacionDTO notificacion) {
        messagingTemplate.convertAndSend(notificacion.topic(), notificacion);
    }
}


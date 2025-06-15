package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.domain.Message;
import luis_auth.guess_the_spy.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@RestController
public class WebSocketController {

	@Autowired
	private WebSocketService service;

	@EventListener
	public void inscreverJogador(SessionSubscribeEvent event) {
		String s = (String) event.getMessage().getHeaders().get("simpSubscriptionId");
		Integer id = Integer.parseInt(s);
	}

	@MessageMapping(value = "/chat/{roomName}")
	public void enviarMensagemChat(@DestinationVariable String roomName, Message message) {
		service.sendChatMenssage(roomName, message);
	}

}

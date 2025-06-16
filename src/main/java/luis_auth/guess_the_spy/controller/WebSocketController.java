package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.domain.Message;

import luis_auth.guess_the_spy.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

	@Autowired
	private WebSocketService service;

	@MessageMapping(value = "/chat/{roomName}")
	public void enviarMensagemChat(@DestinationVariable String roomName, Message message) {
		service.sendChatMenssage(roomName, message);
	}
}

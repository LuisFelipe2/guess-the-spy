package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.domain.Game;
import luis_auth.guess_the_spy.domain.Message;
import luis_auth.guess_the_spy.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate operator;

    private static final String PATH_SOCKET_ROOM = "/topic/room/";
    private static final String PATH_SOCKET_GAME = "/topic/game/";
    private static final String PATH_SOCKET_CHAT = "/topic/chat/";

    public void updateRoom(Room room) {
        operator.convertAndSend(PATH_SOCKET_ROOM + room.getName(), room);
    }

    public void updateGame(Game game) {
        operator.convertAndSend(PATH_SOCKET_GAME + game.roomName(), game);
    }

    public void sendChatMenssage(String roomName, Message message) {
        operator.convertAndSend(PATH_SOCKET_CHAT + roomName, message);
    }

}




package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.controller.representation.GameResponse;
import luis_auth.guess_the_spy.controller.representation.MessageRoomResponse;
import luis_auth.guess_the_spy.controller.representation.PlayerResponse;
import luis_auth.guess_the_spy.domain.Game;
import luis_auth.guess_the_spy.domain.Message;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.domain.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate operator;

    private static final String PATH_SOCKET_ROOM = "/topic/room/";
    private static final String PATH_SOCKET_GAME = "/topic/game/";
    private static final String PATH_SOCKET_CHAT = "/topic/chat/";

    public void updateRoom(Room room) {
        List<PlayerResponse> playerResponses = room.getPlayers().stream().map(player -> new PlayerResponse(player.name())).toList();
        MessageRoomResponse roomResponse = new MessageRoomResponse(playerResponses, room.getStatus().equals(RoomStatus.END_GAME));
        operator.convertAndSend(PATH_SOCKET_ROOM + room.getName(), roomResponse);
    }

    public void updateGame(Game game) {
        String passwordGuess = game.passwordGuess().stream().findFirst().orElse("");
        GameResponse gameResponse = new GameResponse(game.password(), game.spy().name(), passwordGuess, game.votes());
        operator.convertAndSend(PATH_SOCKET_GAME + game.roomName(), gameResponse);
    }

    public void sendChatMenssage(String roomName, Message message) {
        operator.convertAndSend(PATH_SOCKET_CHAT + roomName, message);
    }

}




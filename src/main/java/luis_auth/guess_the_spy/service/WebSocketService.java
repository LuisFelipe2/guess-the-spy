package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.domain.Game;
import luis_auth.guess_the_spy.domain.Message;
import luis_auth.guess_the_spy.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;


@Service
public class WebSocketService {

    @Autowired
    TaskScheduler taskScheduler;

    @Autowired
    SimpMessagingTemplate operator;

    static final String PATH_SOCKET_ROOM = "/topic/room/";

    static final String PATH_SOCKET_GAME = "/topic/game/";
    static final String PATH_SOCKET_CHAT = "/topic/chat/";

    private void convertSend(Game game) {
        operator.convertAndSend(PATH_SOCKET_GAME + game.getRoom().getName(), game);
    }
    private void convertSend(Room room) {
        operator.convertAndSend(PATH_SOCKET_ROOM + room.getName(), room);
    }

    public void updateRoom(Room room) {
        convertSend(room);
    }

    public void updateGame(Game game) {
        convertSend(game);
    }

    public void sendChatMenssage(String roomName, Message message) {
        operator.convertAndSend(PATH_SOCKET_CHAT + roomName, message);
    }


//    public void buscarNovaPartida(Integer tempoSegundos, PartidaEmProgresso partidaEmProgresso) {
//        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(tempoSegundos);
//        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
//        Instant result = Instant.from(zonedDateTime);
//
//
//        taskScheduler.schedule(new DuaracaoRodada(partidaEmProgresso), result);
//    }

//    public void encerrarRodada(Game game) {
//        convertSend(game);
//    }


//    class DuaracaoRodada implements Runnable {
//
//        Game partidaEmProgresso;
//
//        public DuaracaoRodada(Game game) {
//            this.partidaEmProgresso = game;
//        }
//
//        @SneakyThrows
//        @Override
//        public void run() {
//            Thread.sleep(4000);
//            encerrarRodada(partidaEmProgresso);
//        }
//    }

}




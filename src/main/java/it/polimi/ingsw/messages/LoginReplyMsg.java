package it.polimi.ingsw.messages;

public class LoginReplyMsg extends ServerMessage {
     private boolean joined;
     private String username;
     private String gameId;
    public LoginReplyMsg(String username, String gameId, boolean joined) {
        super(MessageType.LOGIN_REPLY);
        this.joined=joined;
        this.username=username;
        this.gameId=gameId;
    }

    public boolean wasJoined(){
        return joined;
    }

    public String getUsername() {
        return username;
    }

    public String getGameId() {
        return gameId;
    }
}

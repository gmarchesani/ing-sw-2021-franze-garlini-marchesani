package it.polimi.ingsw.messages;

public class PlayersNumberRequest extends ClientMessage {
    private int playersNumber;
    private String username;
    public PlayersNumberRequest(String username, int playersNumber) {
        super(username,MessageType.PLAYER_NUMBER);
        this.playersNumber=playersNumber;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

}
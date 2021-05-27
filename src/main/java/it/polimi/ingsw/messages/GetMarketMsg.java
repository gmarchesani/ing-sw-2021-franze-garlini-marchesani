package it.polimi.ingsw.messages;

import it.polimi.ingsw.model.enumeration.ResourceType;

public class GetMarketMsg extends GeneralMessage{
    private char rowOrCol;
    private int num;
    private ResourceType conversion;
    public GetMarketMsg(String username, String gameID, char rowOrCol, int num, ResourceType conversion) {
        super(username, gameID, MessageType.PICK_MARKETRES);
        this.rowOrCol = rowOrCol;
        this.num = num;
        this.conversion=conversion;
    }

    public char getRowOrCol(){
        return rowOrCol;
    }

    public int getNum() {
        return num;
    }
    public ResourceType getConversion(){ return conversion;}

}
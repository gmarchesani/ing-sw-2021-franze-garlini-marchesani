package it.polimi.ingsw.model;

public enum ResourceType {
    COIN(0),
    STONE(1),
    SERVANT(2),
    SHIELD(3),
    FAITH(4),
    EMPTY(5);

    private int resource;

    ResourceType(int resource){
        this.resource=resource;
    }

    public int getResource(){
        return resource;
    }

}
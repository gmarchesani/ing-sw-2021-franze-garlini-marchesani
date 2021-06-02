package it.polimi.ingsw.messages;

public enum MessageType {
    //game's messages
    LOGIN,
    LOGIN_REPLY,
    START_GAME,
    PLAYER_NUMBER,

    //Server Connection Messages
    SUCCESSFUL_HOST,
    STRING_MESSAGE,

    //end turn message
    START_TURN,
    TURN_ACTION,
    END_TURN,


    //Setup
    STARTING_LEADERS,

    //show messages
    SHOW_LEADER,
    SHOW_MARKET,
    SHOW_DEV_MARKET,
    SHOW_DEV_CARDS,
    SHOW_RES,
    SHOW_FAITH_TRACK,
    SHOW_VICTORY_POINTS,

    //action
    PLAYLEADER,
    PICK_DEVCARD,
    PICK_MARKETRES,
    ACTIVATE_PRODUCTION,
    RESOURCE_TO_WAREHOUSE,

    //intermediate message
    REARRANGE_REPLY,
    REARRANGE_REQUEST,
    DEPOT_TO_REARRANGE,
    DEVCARD_REPLY,
    PLACE_CARD,
    ROW_OR_COL,
    WHITE_CONVERSION,
    PLACE_RES,
    PRODUCTION_RES,
    PAY_RES,
    RESOURCE_TO_STRONGBOX,

    ERROR,
    WIN,
    DISCONNECTION,
    LOSE;
}

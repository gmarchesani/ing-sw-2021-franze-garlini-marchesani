package it.polimi.ingsw.messages;

public enum MessageType {
    //game's messages
    LOGIN,
    LOGIN_REPLY,
    START_GAME,
    //LEADER_REPLY for the initial state

    //end turn message
    START_TURN,
    END_TURN,

    //show messages
    SHOW_LEADER,
    SHOW_MARKET,
    SHOW_DEV_MARKET,
    SHOW_SLOT,
    SHOW_RES,
    SHOW_FAITH_TRACK,
    SHOW_ALL_SLOT,
    SHOW_ALL_RES,
    SHOW_INFO,

    //action
    PLAYLEADER,
    PICK_DEVCARD,
    PICK_MARKETRES,
    ACTIVATE_PRODUCTION,

    //intermediate message
    CHOOSE_RES,
    REARRANGE_REPLY,
    REARRANGE_REQUEST,
    DEPOT_TO_REARRANGE,
    LEADER_REPLY,
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

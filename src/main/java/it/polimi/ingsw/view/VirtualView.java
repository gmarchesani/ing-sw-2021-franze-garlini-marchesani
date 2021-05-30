package it.polimi.ingsw.view;


import it.polimi.ingsw.messages.GeneralMessage;
import it.polimi.ingsw.messages.*;
import it.polimi.ingsw.model.Board.Market;
import it.polimi.ingsw.model.Board.Warehouse;
import it.polimi.ingsw.model.Card.DevCard;
import it.polimi.ingsw.model.Card.DevCardSlot;
import it.polimi.ingsw.model.Card.LeaderCard;
import it.polimi.ingsw.model.enumeration.ResourceType;
import it.polimi.ingsw.network.ClientHandler;
import it.polimi.ingsw.observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Hides the network implementation from the controller.
 * The controller calls methods from this class instead of calling methods from the view.
 */
public class VirtualView implements View, Observer {
    private final ClientHandler clientHandler;

    /**
     * Class constructor.
     * @param clientHandler the clientHandler the view will sends messages to.
     */
    public VirtualView(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }


    @Override
    public void askPlayersNumber() {
        clientHandler.sendMessage(new HostGameReply());
    }

    @Override
    public void askInitialRes() {
        clientHandler.sendMessage(new InitialResMsg());
    }
    @Override
    public void askAction() {
        clientHandler.sendMessage(new ActionRequest());
    }

    @Override
    public void showLoginResult(String username, String gameId, boolean wasJoined) {
        clientHandler.sendMessage(new LoginReplyMsg(username, gameId, wasJoined);
    }

    @Override
    public void showMessage(String message) {
        clientHandler.sendMessage(new ServerGenericMessage(message));
    }

    @Override
    public void showDisconnectionMsg(String disconnectedUser, String message) {
        clientHandler.sendMessage(new DisconnectionMessage(disconnectedUser, message));
    }

    @Override
    public void showMatchInfo(List<String> players, String activePlayer) {
        clientHandler.sendMessage(new MatchInfoRequest(players, activePlayer));
    }

    @Override
    public void showLeaderCards(List<LeaderCard> leaderCards, String username) {
        clientHandler.sendMessage(new LeaderCardShowReq(leaderCards, username));
    }

    @Override
    public void askDevCardToBuy(List<DevCard> devCardList) {
        clientHandler.sendMessage(new BuyDevCardRequest(devCardList));
    }

    @Override
    public void askMarketLineToGet(Market market) {
        clientHandler.sendMessage(new GetMarketLineRequest(market));
    }

    @Override
    public void showMarket(Market market) {
        clientHandler.sendMessage(new ShowMarketMsg(market)));
    }

    @Override
    public void showDevMarket(List<DevCard> availableCards) {
        clientHandler.sendMessage(new ShowDevMarket(availableCards));
    }

    @Override
    public void askCardsToActivateProd(List<DevCard> devCardList) {
        clientHandler.sendMessage(new ActivateProductionMsg(devCardList));
    }

    @Override
    public void showResources(HashMap<ResourceType, Integer> strongbox, Warehouse warehouse, String username) {
        clientHandler.sendMessage(new ShowResourcesRequest(warehouse,strongbox));
    }

    @Override
    public void showResources(HashMap<ResourceType, Integer> strongbox, Warehouse warehouse) {
        clientHandler.sendMessage(new ShowResourcesRequest(warehouse, strongbox));
    }

    @Override
    public void showErrorMsg(String message) {
        clientHandler.sendMessage(new ErrorMsg(message));
    }

    @Override
    public void showFaithTrack(HashMap<String, Integer> faithTrackState) {
        clientHandler.sendMessage(new ShowFaithTrackMsg(faithTrackState));
    }

    @Override
    public void showCurrentVP(int victoryPoints, String username) {
        clientHandler.sendMessage(new ShowActualVPMsg(victoryPoints, username));
    }

    @Override
    public void showSlots(DevCardSlot devCardSlot, String username) {
        clientHandler.sendMessage(new ShowSlotsMsg(victoryPoints, username));
    }

    @Override
    public void askSlot(ArrayList<Integer> availableSlots) {
        clientHandler.sendMessage(new SlotRequest(availableSlots));
    }

    @Override
    public void askChooseMarbleConversion(ArrayList<ResourceType> availableConversions) {
        clientHandler.sendMessage(new WhiteConversionMsg(availableConversions));
    }

    @Override
    public void askChooseResToPay(HashMap<ResourceType, Integer> strongbox, Warehouse warehouse, ResourceType resource) {
        clientHandler.sendMessage(new ResToPayRequest(strongbox, warehouse, resource));
    }

    @Override
    public void askChooseOneRes(ArrayList<String> resources, String message) {
        clientHandler.sendMessage(new ResourceReply(resources, message));
    }

    @Override
    public void askChooseFloor(Warehouse warehouse, ResourceType resToPlace) {
        clientHandler.sendMessage(new PlaceMsg(warehouse, resToPlace));
    }

    @Override
    public void askRearrange(Warehouse warehouse, HashMap<ResourceType, Integer> resources) {
        clientHandler.sendMessage(new RearrangeRequestMsg(warehouse, resources));
    }

    @Override
    public void askLeaderCardToPlay(List<LeaderCard> leaderCards) {
        clientHandler.sendMessage(new ShowLeaderMsg(leaderCards));
    }

    @Override
    public void askLeaderCardToKeep(ArrayList<LeaderCard> leaderCards) {
        clientHandler.sendMessage(new StartingLeadersReplyMsg(leaderCards));
    }

    @Override
    public void showWinMessage(String winnerUser) {
        clientHandler.sendMessage(new WinMessage(winner));
    }

    /**
     * Receives an update message from the model.
     * The message is sent over the network to the client.
     * @param message the update message.
     */

    @Override
    public void update(GeneralMessage message) {
        clientHandler.sendMessage(message);
    }
}

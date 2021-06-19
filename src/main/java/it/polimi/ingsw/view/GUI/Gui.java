package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Card.DevCard;
import it.polimi.ingsw.model.Card.DevCardSlot;
import it.polimi.ingsw.model.Card.LeaderCard;
import it.polimi.ingsw.model.enumeration.ResourceType;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.View;
import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Gui extends ObservableView implements View {

    @Override
    public void askConnect() {
        InitSceneController isc = new InitSceneController();
        isc.addAllObservers(observers);
        Platform.runLater(() -> {
            try {
                MainApp.changeRootPane(observers,"/fxml/init_scene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void askLobby() {
        LobbySceneController lsc = new LobbySceneController();
        lsc.addAllObservers(observers);
        Platform.runLater(() -> {
            try {
                MainApp.changeRootPane(observers,"/fxml/lobby_scene");

            } catch (IOException e) {

                e.printStackTrace();
            }
        });



    }

    @Override
    public void askPlayersNumber() {
/*
        NumPlayerSceneController npsc = new NumPlayerSceneController();
        npsc.addAllObservers(observers);
        Platform.runLater(()->
            {
               try{
                   MainApp.changeRootPane(observers,"/fxml/board_scene_singleplayer");
               } catch (IOException e){
                   e.printStackTrace();
               }
            });


 */

    }

    @Override
    public void askAction() {

    }

    @Override
    public void showLoginResult(String username, String gameId, boolean wasJoined) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void askDevCardToBuy() {

    }

    @Override
    public void askMarketLineToGet(ArrayList<ResourceType> conversion) {

    }

    @Override
    public void askResourceToWarehouse(HashMap<ResourceType, Integer> resToPlace, int numAny, ArrayList<ResourceType> extraDepot) {

    }

    @Override
    public void askProduction(HashMap<ResourceType, Integer> strongbox, HashMap<ResourceType, Integer> warehouse,  HashMap<ResourceType, Integer> price, int anyPayment, int anyProduce) {

    }

    @Override
    public void showMarket(ResourceType[][] market, ResourceType corner) {

    }

    @Override
    public void showDevMarket(ArrayList<DevCard> availableCards, ArrayList<Integer> remainingCards) {

    }

    @Override
    public void showStrongbox(HashMap<ResourceType, Integer> strongbox, String username) {

    }

    @Override
    public void showWarehouse(HashMap<Integer, Integer> depotToQuantity, HashMap<Integer, ResourceType> depotToResource, String username) {

    }

    @Override
    public void askCardsToActivateProd(ArrayList<DevCard> devCardList) {

    }

    @Override
    public void showErrorMsg(String message) {

    }

    @Override
    public void showFaithTrack(HashMap<String, Integer> playerFaith, boolean wasZoneActivated, int whichZone) {

    }

    @Override
    public void showCurrentVP(HashMap<String, Integer> victoryPoints) {

    }

    @Override
    public void showSlots(DevCardSlot devCardSlot, String username) {

    }

    @Override
    public void showRemainingLeaderCards(String username, int remaining) {

    }

    @Override
    public void askSlot(HashMap<ResourceType, Integer> strongbox, HashMap<ResourceType, Integer> warehouse, HashMap<ResourceType, Integer> cardCost, int numAny, ArrayList<Integer> availableSlots) {

    }

    @Override
    public void askLeaderCardToPlay(ArrayList<LeaderCard> leaderCards) {

    }

    @Override
    public void askLeaderCardToKeep(ArrayList<LeaderCard> leaderCards) {

    }

    @Override
    public void showWinMessage(HashMap<String, Integer> finalPoints) {

    }

    @Override
    public void showLoseMessage() {

    }

    @Override
    public void showLeaderCards(HashMap<LeaderCard, Boolean> leaderCards) {

    }
}

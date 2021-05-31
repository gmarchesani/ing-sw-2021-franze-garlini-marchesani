package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.messages.*;
import it.polimi.ingsw.model.Card.LeaderCard;
import it.polimi.ingsw.model.enumeration.GameState;
import it.polimi.ingsw.model.enumeration.PhaseTurn;
import it.polimi.ingsw.model.enumeration.ResourceType;
import it.polimi.ingsw.view.*;

import java.security.InvalidParameterException;
import java.util.*;

import static it.polimi.ingsw.messages.MessageType.*;

public class GameController {
    private int maxPlayers;
    private Game gameSession;
    private final HashMap<String, VirtualView> allVirtualView;
    private VirtualView virtualView;
    private TurnController turnController;
    private GameState gameState;
    private String gameId;

    public GameController(){
        this.allVirtualView= new HashMap<String, VirtualView>();
        this.gameState= GameState.INIT;
        maxPlayers=0;

    }

    public HashMap<String, VirtualView> getAllVirtualView() {
        return allVirtualView;
    }

    public void newPlayer(String username, VirtualView virtualView) {
        if(allVirtualView.isEmpty()){
            allVirtualView.put(username, virtualView);
            virtualView.showLoginResult(username,gameId, true);
            virtualView.askPlayersNumber();
        }
        else if(allVirtualView.size()<maxPlayers){
            this.gameSession.addPlayer(new Player(username));
            allVirtualView.put(username, virtualView);
            virtualView.showLoginResult(username, gameId,true);
            if(allVirtualView.size()==maxPlayers){
                startGame();
            }
        }
        else virtualView.showLoginResult(username, gameId,false);

    }

    /** Game state.
     *
     */
    public void getMessage(ClientMessage receivedMessage)throws InvalidParameterException {
            switch (gameState) {
                case INIT:
                    VirtualView virtualView = allVirtualView.get(receivedMessage.getUsername());
                    if(receivedMessage.getMessageType() == PLAYER_NUMBER){

                        PlayersNumberRequest pmsg =(PlayersNumberRequest) receivedMessage;

                        if(pmsg.getPlayersNumber()==1){gameSession=new SinglePlayerGame();
                        gameSession.addPlayer(new Player(pmsg.getUsername()));
                        virtualView.showMessage("Hosting SinglePlayer Game");
                        startGame();
                        }
                        else{
                            gameSession=new Game();
                            gameSession.addPlayer(new Player(pmsg.getUsername()));
                            virtualView.showMessage("Hosting MultiPlayer ("+pmsg.getPlayersNumber()+") Game. \nWaiting for other players...");
                        }
                        maxPlayers=pmsg.getPlayersNumber();
                    }
                case SETUP:
                    setupGame(receivedMessage);
                    break;
                case IN_GAME:
                    inGame(receivedMessage);
                    break;
                case END_GAME:
                    endGame(receivedMessage);
                    break;
                default:
                    for (VirtualView vv : allVirtualView.values()) {
                        vv.showErrorMsg("Error!");
                    }
                    break;
            }

    }

    /**
     * initial phase.
     * @param msg
     */
    public void setupGame(ClientMessage msg){
        int indexPlayer = gameSession.getPlayerListByUsername().indexOf(msg.getUsername());
        Player player=gameSession.getPlayersList().get(indexPlayer);
         if(msg.getMessageType() == STARTING_LEADERS){
            choseLeader((StartingLeadersRequestMsg) msg,player);
        }
        else if(msg.getMessageType()== MessageType.CHOOSE_RES){
            choseInitialRes(player);
        }
        else if(msg.getMessageType()== PLACE_RES){
            placeRes((PlaceMsg) msg, player);
        }

    }

    /**
     * in game phase
     * @param msg
     */
    public void inGame(GeneralMessage msg){
        if(msg.getMessageType() == SHOW_LEADER){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== SHOW_MARKET){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== SHOW_DEV_MARKET){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== SHOW_SLOT){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == SHOW_ALL_SLOT){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == SHOW_RES){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== SHOW_ALL_RES){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== SHOW_FAITH_TRACK){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== SHOW_INFO){
            turnController.setPhaseTurn(PhaseTurn.START_TURN);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == PLAYLEADER){
            turnController.setPhaseTurn(PhaseTurn.ACTION);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == LEADER_REPLY){
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == PICK_DEVCARD){
            turnController.setPhaseTurn(PhaseTurn.ACTION);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == DEVCARD_REPLY){
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== PLACE_CARD){
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == ACTIVATE_PRODUCTION){
            turnController.setPhaseTurn(PhaseTurn.ACTION);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == PRODUCTION_RES){
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== PAY_RES){
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType() == PICK_MARKETRES){
            turnController.setPhaseTurn(PhaseTurn.ACTION);
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== ROW_OR_COL){
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== WHITE_CONVERSION){
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()==PLACE_RES) {
            turnController.getMessage(msg);
        }
        else if(msg.getMessageType()== END_TURN){
            turnController.setPhaseTurn(PhaseTurn.NEXT_TURN);
            turnController.getMessage(msg);
        }
        else{
            allVirtualView.get(turnController.getActivePlayer()).showErrorMsg("Invalid action. Try again!");
            allVirtualView.get(turnController.getActivePlayer()).askAction();
        }
    }

    /**
     * end game phase
     * @param msg
     */
    public void endGame(GeneralMessage msg){
        //TODO: viene fatto fare l'ultimo giro
        //TODO: viene preso il vincitore


        //TODO: chiude la partita
    }

    private void startGame() {
        setGameState(GameState.SETUP);
        turnController=new TurnController(this);
        switch(gameSession.getPlayersList().size()){
            case 3:
                gameSession.getPlayersList().get(2).increaseFaith(1);
            case 4:
                gameSession.getPlayersList().get(3).increaseFaith(2);
                break;
            default:
                //Don't increase faith
        }
        broadcastMessage("Everyone joined the game!");
        drawLeaderCards();
    }

    /**
     * to discard leader's card that the player chose.
     * @param msg
     * @param player (who send message)
     */
    private void choseLeader(StartingLeadersRequestMsg msg, Player player){
        for(int i=0; i<2;i++){
            player.getLeaderCardList().put(msg.getLeaderCard().get(i), false);
        }
        if(turnController.proxPlayer().equals( turnController.firstPlayer())){
            //The First two players don't gain any resources
            turnController.proxPlayer();
            turnController.proxPlayer();
            choseInitialRes();
        }
        else{
            drawLeaderCards();
        }

    }
    private void drawLeaderCards(){
        broadcastMessage("It's "+ turnController.getActivePlayer()+"'s turn to discard leader cards.");
        ArrayList<LeaderCard> leaderCards= new ArrayList<>();
        for(int j=0; j<4; j++){
            leaderCards.add(gameSession.drawCard());
        }
        allVirtualView.get(turnController.getActivePlayer()).askLeaderCardToKeep(leaderCards);

    }

    /**
     * to pick resource that the player chose.
     */
    private void choseInitialRes(){
        String activePlayer= turnController.getActivePlayer();
        if(activePlayer.equals(turnController.firstPlayer())){
            //TODO: ACTUALLY START THE GAME
        }
        else if(activePlayer.equals(turnController.getPlayerOrder().get(2))){
            allVirtualView.get(activePlayer).askInitialRes(1);
        }
        else{
            allVirtualView.get(activePlayer).askInitialRes(2);
        }


    }

    /**
     * to place the resource in the depot that chose the player
     * @param player (who send message)
     */
    private void placeRes(PlaceMsg msg, Player player){
        if(msg.getFloor()>0 && msg.getFloor() <= player.getWarehouse().getDepotList().size()){
            int x = player.placeResources(msg.getRes(), 1, msg.getFloor());
            if(x == -1){
                allVirtualView.get(player.getUsername()).showErrorMsg("Invalid depot!");
                allVirtualView.get(player.getUsername()).askChooseFloor(player.getWarehouse(), msg.getRes());
            }
        }
        else{
            allVirtualView.get(player.getUsername()).showErrorMsg("Invalid depot!");
            allVirtualView.get(player.getUsername()).askChooseFloor(player.getWarehouse(), msg.getRes());
        }
    }


    public boolean isGameStarted(){
        return gameState!=GameState.INIT;
    }
    public Game getGameSession(){
        return gameSession;
    }
    public TurnController getTurnController() {
        return turnController;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void disconnect(String username){
        turnController.disconnect(username);
    }

    public List<String> getInactivePlayers(){
        return turnController.getInactivePlayers();
    }
    public boolean hasInactivePlayers(){
        return turnController.hasInactivePlayers();
    }

    public void reconnect(String username, VirtualView virtualView){
        allVirtualView.put(username, virtualView);
        turnController.reconnect(username);
        broadcastMessage(username + "has reconnected.");
    }
    public ArrayList<ResourceType> availableRes(){
        ArrayList<ResourceType> resource= new ArrayList<>();
        for(ResourceType resourceType: ResourceType.values()){
            if(!resourceType.equals(ResourceType.ANY) && !resourceType.equals(ResourceType.EMPTY) &&
                    !resourceType.equals(ResourceType.FAITH))
                resource.add(resourceType);
        }
        return resource;
    }
    public ArrayList<String> availableResToString() {
        ArrayList<String> resource = new ArrayList<>();
        for (ResourceType resourceType : ResourceType.values()) {
            if (!resourceType.equals(ResourceType.ANY) && !resourceType.equals(ResourceType.EMPTY) &&
                    !resourceType.equals(ResourceType.FAITH))
                resource.add(resourceType.toString());
        }
        return resource;
    }

    public void broadcastMessage(String message) {
        for (VirtualView vv : allVirtualView.values()) {
            vv.showMessage(message);
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}

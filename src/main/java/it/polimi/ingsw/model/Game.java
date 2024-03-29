package it.polimi.ingsw.model;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.Action.ActionToken;
import it.polimi.ingsw.model.Board.CardMarket;
import it.polimi.ingsw.model.Board.FaithTrack;
import it.polimi.ingsw.model.Board.FaithZone;
import it.polimi.ingsw.model.Board.Market;
import it.polimi.ingsw.model.Card.*;
import it.polimi.ingsw.model.enumeration.Color;
import it.polimi.ingsw.model.enumeration.GameState;
import it.polimi.ingsw.model.enumeration.ResourceType;
import it.polimi.ingsw.view.VirtualView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class is the intermediary between all the game's components.
 */
public class Game {
    private Market market ;
    private FaithTrack faithTrack ;
    private CardMarket cardMarket;
    private ArrayList<Player> playersList;
    private ArrayList<LeaderCard> leaderCards;

    /**
     * Default constructor.
     */
    public Game() {
        playersList = new ArrayList<Player>();
        market = new Market(generateMarbles());
        faithTrack = new FaithTrack(generateFaithTrack(), generateVPspaces());
        cardMarket = new CardMarket(generateDevCardDeck());
        leaderCards = generateLeaderCards();
        Collections.shuffle(leaderCards);
    }

    /**
     * This method adds a player in the playersList.
     * @param player the player who has just joined.
     */
    public void addPlayer(Player player) {
        if(playersList.size()<4){
            playersList.add(player);
        }
    }

    /**
     * This method is used to draw a Leader Card from the deck.
     * @return The drawn Leader Card.
     */
    public LeaderCard drawCard(){
        LeaderCard temp = leaderCards.get(0);
        leaderCards.remove(0);
        return temp;
    }

    /**
     * Calls the method pickResources from the class Market and returns the resources.
     * @param rowOrCol 'c' stands for column, 'r' stands for row.
     * @param rowOrColNumber the number of the row/column. Starts from 0.
     * @param conversion the ResourceType the player can obtain with the white marble. It must be null if the player doesn't have any conversion.
     * @return a HashMap representing the resources list.
     */
    public HashMap<ResourceType, Integer> pickMarketRes(char rowOrCol, int rowOrColNumber, ResourceType conversion) {
        return market.pickResources(rowOrCol,rowOrColNumber,conversion);
    }

    /**
     * This method calls the returnDevCard method from the DevMarket class.
     * @param card the card to be added.
     */
    public void returnDevCard(DevCard card){
        cardMarket.returnDevCard(card);
    }

    /**
     * Returns the player associated to the username passed as parameter.
     * @param username the username of the player.
     * @return the player with the given username.
     */
    public Player getPlayer(String username){
        int indexPlayer = getPlayerListByUsername().indexOf(username);
        return getPlayersList().get(indexPlayer);
    }

    /**
     * Pick the Development Card with the features passed as parameters.
     * @param color the chosen Development Card color.
     * @param level the chosen Development Card level.
     * @return the chosen Development Card.
     */
    public DevCard pickDevCard(Color color, int level){
        return cardMarket.pickCard(color, level);
    }

    /**
     * Ends the game and communicate the result.
     * @return the results in a Hash Map.
     */
    public LinkedHashMap<String, Integer> endGame() {
        //Turn has already let players to play the last turn.
        HashMap<String, Integer> userPoints=new HashMap<>();
        for(Player player : playersList){
            userPoints.put(player.getUsername(), player.getFinalVP()+faithTrack.getAssociatedVP(player.getFaithSpace()));
        }
        ArrayList<String> keys = new ArrayList<>(userPoints.keySet());
        ArrayList<Integer> values = new ArrayList<>(userPoints.values());
        Collections.sort(values);
        Collections.reverse(values);
        Collections.sort(keys);
        int tie=-1;
        for(int i : values){
            if(i==values.get(0)){
                tie++;
            }
        }
        LinkedHashMap<String,Integer> sortedPoints= new LinkedHashMap<>();

        Iterator<Integer> sortInt = values.iterator();
        while (sortInt.hasNext()) {
            Integer points = sortInt.next();
            Iterator<String> user= keys.iterator();

            while (user.hasNext()) {
                String key = user.next();
                Integer comparePoints1 = userPoints.get(key);
                Integer comparePoints2 = points;

                if (comparePoints1.equals(comparePoints2)) {
                    user.remove();
                    sortedPoints.put(key, points);
                    break;
                }
            }
        }
        int max=0;
        int index=0;
        ArrayList<String> users=new ArrayList<>(sortedPoints.keySet());
        for(int i=0; i<tie;i++){
            if(getPlayer(users.get(i+0)).getResourceNum()>max){
                max=getPlayer(users.get(i+0)).getResourceNum();
                index=i;
            }
        }
        LinkedHashMap<String,Integer> finalSorted=new LinkedHashMap<>();
        finalSorted.put(users.get(index), sortedPoints.get(users.get(index)));
        for(String s: sortedPoints.keySet()){
            if(!s.equals(users.get(index)) ){
                finalSorted.put(s, sortedPoints.get(s));
            }
        }
        return finalSorted;
    }

    /**
     * Takes the development cards from the json file and generates it.
     * @return returns the deck of leader cards.
     */
    private ArrayList<DevCard> generateDevCardDeck() {

        //DevCard generation
        String devCardListJson ="";
        ArrayList<DevCard> devCardDeck = null;

        try {
            InputStream is = Game.class.getResourceAsStream("/dev-cards.JSON");
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            devCardListJson = sb.toString();
        } catch(IOException e) {
            e.printStackTrace();
        }
        Type foundListType = new TypeToken<ArrayList<DevCard>>(){}.getType();
        devCardDeck = new Gson().fromJson(devCardListJson, foundListType);
        return devCardDeck;

    }

    /**
     * Takes the faith track from the json file and generates it.
     * @return returns the faith track.
     */
    private ArrayList<FaithZone> generateFaithTrack() {
        //Faith Zone generation
        String faithZonesJson = "";
        ArrayList<FaithZone> faithZones = null;

        try {
            InputStream is = Game.class.getResourceAsStream("/faith-track.JSON");
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            faithZonesJson = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type foundListType = new TypeToken<ArrayList<FaithZone>>(){}.getType();
        faithZones = new Gson().fromJson(faithZonesJson, foundListType);
        return faithZones;
    }

    /**
     * Takes the victory points spaces from the json file and generates it.
     * @return returns the victory point spaces.
     */
    private LinkedHashMap<Integer, Integer> generateVPspaces() {
        //VPspaces generation
        String VPspacesJson = "";
        LinkedHashMap<Integer, Integer> VPspaces = null;

        try {
            InputStream is = Game.class.getResourceAsStream("/vp-spaces.JSON");
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            VPspacesJson = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
            Type foundHashMapType = new TypeToken<LinkedHashMap<Integer, Integer>>(){}.getType();
            VPspaces = new Gson().fromJson(VPspacesJson, foundHashMapType);
            return VPspaces;

    }

    /**
     * Takes the marbles from the json file and generates it.
     * @return returns all the marbles.
     */
    private ArrayList<ResourceType> generateMarbles() {
        //Marble generation
        String marbleJson ="";
        ArrayList<ResourceType> totalMarbles = null;

        try {
            InputStream is = Game.class.getResourceAsStream("/marbles.JSON");
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            marbleJson = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type foundHashMapType = new TypeToken<ArrayList<ResourceType>>(){}.getType();
        totalMarbles = new Gson().fromJson(marbleJson, foundHashMapType);
        return totalMarbles;
    }

    /**
     * Takes the leader cards from the json files and generates it.
     * @return returns the deck of leader cards.
     */
    private ArrayList<LeaderCard> generateLeaderCards() {
        //LeaderCard generation
        String leaderJson="";
        ArrayList<LeaderCard> leaderCardDeck = null;

        //LeaderDepot generation
        try {
                InputStream is = Game.class.getResourceAsStream("/leader-depot.JSON");
                StringBuilder sb = new StringBuilder();
                for (int ch; (ch = is.read()) != -1; ) {
                    sb.append((char) ch);
                }
                leaderJson = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type foundHashMapType = new TypeToken<ArrayList<LeaderDepot>>(){}.getType();
        leaderCardDeck = new Gson().fromJson(leaderJson, foundHashMapType);


        //LeaderDiscount generation
        try {
            InputStream is = Game.class.getResourceAsStream("/leader-discount.JSON");
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            leaderJson = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        foundHashMapType = new TypeToken<ArrayList<LeaderDiscount>>(){}.getType();
        leaderCardDeck.addAll(new Gson().fromJson(leaderJson, foundHashMapType));

        //LeaderMarble generation
        try {
            InputStream is = Game.class.getResourceAsStream("/leader-marble.JSON");
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            leaderJson = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        foundHashMapType = new TypeToken<ArrayList<LeaderMarble>>(){}.getType();
        leaderCardDeck.addAll(new Gson().fromJson(leaderJson, foundHashMapType));

        //LeaderProduction generation
        try {
            InputStream is = Game.class.getResourceAsStream("/leader-production.JSON");
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = is.read()) != -1; ) {
                sb.append((char) ch);
            }
            leaderJson = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        foundHashMapType = new TypeToken<ArrayList<LeaderProduction>>(){}.getType();
        leaderCardDeck.addAll(new Gson().fromJson(leaderJson, foundHashMapType));

        return leaderCardDeck;
    }

    /**
     * Used to obtain all the players' usernames.
     * @return an ArrayList of usernames.
     */
    public ArrayList<String> getPlayerListByUsername() {
        ArrayList<String> playerList = new ArrayList<>();
        for (int i = 0; i < getPlayersList().size(); i++) {
            playerList.add(getPlayersList().get(i).getUsername());
        }
        return playerList;
    }

    public Market getMarket() {
        return market;
    }

    public CardMarket getCardMarket() {
        return cardMarket;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public boolean checkLoss(){
        return false;
    }

    public ActionToken drawToken(){
        return null;
    }


    /**
     * Update the FaithTrack and returns a boolean that represents if a FaithZone has been activated.
     * @return true if a FaithZone has been activated, otherwise false.
     */
    public boolean updateFaithTrack(){
        ArrayList<Player> vpWinners=new ArrayList<Player>();
        int thresholdL=getFaithTrack().getNextFaithZone().getStart();
        boolean trigger=false;
        int thresholdH=getFaithTrack().getNextFaithZone().getEnd();

        for(Player player : playersList){
            if(player.getFaithSpace()>=thresholdL){
                vpWinners.add(player);
            }
            if(player.getFaithSpace()>=thresholdH){
                trigger=true;
            }

        }

        if (trigger){
            int vp=getFaithTrack().getNextFaithZone().getFaithZoneVP();
            for(Player p: vpWinners){
                p.increaseVP(vp);
            }
           getFaithTrack().getNextFaithZone().setActivated();
        }
        return trigger;
    }


    /**
     * Returns the last activated FaithZone.
     * @return the last activated FaithZone.
     */
    public int lastActivatedFaithZone() {
       if( faithTrack.indexOfNextFaithZone()<0){
           return 2;
       }
       else return faithTrack.indexOfNextFaithZone()-1;
    }

    /**
     * Used to get the faith space.
     * @return the players' faith space.
     */
    public ArrayList<Integer> getFaith(String username){
        ArrayList<Integer> faith=new ArrayList<>();
        faith.add(getPlayer(username).getFaithSpace());
        return faith;
    }

    /**
     * Increases the faith space of a player.
     * @param faith points to be added.
     * @param activateOnYourself true if points must be added to the active player, false if must be added only to others player instead of the active one.
     * @param username username of the active player.
     * @return true if a FaithZone has been activated, false otherwise.
     */
    public boolean increaseFaith(int faith, boolean activateOnYourself, String username) {
        Player active = getPlayer(username);
        if (activateOnYourself) {
            active.increaseFaith(faith);
        } else {
            for (Player player : playersList) {
                if (player != active) {
                    player.increaseFaith(faith);
                }
            }
        }
        return updateFaithTrack();
    }

    /**
     * Returns the Victory Points for each player.
     * @return all the victory points for each player.
     */
    public HashMap<String, Integer> getVictoryPoints(){
        HashMap<String,Integer> points=new HashMap<>();
        for(Player p : playersList){
            points.put(p.getUsername(), p.getDevCardSlot().getCardPoints()+p.getLeaderVp()+p.getVictoryPoint());
        }
        return points;
    }
}

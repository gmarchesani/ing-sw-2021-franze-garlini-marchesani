package it.polimi.ingsw.view.GUI.scene;


import it.polimi.ingsw.controller.TurnController;
import it.polimi.ingsw.model.Card.DevCard;
import it.polimi.ingsw.model.Card.LeaderCard;
import it.polimi.ingsw.model.enumeration.ResourceType;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.Gui;
import it.polimi.ingsw.view.GUI.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardSceneController extends ObservableView implements GenericSceneController {
    @FXML
    private ImageView corner;
    @FXML
    private ImageView res0x0;
    @FXML
    private ImageView res1x0;
    @FXML
    private ImageView res2x0;
    @FXML
    private ImageView res3x0;
    @FXML
    private ImageView res0x1;
    @FXML
    private ImageView res1x1;
    @FXML
    private ImageView res2x1;
    @FXML
    private ImageView res3x1;
    @FXML
    private ImageView res0x2;
    @FXML
    private ImageView res1x2;
    @FXML
    private ImageView res2x2;
    @FXML
    private ImageView res3x2;
    @FXML
    private ImageView card0x0;
    @FXML
    private ImageView card1x0;
    @FXML
    private ImageView card2x0;
    @FXML
    private ImageView card3x0;
    @FXML
    private ImageView card0x1;
    @FXML
    private ImageView card1x1;
    @FXML
    private ImageView card2x1;
    @FXML
    private ImageView card3x1;
    @FXML
    private ImageView card0x2;
    @FXML
    private ImageView card1x2;
    @FXML
    private ImageView card2x2;
    @FXML
    private ImageView card3x2;
    @FXML
    private ImageView img_leader1;
    @FXML
    private ImageView img_leader2;
    @FXML
    private ImageView img_space1;
    @FXML
    private ImageView img_space2;
    @FXML
    private ImageView img_space3;
    @FXML
    private ImageView img_space4;
    @FXML
    private ImageView img_space5;
    @FXML
    private ImageView img_space6;
    @FXML
    private ImageView img_space7;
    @FXML
    private ImageView img_space8;
    @FXML
    private ImageView img_space9;
    @FXML
    private ImageView img_space10;
    @FXML
    private ImageView img_space11;
    @FXML
    private ImageView img_space12;
    @FXML
    private ImageView img_space13;
    @FXML
    private ImageView img_space14;
    @FXML
    private ImageView img_space15;
    @FXML
    private ImageView img_space16;
    @FXML
    private ImageView img_space17;
    @FXML
    private ImageView img_space18;
    @FXML
    private ImageView img_space19;
    @FXML
    private ImageView img_space20;
    @FXML
    private ImageView img_space21;
    @FXML
    private ImageView img_space22;
    @FXML
    private ImageView img_space23;
    @FXML
    private ImageView img_space24;
    @FXML
    private Label name1;
    @FXML
    private Label name2;
    @FXML
    private Label name3;
    @FXML
    private Label name4;
    @FXML
    private Label name5;
    @FXML
    private Label name6;
    @FXML
    private Label name7;
    @FXML
    private Label name8;
    @FXML
    private Label name9;
    @FXML
    private Label name10;
    @FXML
    private Label name11;
    @FXML
    private Label name12;
    @FXML
    private Label name13;
    @FXML
    private Label name14;
    @FXML
    private Label name15;
    @FXML
    private Label name16;
    @FXML
    private Label name17;
    @FXML
    private Label name18;
    @FXML
    private Label name19;
    @FXML
    private Label name20;
    @FXML
    private Label name21;
    @FXML
    private Label name22;
    @FXML
    private Label name23;
    @FXML
    private Label name24;
    @FXML
    private Button end_turn;
    @FXML
    private Button btmResources;
    @FXML
    private Button btmDevCard;
    @FXML
    private Button btmProduction;
    @FXML
    private Button btmLeader;
    @FXML
    private Button btm_vp;
    @FXML
    private Button btmPlayers;



    private ArrayList<ImageView> cards = new ArrayList<>();
    private ArrayList<ImageView> market = new ArrayList<>();
    private ResourceType cornerMarket;
    private ArrayList<DevCard> cardMarket = new ArrayList<>();
    private ArrayList <String> numPlayer = new ArrayList<>();
    private HashMap<String,ArrayList<LeaderCard>> leaderCards = new HashMap<>();
    private ArrayList<ImageView> imgLeader = new ArrayList<>();
    private HashMap<String, Integer> faithTrack = new HashMap<>();
    private String activePlayer;
    private int leaderAction = 0;

    @FXML
    public void initialize(){
        updateMarket();
        updateCardMarket();
        updateLeader();
        updateFaithTrack();

        numPlayer = Gui.getPlayerList();
        if(numPlayer.size()==1) {
            btmPlayers.setDisable(true);
            btmPlayers.setVisible(false);

        }
        btmDevCard.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onBuyCardBtm);
        btmResources.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onResourcesBtm);
        btmProduction.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onProductionBtm);
        btmLeader.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onLeaderBtm);
        btm_vp.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onVpBtm);
        btmPlayers.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onPlayerBtm);
        end_turn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onEndTurnBtm);
    }

    public void onBuyCardBtm(Event event){
        btmProduction.setDisable(true);
        btmResources.setDisable(true);
        btmDevCard.setDisable(true);
        notifyObserver(obs -> obs.updateAction(1));
    }
    public void onResourcesBtm(Event event){
        btmProduction.setDisable(true);
        btmResources.setDisable(true);
        btmDevCard.setDisable(true);
        notifyObserver(obs -> obs.updateAction(0));
    }
    public void onProductionBtm(Event event){
        btmProduction.setDisable(true);
        btmResources.setDisable(true);
        btmDevCard.setDisable(true);
        notifyObserver(obs -> obs.updateAction(2));
    }
    public void onLeaderBtm(Event event){
        leaderAction++;
        if(leaderAction==2){
            btmLeader.setDisable(true);
        }
        notifyObserver(obs -> obs.updateAction(3));
    }
    public void onVpBtm(Event event){
        notifyObserver(obs -> obs.updateAction(11));
    }
    public void onPlayerBtm(Event event){

    }
    public void onEndTurnBtm(Event event){
        notifyObserver(obs -> obs.updateAction(-1));
    }

    private void updateCardMarket(){
        cardMarket.addAll(Gui.getCardMarket());
        cards.add(card0x0);
        cards.add(card0x1);
        cards.add(card0x2);
        cards.add(card1x0);
        cards.add(card1x1);
        cards.add(card1x2);
        cards.add(card2x0);
        cards.add(card2x1);
        cards.add(card2x2);
        cards.add(card3x0);
        cards.add(card3x1);
        cards.add(card3x2);

        int i=0;

        for (DevCard devCard : cardMarket){
            Image image = new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCard.getId()-1) + ".png"));
            cards.get(i).setImage(image);
            cards.get(i).setVisible(true);
            i++;
        }
    }

    private void updateLeader(){
        leaderCards.putAll(Gui.getChosenLeader());
        imgLeader.add(img_leader1);
        imgLeader.add(img_leader2);
        int i=0;
        for(LeaderCard ld: leaderCards.get(Gui.getActivePlayer())){
            Image image = new Image(MainApp.class.getResourceAsStream("/images/leaderCards/leader_Id" + (ld.getId()-1) + ".png"));
            imgLeader.get(i).setImage(image);
            imgLeader.get(i).setVisible(true);
            i++;
        }
    }

    private void updateMarket(){
        int n=0;
        market.add(res0x0);
        market.add(res0x1);
        market.add(res0x2);
        market.add(res1x0);
        market.add(res1x1);
        market.add(res1x2);
        market.add(res2x0);
        market.add(res2x1);
        market.add(res2x2);
        market.add(res3x0);
        market.add(res3x1);
        market.add(res3x2);

        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                Image image;
                if(Gui.getMarket()[i][j] == ResourceType.COIN){
                    image = new Image(MainApp.class.getResourceAsStream("/images/yellowMarble.png"));
                }
                else if(Gui.getMarket()[i][j] == ResourceType.SERVANT){
                    image = new Image(MainApp.class.getResourceAsStream("/images/purpleMarble.png"));
                }
                else if(Gui.getMarket()[i][j] == ResourceType.SHIELD){
                    image = new Image(MainApp.class.getResourceAsStream("/images/blueMarble.png"));
                }
                else if(Gui.getMarket()[i][j] == ResourceType.STONE){
                    image = new Image(MainApp.class.getResourceAsStream("/images/greyMarble.png"));
                }
                else if (Gui.getMarket()[i][j] == ResourceType.EMPTY){
                    image = new Image(MainApp.class.getResourceAsStream("/images/whiteMarble.png"));
                }
                else{
                    image = new Image(MainApp.class.getResourceAsStream("/images/redMarble.png"));
                }
                market.get(n).setImage(image);
                market.get(n).setVisible(true);
                n++;

            }
        }
        cornerMarket = Gui.getCornerMarble();
        if(cornerMarket == ResourceType.COIN){
            corner.setImage(new Image("/images/yellowMarble.png"));
        }
        else if(cornerMarket == ResourceType.EMPTY){
            corner.setImage(new Image("/images/whiteMarble.png"));
        }
        else if(cornerMarket == ResourceType.FAITH){
            corner.setImage(new Image("/images/redMarble.png"));
        }
        else if(cornerMarket == ResourceType.SERVANT){
            corner.setImage(new Image("/images/purpleMarble.png"));
        }
        else if(cornerMarket == ResourceType.SHIELD){
            corner.setImage(new Image("/images/blueMarble.png"));
        }
        else{
            corner.setImage(new Image("/images/greyMarble.png"));
        }
    }

    private void updateFaithTrack(){
        this.faithTrack.putAll(Gui.getFaithTrack());

        for(String name: faithTrack.keySet()){
            Image image = new Image(MainApp.class.getResourceAsStream("/images/cross.png"));
            switch (faithTrack.get(name)){
                case 1:
                    img_space1.setImage(image);
                    img_space1.setVisible(true);
                    name1.setText(name1.getText() + " " + name);
                case 2:
                    img_space2.setImage(image);
                    img_space2.setVisible(true);
                    name2.setText(name2.getText() + " " + name);
                case 3:
                    img_space3.setImage(image);
                    img_space3.setVisible(true);
                    name3.setText(name3.getText() + " " + name);
                case 4:
                    img_space4.setImage(image);
                    img_space4.setVisible(true);
                    name4.setText(name4.getText() + " " + name);
                case 5:
                    img_space5.setImage(image);
                    img_space5.setVisible(true);
                    name5.setText(name5.getText() + " " + name);
                case 6:
                    img_space6.setImage(image);
                    img_space6.setVisible(true);
                    name6.setText(name6.getText() + " " + name);
                case 7:
                    img_space7.setImage(image);
                    img_space7.setVisible(true);
                    name7.setText(name7.getText() + " " + name);
                case 8:
                    img_space8.setImage(image);
                    img_space8.setVisible(true);
                    name8.setText(name8.getText() + " " + name);
                case 9:
                    img_space9.setImage(image);
                    img_space9.setVisible(true);
                    name9.setText(name9.getText() + " " + name);
                case 10:
                    img_space10.setImage(image);
                    img_space10.setVisible(true);
                    name10.setText(name10.getText() + " " + name);
                case 11:
                    img_space11.setImage(image);
                    img_space11.setVisible(true);
                    name11.setText(name11.getText() + " " + name);

                case 12:
                    img_space12.setImage(image);
                    img_space12.setVisible(true);
                    name12.setText(name12.getText() + " " + name);

                case 13:
                    img_space13.setImage(image);
                    img_space13.setVisible(true);
                    name13.setText(name13.getText() + " " + name);

                case 14:
                    img_space14.setImage(image);
                    img_space14.setVisible(true);
                    name14.setText(name14.getText() + " " + name);

                case 15:
                    img_space15.setImage(image);
                    img_space15.setVisible(true);
                    name15.setText(name15.getText() + " " + name);

                case 16:
                    img_space16.setImage(image);
                    img_space16.setVisible(true);
                    name16.setText(name16.getText() + " " + name);

                case 17:
                    img_space17.setImage(image);
                    img_space17.setVisible(true);
                    name17.setText(name17.getText() + " " + name);

                case 18:
                    img_space18.setImage(image);
                    img_space18.setVisible(true);
                    name18.setText(name18.getText() + " " + name);

                case 19:
                    img_space19.setImage(image);
                    img_space19.setVisible(true);
                    name19.setText(name19.getText() + " " + name);

                case 20:
                    img_space20.setImage(image);
                    img_space20.setVisible(true);
                    name20.setText(name20.getText() + " " + name);

                case 21:
                    img_space21.setImage(image);
                    img_space21.setVisible(true);
                    name21.setText(name21.getText() + " " + name);

                case 22:
                    img_space22.setImage(image);
                    img_space22.setVisible(true);
                    name22.setText(name22.getText() + " " + name);

                case 23:
                    img_space23.setImage(image);
                    img_space23.setVisible(true);
                    name23.setText(name23.getText() + " " + name);

                case 24:
                    img_space24.setImage(image);
                    img_space24.setVisible(true);
                    name24.setText(name24.getText() + " " + name);
                default:
                    break;
            }
        }
    }
}
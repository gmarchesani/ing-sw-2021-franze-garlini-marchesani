package it.polimi.ingsw.view.GUI.scene;

import com.sun.tools.javac.Main;
import it.polimi.ingsw.model.Card.LeaderCard;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class ChooseLeaderToPlay extends ObservableView implements GenericSceneController {

    @FXML
    private RadioButton check_leader1;
    @FXML
    private RadioButton check_leader2;
    @FXML
    private ImageView leader1;
    @FXML
    private ImageView leader2;
    @FXML
    private Button btm_play;
    @FXML
    private Button btm_discard;

    private final ToggleGroup group = new ToggleGroup();
    private ArrayList<LeaderCard> allLeaderCards = new ArrayList<>();
    private HashMap<LeaderCard,Boolean> leaderCards = new HashMap<>();
    private ArrayList<ImageView> leaders = new ArrayList<>();
    private ArrayList<LeaderCard> restLeader;
    private LeaderCard chosenLeaderDiscard;
    @FXML
    public void initialize(){
        btm_play.setDisable(false);
        leaders.add(leader1);
        leaders.add(leader2);
        check_leader1.setToggleGroup(group);
        check_leader2.setToggleGroup(group);
        restLeader = allLeaderCards;
        int count =0;
        for(LeaderCard ld: allLeaderCards){
            Image image = new Image(MainApp.class.getResourceAsStream("/images/leaderCards/leader_Id" + (ld.getId()-1) + ".png"));
            leaders.get(count).setImage(image);
            count++;
        }
        if(allLeaderCards.size()==1){
            check_leader2.setVisible(false);
            leaders.get(1).setVisible(false);
        }


        btm_play.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onPlayBtm);
        btm_discard.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onDiscardBtm);

    }

    private void onPlayBtm(Event event){

        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String id = selectedRadioButton.getId();

        if(id.equals("leader1")){
            notifyObserver(obs -> obs.updatePlayLeaderCard(allLeaderCards.get(0), 'p'));
        }
        else{
            notifyObserver(obs -> obs.updatePlayLeaderCard(allLeaderCards.get(1), 'p'));
        }

    }
    private void onDiscardBtm(Event event){

        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String id = selectedRadioButton.getId();

        if(id.equals("leader1")){
            chosenLeaderDiscard = allLeaderCards.get(0);
            notifyObserver(obs -> obs.updatePlayLeaderCard(allLeaderCards.get(0), 'd'));
        }
        else{
            chosenLeaderDiscard = allLeaderCards.get(1);
            notifyObserver(obs -> obs.updatePlayLeaderCard(allLeaderCards.get(1), 'd'));
        }

    }

    public void updateLeaders(ArrayList<LeaderCard> leaders){
        this.allLeaderCards = leaders;
    }
    public LeaderCard getChosenLeaderDiscard(){
        return chosenLeaderDiscard;
    }
    public void setAllLeaderCards(ArrayList<LeaderCard> allLeaderCards){
        this.allLeaderCards = allLeaderCards;
    }
}
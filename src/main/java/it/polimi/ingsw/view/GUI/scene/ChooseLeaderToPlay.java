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
    private ArrayList<LeaderCard> leaderCards = new ArrayList<>();
    @FXML
    public void initialize(){
        leader1.setImage(null);
        leader2.setImage(null);
        check_leader1.setToggleGroup(group);
        check_leader2.setToggleGroup(group);

        if(leaderCards.size()==2){
            Image image1 = new Image(MainApp.class.getResourceAsStream("/images/leaderCards/leader_Id" + (leaderCards.get(0).getId()-1) + ".png"));
            Image image2 = new Image(MainApp.class.getResourceAsStream("/images/leaderCards/leader_Id" + (leaderCards.get(1).getId()-1) + ".png"));
            leader1.setImage(image1);
            leader2.setImage(image2);
        }
        else if(leaderCards.size()==1){
            leader2.setImage(null);
            check_leader2.setDisable(true);
            check_leader2.setVisible(false);
            Image image1 = new Image(MainApp.class.getResourceAsStream("/images/leaderCards/leader_Id" + (leaderCards.get(0).getId()-1) + ".png"));
            leader1.setImage(image1);
        }
        btm_play.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onPlayBtm);
        btm_discard.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onDiscardBtm);

    }

    private void onPlayBtm(Event event){
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String id = selectedRadioButton.getId();
        if(id.equals("leader1")){
            notifyObserver(obs -> obs.updatePlayLeaderCard(leaderCards.get(0), 'p'));
        }
        else{
            notifyObserver(obs -> obs.updatePlayLeaderCard(leaderCards.get(1), 'p'));
        }

    }
    private void onDiscardBtm(Event event){
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String id = selectedRadioButton.getId();
        if(id.equals("leader1")){
            notifyObserver(obs -> obs.updatePlayLeaderCard(leaderCards.get(0), 'd'));
        }
        else{
            notifyObserver(obs -> obs.updatePlayLeaderCard(leaderCards.get(1), 'd'));
        }

    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards){
        this.leaderCards = leaderCards;
    }
}

package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.enumeration.ResourceType;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.GuiManager;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This Scene Controller is used to asks the initial resources the player wants to get.
 */
public class AnySceneController extends ObservableView implements GenericSceneController {
    private final ToggleGroup group = new ToggleGroup();
    private ResourceType anyRes;
    private ArrayList<ResourceType> extraDepot =new ArrayList<>();
    private HashMap<ResourceType, Integer> resToPlace = new HashMap<>();
    @FXML
    private RadioButton coin;
    @FXML
    private RadioButton servant;
    @FXML
    private RadioButton shield;
    @FXML
    private RadioButton stone;
    @FXML
    private Button btm_next;
    @FXML
    public void initialize(){
        coin.setToggleGroup(group);
        servant.setToggleGroup(group);
        shield.setToggleGroup(group);
        stone.setToggleGroup(group);
        btm_next.addEventHandler(MouseEvent.MOUSE_CLICKED, this:: onNextBtm);
    }

    /**
     * Handles the click on the Next button.
     * @param event the mouse click event.
     */
    private void onNextBtm(Event event){
        coin.setDisable(true);
        stone.setDisable(true);
        shield.setDisable(true);
        servant.setDisable(true);
        btm_next.setDisable(true);
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String id = selectedRadioButton.getId();
        switch (id){
            case "coin":
                anyRes = ResourceType.COIN;
                break;
            case "servant":
                anyRes = ResourceType.SERVANT;
                break;
            case "shield":
                anyRes = ResourceType.SHIELD;
                break;
            default:
                anyRes = ResourceType.STONE;

                break;
        }
        resToPlace.put(anyRes, 1);
        PlaceResourcesSceneController prsc = new PlaceResourcesSceneController();
        prsc.addAllObservers(observers);
        prsc.setResToPlace(resToPlace);
        Platform.runLater(() ->
                GuiManager.changeRootPane(prsc, "/fxml/place_resources_scene")
        );
    }

    public ResourceType getAny(){
        return anyRes;
    }
    public void setResToPlace(HashMap<ResourceType, Integer> resToPlace){
        this.resToPlace=resToPlace;
    }
    public void setExtraDepot(ArrayList<ResourceType> extraDepot){
        this.extraDepot=extraDepot;
    }
}

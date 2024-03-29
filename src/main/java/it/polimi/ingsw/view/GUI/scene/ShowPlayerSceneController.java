package it.polimi.ingsw.view.GUI.scene;

import it.polimi.ingsw.model.Card.DevCardSlot;
import it.polimi.ingsw.model.Card.LeaderCard;
import it.polimi.ingsw.model.enumeration.ResourceType;
import it.polimi.ingsw.observer.ObservableView;
import it.polimi.ingsw.view.GUI.MainApp;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This Scene Controller is used to show the requested player's board.
 */
public class ShowPlayerSceneController extends ObservableView implements GenericSceneController {

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
    private ImageView img_depot1_res1;
    @FXML
    private ImageView img_depot2_res3;
    @FXML
    private ImageView img_depot3_res1;
    @FXML
    private ImageView img_depot1_res2;
    @FXML
    private ImageView img_depot2_res2;
    @FXML
    private ImageView img_depot2_res1;
    @FXML
    private ImageView strong_res1;
    @FXML
    private ImageView strong_res2;
    @FXML
    private ImageView strong_res3;
    @FXML
    private ImageView strong_res4;
    @FXML
    private Label strong_quantity1;
    @FXML
    private Label strong_quantity2;
    @FXML
    private Label strong_quantity3;
    @FXML
    private Label strong_quantity4;
    @FXML
    private ImageView pope1;
    @FXML
    private ImageView pope2;
    @FXML
    private ImageView pope3;
    @FXML
    private ImageView img_slot0_card0;
    @FXML
    private ImageView img_slot0_card1;
    @FXML
    private ImageView img_slot0_card2;
    @FXML
    private ImageView img_slot1_card0;
    @FXML
    private ImageView img_slot1_card1;
    @FXML
    private ImageView img_slot1_card2;
    @FXML
    private ImageView img_slot2_card0;
    @FXML
    private ImageView img_slot2_card1;
    @FXML
    private ImageView img_slot2_card2;
    @FXML
    private Button btm_ok;
    @FXML
    private ImageView img_leader1;
    @FXML
    private ImageView img_leader2;
    @FXML
    private Label username;

    private String chosenPlayer;
    private int faithSpace;
    private HashMap<Integer, ResourceType> depotToResource = new HashMap<>();
    private HashMap<Integer, Integer> depotToQuantity = new HashMap<>();
    private HashMap<ResourceType, Integer> strongbox = new HashMap<>();
    private DevCardSlot devCardSlot= new DevCardSlot();
    private ArrayList<LeaderCard> playedLeaderCards = new ArrayList<>();
    private String self;
    private int remainingLeaderCards;


    @FXML
    public void initialize(){
        updateFaithTrack();
        updateSlots();
        updateStrongbox();
        updateWarehouse();
        updateLeaderCard();
        username.setText(chosenPlayer + " 's board");
        btm_ok.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onOkBtm);
    }

    /**
     * Handles the click on the Ok button.
     * @param event the mouse click event.
     */
    private void onOkBtm(Event event){
        notifyObserver(obs-> obs.updateShowPlayer(self));
    }

    /**
     * Updates the images of leaders cards.
     */
    private void updateLeaderCard(){
        ArrayList<ImageView> leaderCards=new ArrayList<>();
        leaderCards.add(img_leader1);
        leaderCards.add(img_leader2);
        int count = 0;
        for(LeaderCard ld : playedLeaderCards){
            Image image = new Image(MainApp.class.getResourceAsStream("/images/leaderCards/leader_Id" + (ld.getId()-1) + ".png"));
            leaderCards.get(count).setImage(image);
            count++;
        }
        if(playedLeaderCards.size()==0 && remainingLeaderCards == 0){
            for (ImageView imageView : leaderCards){
                imageView.setOpacity(1);
                imageView.setVisible(false);
            }
        }
        else if(playedLeaderCards.size()==1 && remainingLeaderCards == 0){
            leaderCards.get(1).setVisible(false);
        }
    }

    /**
     * Updates the images of resources in warehouse.
     */
    private void updateWarehouse(){
        String res="COIN";
        img_depot1_res1.setImage(null);
        img_depot1_res2.setImage(null);
        img_depot2_res3.setImage(null);
        img_depot2_res1.setImage(null);
        img_depot2_res2.setImage(null);
        img_depot3_res1.setImage(null);
        for (Integer depot : depotToResource.keySet()) {
            for (int i = 1; i <= depotToQuantity.get(depot); i++) {
                if(depotToResource.get(depot).equals(ResourceType.SHIELD)){
                    Image image = new Image(MainApp.class.getResourceAsStream("/images/shield.png"));
                    setImage(depot, depotToQuantity.get(depot), image);
                }
                else if(depotToResource.get(depot).equals(ResourceType.SERVANT)){
                    Image image = new Image(MainApp.class.getResourceAsStream("/images/servant.png"));
                    setImage(depot, depotToQuantity.get(depot), image);
                }
                else if(depotToResource.get(depot).equals(ResourceType.STONE)){
                    Image image = new Image(MainApp.class.getResourceAsStream("/images/stone.png"));
                    setImage(depot, depotToQuantity.get(depot), image);
                }
                else if(depotToResource.get(depot).equals(ResourceType.COIN)){
                    Image image = new Image(MainApp.class.getResourceAsStream("/images/coin.png"));
                    setImage(depot, depotToQuantity.get(depot), image);
                }

                if(depotToResource.get(depot).equals(ResourceType.SHIELD)){
                    Image image = new Image(MainApp.class.getResourceAsStream("/images/shield.png"));
                    setImage(depot, depotToQuantity.get(depot), image);
                }
                else if(depotToResource.get(depot).equals(ResourceType.SERVANT)) {
                    Image image = new Image(MainApp.class.getResourceAsStream("/images/servant.png"));
                    setImage(depot, depotToQuantity.get(depot), image);
                }
                else if(depotToResource.get(depot).equals(ResourceType.STONE)){
                    Image image = new Image(MainApp.class.getResourceAsStream("/images/stone.png"));
                    setImage(depot, depotToQuantity.get(depot), image);
                }
                else if(depotToResource.get(depot).equals(ResourceType.COIN)) {
                    Image image = new Image(MainApp.class.getResourceAsStream("/images/coin.png"));
                    setImage(depot, depotToQuantity.get(depot), image);                }
            }
        }
    }
    private void setImage(int n, int quantity, Image img) {
        switch (n) {
            case 0:
                switch (quantity) {

                    case 3:
                        img_depot2_res3.setImage(img);
                    case 2:
                        img_depot1_res2.setImage(img);
                    case 1:
                        img_depot1_res1.setImage(img);
                        break;

                }
                break;
            case 1:
                switch (quantity) {
                    case 2:
                        img_depot2_res2.setImage(img);
                    case 1:
                        img_depot2_res1.setImage(img);
                        break;
                }
                break;
            case 2:
                img_depot3_res1.setImage(img);
                break;

        }
    }

    /**
     * Updates the position on the faith track.
     */
    private void updateFaithTrack(){
        img_space1.setImage(null);
        img_space2.setImage(null);
        img_space3.setImage(null);
        img_space4.setImage(null);
        img_space5.setImage(null);
        img_space6.setImage(null);
        img_space7.setImage(null);
        img_space8.setImage(null);
        img_space9.setImage(null);
        img_space10.setImage(null);
        img_space11.setImage(null);
        img_space12.setImage(null);
        img_space13.setImage(null);
        img_space14.setImage(null);
        img_space15.setImage(null);
        img_space16.setImage(null);
        img_space17.setImage(null);
        img_space18.setImage(null);
        img_space19.setImage(null);
        img_space20.setImage(null);
        img_space21.setImage(null);
        img_space22.setImage(null);
        img_space23.setImage(null);
        img_space24.setImage(null);

        Image image2 = new Image(MainApp.class.getResourceAsStream("/images/cross.png"));
        switch (faithSpace){
            case 1:
                img_space1.setImage(image2);
                img_space1.setVisible(true);
                break;
            case 2:
                img_space2.setImage(image2);
                img_space2.setVisible(true);
                break;
            case 3:
                img_space3.setImage(image2);
                img_space3.setVisible(true);
                break;
            case 4:
                img_space4.setImage(image2);
                img_space4.setVisible(true);
                break;
            case 5:
                img_space5.setImage(image2);
                img_space5.setVisible(true);
                break;
            case 6:
                img_space6.setImage(image2);
                img_space6.setVisible(true);
                break;
            case 7:
                img_space7.setImage(image2);
                img_space7.setVisible(true);
                break;
            case 8:
                img_space8.setImage(image2);
                img_space8.setVisible(true);
                break;
            case 9:
                img_space9.setImage(image2);
                img_space9.setVisible(true);
                break;
            case 10:
                img_space10.setImage(image2);
                img_space10.setVisible(true);
                break;
            case 11:
                img_space11.setImage(image2);
                img_space11.setVisible(true);
                break;

            case 12:
                img_space12.setImage(image2);
                img_space12.setVisible(true);
                break;

            case 13:
                img_space13.setImage(image2);
                img_space13.setVisible(true);
                break;

            case 14:
                img_space14.setImage(image2);
                img_space14.setVisible(true);
                break;

            case 15:
                img_space15.setImage(image2);
                img_space15.setVisible(true);
                break;

            case 16:
                img_space16.setImage(image2);
                img_space16.setVisible(true);
                break;

            case 17:
                img_space17.setImage(image2);
                img_space17.setVisible(true);
                break;

            case 18:
                img_space18.setImage(image2);
                img_space18.setVisible(true);
                break;

            case 19:
                img_space19.setImage(image2);
                img_space19.setVisible(true);
                break;

            case 20:
                img_space20.setImage(image2);
                img_space20.setVisible(true);
                break;

            case 21:
                img_space21.setImage(image2);
                img_space21.setVisible(true);
                break;

            case 22:
                img_space22.setImage(image2);
                img_space22.setVisible(true);
                break;

            case 23:
                img_space23.setImage(image2);
                img_space23.setVisible(true);
                break;

            case 24:
                img_space24.setImage(image2);
                img_space24.setVisible(true);
                break;
            default:
                break;
        }
    }

    /**
     * Updates the images of resources in strongbox.
     */
    private void updateStrongbox(){
        String resimg="";
        int n=0;
        for(ResourceType res: strongbox.keySet()){
            if(res.equals(ResourceType.SHIELD)){
                Image image = new Image(MainApp.class.getResourceAsStream("/images/shield.png"));
                if(n==0){
                    strong_res1.setImage(image);
                    strong_quantity1.setText(String.valueOf(strongbox.get(res)));
                }
                else if(n==1){
                    strong_res2.setImage(image);
                    strong_quantity2.setText(String.valueOf(strongbox.get(res)));
                }
                else if(n==2){
                    strong_res3.setImage(image);
                    strong_quantity3.setText(String.valueOf(strongbox.get(res)));
                }
                else{
                    strong_res4.setImage(image);
                    strong_quantity4.setText(String.valueOf(strongbox.get(res)));
                }
            }
            else if(res.equals(ResourceType.SERVANT)) {
                Image image = new Image(MainApp.class.getResourceAsStream("/images/servant.png"));
                if(n==0){
                    strong_res1.setImage(image);
                    strong_quantity1.setText(String.valueOf(strongbox.get(res)));
                }
                else if(n==1){
                    strong_res2.setImage(image);
                    strong_quantity2.setText(String.valueOf(strongbox.get(res)));
                }
                else if(n==2){
                    strong_res3.setImage(image);
                    strong_quantity3.setText(String.valueOf(strongbox.get(res)));
                }
                else{
                    strong_res4.setImage(image);
                    strong_quantity4.setText(String.valueOf(strongbox.get(res)));
                }
            }
            else if(res.equals(ResourceType.STONE)){
                Image image = new Image(MainApp.class.getResourceAsStream("/images/stone.png"));
                if(n==0){
                    strong_res1.setImage(image);
                    strong_quantity1.setText(String.valueOf(strongbox.get(res)));
                }
                else if(n==1){
                    strong_res2.setImage(image);
                    strong_quantity2.setText(String.valueOf(strongbox.get(res)));
                }
                else if(n==2){
                    strong_res3.setImage(image);
                    strong_quantity3.setText(String.valueOf(strongbox.get(res)));
                }
                else{
                    strong_res4.setImage(image);
                    strong_quantity4.setText(String.valueOf(strongbox.get(res)));
                }
            }
            else if(res.equals(ResourceType.COIN)) {
                Image image = new Image(MainApp.class.getResourceAsStream("/images/coin.png"));
                if(n==0){
                    strong_res1.setImage(image);
                    strong_quantity1.setText(String.valueOf(strongbox.get(res)));
                }
                else if(n==1){
                    strong_res2.setImage(image);
                    strong_quantity2.setText(String.valueOf(strongbox.get(res)));
                }
                else if(n==2){
                    strong_res3.setImage(image);
                    strong_quantity3.setText(String.valueOf(strongbox.get(res)));
                }
                else{
                    strong_res4.setImage(image);
                    strong_quantity4.setText(String.valueOf(strongbox.get(res)));
                }
            }
            n++;
        }
    }

    /**
     * Updates the images of cards in slots.
     */
    private void updateSlots(){
        for(int i=0; i<devCardSlot.getSlotDev().size(); i++){
            if(i==0){
                if(devCardSlot.getSlotDev().get(i).size() == 1){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot0_card0.setImage(image1);
                }
                else if(devCardSlot.getSlotDev().get(i).size() == 2){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot0_card0.setImage(image1);
                    Image image2= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(1).getId()-1) + ".png"));
                    img_slot0_card1.setImage(image2);
                }
                else if (devCardSlot.getSlotDev().get(i).size() == 3){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot0_card0.setImage(image1);
                    Image image2= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(1).getId()-1) + ".png"));
                    img_slot0_card1.setImage(image2);
                    Image image3= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(2).getId()-1) + ".png"));
                    img_slot0_card2.setImage(image3);
                }
            }

            else if(i==1){
                if(devCardSlot.getSlotDev().get(i).size() == 1){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot1_card0.setImage(image1);
                }
                else if(devCardSlot.getSlotDev().get(i).size() == 2){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot1_card0.setImage(image1);
                    Image image2= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(1).getId()-1) + ".png"));
                    img_slot1_card1.setImage(image2);
                }
                else if (devCardSlot.getSlotDev().get(i).size() == 3){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot1_card0.setImage(image1);
                    Image image2= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(1).getId()-1) + ".png"));
                    img_slot1_card1.setImage(image2);
                    Image image3= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(2).getId()-1) + ".png"));
                    img_slot1_card2.setImage(image3);
                }
            }
            else if(i==2){
                if(devCardSlot.getSlotDev().get(i).size() == 1){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot2_card0.setImage(image1);
                }
                else if(devCardSlot.getSlotDev().get(i).size() == 2){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot2_card0.setImage(image1);
                    Image image2= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(1).getId()-1) + ".png"));
                    img_slot2_card1.setImage(image2);
                }
                else if (devCardSlot.getSlotDev().get(i).size() == 3){
                    Image image1= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(0).getId()-1) + ".png"));
                    img_slot2_card0.setImage(image1);
                    Image image2= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(1).getId()-1) + ".png"));
                    img_slot2_card1.setImage(image2);
                    Image image3= new Image(MainApp.class.getResourceAsStream("/images/devCards/dev_Id" + (devCardSlot.getSlotDev().get(i).get(2).getId()-1) + ".png"));
                    img_slot2_card2.setImage(image3);
                }
            }
        }
    }

    public void setChosenPlayer(String chosenPlayer) {
        this.chosenPlayer = chosenPlayer;
    }

    public void setFaithSpace(int faithSpace){
        this.faithSpace = faithSpace;
    }

    public void setDepotToResource(HashMap<Integer, ResourceType> depotToResource){
        this.depotToResource = depotToResource;
    }
    public void setDepotToQuantity(HashMap<Integer, Integer> depotToQuantity){
        this.depotToQuantity = depotToQuantity;
    }

    public void setStrongbox(HashMap<ResourceType, Integer> strongbox){
        this.strongbox = strongbox;
    }

    public void setDevCardSlot(DevCardSlot devCardSlot){
        this.devCardSlot = devCardSlot;
    }

    public void setPlayedLeaderCards(ArrayList<LeaderCard> playedLeaderCards){
        this.playedLeaderCards = playedLeaderCards;
    }

    public void setSelf(String self){
        this.self=self;
    }
    public void setRemainingLeaderCards(int remainingLeaderCards){
        this.remainingLeaderCards = remainingLeaderCards;
     }
}


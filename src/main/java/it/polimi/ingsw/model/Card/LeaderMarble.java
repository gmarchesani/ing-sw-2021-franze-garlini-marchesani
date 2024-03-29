package it.polimi.ingsw.model.Card;

import it.polimi.ingsw.model.enumeration.Color;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enumeration.LeaderCost;
import it.polimi.ingsw.model.enumeration.ResourceType;

import java.util.HashMap;

/**
 * This class is used to represents the Leader Card which has the white marble ability.
 */
public class LeaderMarble extends LeaderCard{
    private HashMap<Color, Integer> cost;

    /**
     * Default constructor.
     * @param id id associated with the card.
     * @param victoryPoints victory points you can receive playing this card.
     * @param cost required resources or cards you must have to play this card.
     * @param resourceAbility ability related to the card.
     */
    public LeaderMarble(int id, int victoryPoints, HashMap<Color, Integer> cost, ResourceType resourceAbility) {
        super(id, victoryPoints, resourceAbility);
        this.cost=cost;
    }

    @Override
    public void activateAbility(Player player) {
        if (!(player.getMarbleConversion().contains(getResourceAbility()))) {
            player.getMarbleConversion().add(getResourceAbility());
        }
    }


    @Override
    public String toString(){
        String leaderSTR = "Leader Card Marble: \n" +
                "id: " + getId() + "\n" +
                "requirement: " + "\n" +
                "{";
        for(Color color: cost.keySet()) {
            leaderSTR = leaderSTR.concat("\n    " + color.toString() + ": " + cost.get(color));
        }
        leaderSTR = leaderSTR.concat("\n}\nresource ability: " + getResourceAbility().toString() + "\nvictory points: " + getVP() + "\n");
        return leaderSTR;
    }

    @Override
    public LeaderCost getCostType() {
        return LeaderCost.DEV_CARD_DOUBLE;
    }
    @Override
    public HashMap<Color, Integer> getCardCost() {
        return cost;
    }
}

package it.polimi.ingsw.model.Action;

import it.polimi.ingsw.model.Action.ActionToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class is the container of the ActionTokens.
 */
public class TokenBag {
    private ArrayList<ActionToken> availableTokens;
    private ArrayList<ActionToken> usedTokens = new ArrayList<>();

    /**
     * Default constructor.
     * @param availableTokens an ArrayList containing the ActionTokens generated by the game.
     */
    public TokenBag(ArrayList<ActionToken> availableTokens) {
        this.availableTokens = availableTokens;
        Collections.shuffle(availableTokens);
    }

    /**
     * This method picks a random ActionToken from the TokenBag.
     * @return the drawn ActionToken.
     */
    public ActionToken drawToken() {
        Random random = new Random();
        int tokenIndex = random.nextInt(availableTokens.size());
        ActionToken extractedToken = availableTokens.get(tokenIndex);
        usedTokens.add(extractedToken);
        availableTokens.remove(tokenIndex);
        return extractedToken;
    }

    /**
     * This method fills the availableTokens list with the element of the usedTokens list, setting the Token Bag to its original state.
     */
    public void shuffle() {
        availableTokens.addAll(usedTokens);
        usedTokens.clear();
        Collections.shuffle(availableTokens);
    }

    public ArrayList<ActionToken> getAvailableTokens() {
        return availableTokens;
    }

    public ArrayList<ActionToken> getUsedTokens() {
        return usedTokens;
    }

}

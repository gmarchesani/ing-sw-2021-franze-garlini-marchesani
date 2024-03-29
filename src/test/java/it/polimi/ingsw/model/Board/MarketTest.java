package it.polimi.ingsw.model.Board;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.enumeration.ResourceType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


import static org.junit.Assert.*;

public class MarketTest {
    private Market testMarket;

    @Before
    public void setUp() {

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


        testMarket = new Market(totalMarbles);




    }



    @Test
    public void testPickResources() {
        ResourceType[][] testMarketTray = testMarket.getMarketTray();
        HashMap<ResourceType, Integer> test = new HashMap<>();
        ResourceType testCorner = testMarket.getCornerMarble();

        ResourceType res20 = testMarketTray[2][0];
        ResourceType res21 = testMarketTray[2][1];
        ResourceType res22 = testMarketTray[2][2];
        test = testMarket.pickResources('c', 2, null);

        ArrayList<ResourceType> lKey = new ArrayList<>(test.keySet());

        if(res20!=ResourceType.EMPTY) assertTrue(lKey.contains(res20));
        if(res21!=ResourceType.EMPTY) assertTrue(lKey.contains(res21));
        if(res22!=ResourceType.EMPTY) assertTrue(lKey.contains(res22));

        ResourceType[][] testMarketTrayPost = testMarket.getMarketTray();
        ResourceType testCornerPost = testMarket.getCornerMarble();
        assertEquals(testMarketTray[2][1].getVal(), testMarketTrayPost[2][0].getVal());
        assertEquals(testCornerPost.getVal(), testMarketTray[2][0].getVal());
        assertEquals(testMarketTray[2][2].getVal(), testMarketTrayPost[2][1].getVal());
        assertEquals(testMarketTrayPost[2][2].getVal(), testCorner.getVal());

    }



}









package test;

import org.json.JSONArray;
import Pizza.*;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class RecetteTest {
    public static void testGetPreparationTime() {
        Recette recette = new Recette("Margherita", new JSONArray(), 30);
        assertEquals(30, recette.getPreparationTime());

    }
}
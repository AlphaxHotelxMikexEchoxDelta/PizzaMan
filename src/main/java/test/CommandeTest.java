package test;

import org.json.JSONArray;
import Pizza.*;

import static org.junit.Assert.assertEquals;


public class CommandeTest {

    void testGetPrice() {
        Recette recette = new Recette("Margherita", new JSONArray(), 30);
        Commande commande = new Commande("1", recette, 20, "2022-12-31");
        System.out.println("Le prix de la commande est : " + commande.getPrice());
        assertEquals(20, commande.getPrice());
    }

    public static void main(String[] args) {
        CommandeTest commandeTest = new CommandeTest();
        commandeTest.testGetPrice();
    }
}
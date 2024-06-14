package Pizza;

import java.util.ArrayList;

public class Commande {

    private String id ;
    private Recette recettes ;
    private int price ;
    private String expiration ;

    public Commande(String id, Recette recettes, int price, String expiration) {
        this.id = id;
        this.recettes = recettes;
        this.price = price;
        this.expiration = expiration;
    }

    public String getId() {
        return id;
    }

    public Recette getRecettes() {
        return recettes;
    }

    public int getPrice() {
        return price;
    }

    public String getExpiration() {
        return expiration;
    }



}

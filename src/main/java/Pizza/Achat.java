package Pizza;

import org.json.JSONArray;

import java.util.List;

public class Achat {

    public String idPizza ;
    public String idCommande ;
    public List<String> idIngrediens ;

    public Achat(String idPizza, String idCommande, List<String> idIngrediens) {
        this.idPizza = idPizza;
        this.idCommande = idCommande;
        this.idIngrediens = idIngrediens;
    }

    @Override
    public String toString() {
        return "Achat{ C:"+idCommande+" P:"+idPizza+" I:"+idIngrediens.toString();
    }
}

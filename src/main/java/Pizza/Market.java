package Pizza;

import java.util.HashMap;

public class Market {

    private HashMap<String, Integer> ingredients ;

    public Market(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

}

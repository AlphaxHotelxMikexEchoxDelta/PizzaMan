package Pizza;

import java.util.HashMap;
import java.util.List;

public class Stock {

    private List<Ingredient> ingredients ;
    private int quantie = 20;

    public Stock(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}

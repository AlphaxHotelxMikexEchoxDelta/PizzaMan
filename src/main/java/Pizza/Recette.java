package Pizza;

import org.json.JSONArray;

import java.util.List;

public class Recette {

    private String name ;
    private JSONArray ingredients ;
    private int preparationTime ;

    public Recette(String name, JSONArray ingredients, int preparationTime) {
        this.name = name;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getIngredients() {
        return ingredients;
    }

    public void setIngredients(JSONArray ingredients) {
        this.ingredients = ingredients;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

}

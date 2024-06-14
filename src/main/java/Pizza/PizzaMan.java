package Pizza;

import java.util.List;

public class PizzaMan extends PizzaStore {
    public PizzaMan(Stats stats, List<Commande> commandes, List<Pizza> pizzas, List<Recette> recettes) {
        super(stats, commandes, pizzas, recettes);
    }
}

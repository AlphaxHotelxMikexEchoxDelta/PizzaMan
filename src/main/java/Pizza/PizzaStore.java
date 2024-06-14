package Pizza;

import java.util.List;

public abstract class PizzaStore {

    private Stats stats ;
    private List<Commande> commandes ;
    private List<Pizza> pizzas ;
    private List<Recette> recettes ;

    public PizzaStore(Stats stats, List<Commande> commandes, List<Pizza> pizzas, List<Recette> recettes) {
        this.stats = stats;
        this.commandes = commandes;
        this.pizzas = pizzas;
        this.recettes = recettes;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }

}

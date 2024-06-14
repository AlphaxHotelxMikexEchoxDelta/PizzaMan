import MD.Model;
import Pizza.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {

    private static PizzaMan pizzaMan;
    private static Market market;
    private static Stock stock;
    private static List<Achat> achat = new ArrayList<>();
    private static Model model ;

    public static void init() {
        model = Model.getInstance();
        stock = new Stock(model.getStock());
        market = new Market(model.getMarket());
        pizzaMan = new PizzaMan(
                new Stats(0, 0, 0),
                model.getOrder(),
                null,
                model.getRecette()
        );
    }

    public static void getRentable(Commande commande) {
        int cc = 0;

        for (String m : market.getIngredients().keySet()) {
            for (int y = 0; y < commande.getRecettes().getIngredients().length(); y++) {
                if (commande.getRecettes().getIngredients().get(y).equals(m)) {
                    cc += market.getIngredients().get(m);
                }
            }
        }

        if (commande.getPrice() <= cc) {
            pizzaMan.getCommandes().remove(commande);
        }

        Collections.sort(pizzaMan.getCommandes(), new Comparator<Commande>() {
            @Override
            public int compare(Commande c1, Commande c2) {
                return Double.compare(c2.getPrice(), c1.getPrice()); //calcule du prix décroissant pour savoir quelle pizza va rapporter le plus
            }
        });
    }

    public static boolean peutEtreDansStock(Commande commande) {
        System.out.println("c:" + commande.getRecettes().getIngredients().length());
        System.out.println("r:" + (20 - stock.getIngredients().size()));
        System.out.println("s:" + stock.getIngredients().size());
        return commande.getRecettes().getIngredients().length() < (20 - stock.getIngredients().size()); // calculer la taille du stock pour savoir si on peux faire la prochaine pizza ou non
    }

    public static void achaatGringredeint(Commande commande) {
        for (int i = 0; i < commande.getRecettes().getIngredients().length(); i++) {
            model.achatMarket(commande.getRecettes().getIngredients().get(i).toString());
            stock.getIngredients().add(new Ingredient(null, null, null, null, null));
        }
    }

    public static void whatTheFuckMannnn() throws IOException, InterruptedException {
        for (int y = 0; y < pizzaMan.getCommandes().size(); y++) {
            if (peutEtreDansStock(pizzaMan.getCommandes().get(y))) {
                System.out.println("Processing Commande: " + pizzaMan.getCommandes().get(y).getId());

                achaatGringredeint(pizzaMan.getCommandes().get(y));
                Thread.sleep(30000);
                String pizza = model.fabriquerPizza(pizzaMan.getCommandes().get(y));

                if (pizza == null) {
                    System.out.println("Failed to fabricate pizza for Commande: " + pizzaMan.getCommandes().get(y).getId());
                    continue; // Skip this order if pizza fabrication failed
                }

                achat.add(new Achat(
                        pizza,
                        pizzaMan.getCommandes().get(y).getId(),
                        null
                ));

                System.out.println("Achat created: " + achat.get(achat.size() - 1).toString());

            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        init();

        for(Ingredient ingredient : stock.getIngredients()) {
            model.deleteROOT(ingredient.getId());
        }

        for(int y=0; y<pizzaMan.getCommandes().size(); y++) {
            getRentable(pizzaMan.getCommandes().get(y));
        }

        whatTheFuckMannnn();

        System.out.println("Final Achat List: " + achat.toString());
    }
}
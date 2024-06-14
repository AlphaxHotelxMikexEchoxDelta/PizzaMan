package Pizza;

public class Stats {

    private int ingredientLosses ;
    private int expenses ;
    private int revenue ;

    public Stats(int ingredientLosses, int expenses, int revenue) {
        this.ingredientLosses = ingredientLosses;
        this.expenses = expenses;
        this.revenue = revenue;
    }

    public int getIngredientLosses() {
        return ingredientLosses;
    }

    public void setIngredientLosses(int ingredientLosses) {
        this.ingredientLosses = ingredientLosses;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}

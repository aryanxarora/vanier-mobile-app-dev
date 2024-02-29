package ca.vanier.reciperack;

public class Recipe {
    private String name;
    private String category;
    private String[] ingredients;
    private String directions;

    public Recipe(String name, String category, String[] ingredients, String directions) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}

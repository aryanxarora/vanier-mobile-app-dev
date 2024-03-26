package ca.vanier.reciperack;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String uid;
    private List<Recipe> recipes;

    public User(String uid, List<Recipe> recipes) {
        this.uid = uid;
        this.recipes = recipes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}

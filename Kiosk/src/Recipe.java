import Component.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Recipe {
    private View view;
    private ArrayList<Component> components;
    private Order order;

    public void addRecipes(){
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));;
        StringTokenizer st;
        view.viewComponentsPage("size");

    }
    public void saveRecipe(String recipe){

    }
    public String loadRecipe(String phoneNo){
        return "result";
    }
    public void modifyRecipes(){

    }
    public void deleteRecipes(){

    }
}

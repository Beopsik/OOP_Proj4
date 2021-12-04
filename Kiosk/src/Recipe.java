import Component.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Component.*;

public class Recipe {
    private View view;
    private Component component;
    public Recipe(){
        this.view=new View();
    }

    public String addRecipes(String phoneNo){
        ArrayList<Component> size=new ArrayList<Component>();
        ArrayList<Component> bread=new ArrayList<Component>();
        ArrayList<Component> ingredient=new ArrayList<Component>();
        ArrayList<Component> sauce=new ArrayList<Component>();
        ArrayList<Component> beverage=new ArrayList<Component>();
        ArrayList<Component> side=new ArrayList<Component>();
        String recipe="";
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st=new StringTokenizer(br.readLine());

            int select;
            String name;
            String price;
            Component forAdd;


            /*
            * 사이즈 선택 및 선택 저장
            * */
            component=new Size();
            String sizeAll=component.loadComponents();
            String[] sizeList=sizeAll.split("\n");

            view.viewComponentsPage(sizeAll);
            select=Integer.parseInt(st.nextToken());

            String[] sizeInfo=sizeList[select].split(",");
            name=sizeInfo[0];
            price=sizeInfo[1];

            forAdd=new Size();
            forAdd.setName(name);
            forAdd.setPrice(Integer.parseInt(price));

            size.add(forAdd);


            /*
             * 빵 선택 및 선택 저장
             * */
            component=new Bread();
            String breadAll=component.loadComponents();
            String[] breadList=breadAll.split("\n");

            view.viewComponentsPage(breadAll);
            select=Integer.parseInt(st.nextToken());

            String[] breadInfo=breadList[select].split(",");
            name=breadInfo[0];
            price=breadInfo[1];

            forAdd=new Bread();
            forAdd.setName(name);
            forAdd.setPrice(Integer.parseInt(price));

            bread.add(forAdd);

            /*
             * 속재료 선택 및 선택 저장
             * */
            component=new Ingredient();
            String ingredientAll=component.loadComponents();
            String[] ingredientList=ingredientAll.split("\n");

            view.viewComponentsPage(ingredientAll);

            while(st.hasMoreTokens()) {
                select = Integer.parseInt(st.nextToken());
                String[] ingredientInfo=ingredientList[select].split(",");
                name=ingredientInfo[0];
                price=ingredientInfo[1];

                forAdd=new Ingredient();
                forAdd.setName(name);
                forAdd.setPrice(Integer.parseInt(price));

                ingredient.add(forAdd);
            }

            /*
             * 소스 선택 및 선택 저장
             * */
            component=new Sauce();
            String sauceAll=component.loadComponents();
            String[] sauceList=sauceAll.split("\n");

            view.viewComponentsPage(sauceAll);

            while(st.hasMoreTokens()) {
                select = Integer.parseInt(st.nextToken());
                String[] sauceInfo=sauceList[select].split(",");
                name=sauceInfo[0];
                price=sauceInfo[1];

                forAdd=new Sauce();
                forAdd.setName(name);
                forAdd.setPrice(Integer.parseInt(price));

                sauce.add(forAdd);
            }

            /*
             * 음료수 선택 및 선택 저장
             * */
            component=new Beverage();
            String beverageAll=component.loadComponents();
            String[] beverageList=beverageAll.split("\n");

            view.viewComponentsPage(beverageAll);

            while(st.hasMoreTokens()) {
                select = Integer.parseInt(st.nextToken());
                String[] beverageInfo=beverageList[select].split(",");
                name=beverageInfo[0];
                price=beverageInfo[1];

                forAdd=new Beverage();
                forAdd.setName(name);
                forAdd.setPrice(Integer.parseInt(price));

                beverage.add(forAdd);
            }

            /*
             * 사이드 선택 및 선택 저장
             * */
            component=new Side();
            String sideAll=component.loadComponents();
            String[] sideList=sideAll.split("\n");

            view.viewComponentsPage(sideAll);

            while(st.hasMoreTokens()) {
                select = Integer.parseInt(st.nextToken());
                String[] sideInfo=sideList[select].split(",");
                name=sideInfo[0];
                price=sideInfo[1];

                forAdd=new Side();
                forAdd.setName(name);
                forAdd.setPrice(Integer.parseInt(price));

                side.add(forAdd);
            }


            System.out.println("Do you want to save?");
            System.out.println("1. yes  2. no");
            select=Integer.parseInt(st.nextToken());

            String recipeName;
            if(select==1){
                System.out.println("Enter recipe name:");
                recipeName=st.nextToken();
            }else{
                recipeName="-";
            }

            recipe=recipeName+"/"+"size:"+size.get(0).getName()+"_"+size.get(0).getPrice()+",/"+"bread:"+bread.get(0).getName()+"_"+bread.get(0).getPrice()+",/";
            recipe+="ingredient:";
            for(int i=0; i<ingredient.size(); i++){
                recipe+=ingredient.get(i).getName()+"_";
                recipe+=ingredient.get(i).getPrice()+",";
            }
            recipe+="/";

            recipe+="sauce:";
            for(int i=0; i<sauce.size(); i++){
                recipe+=sauce.get(i).getName()+"_";
                recipe+=sauce.get(i).getPrice()+",";
            }
            recipe+="/";

            recipe+="beverage:";
            for(int i=0; i<beverage.size(); i++){
                recipe+=beverage.get(i).getName()+"_";
                recipe+=beverage.get(i).getPrice()+",";
            }
            recipe+="/";

            recipe+="side:";
            for(int i=0; i<side.size(); i++){
                recipe+=side.get(i).getName()+"_";
                recipe+=side.get(i).getPrice()+",";
            }
            recipe+="\n";

            if(select==1){
                saveRecipe(phoneNo+"/"+recipe);
            }
        }catch (IOException e){
            System.out.println(e);
        }

        return recipe;
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

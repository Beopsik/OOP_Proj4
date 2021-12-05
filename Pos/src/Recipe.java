import Component.Component;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Component.*;

public class Recipe {
    private View view;
    private Component component;
    public Recipe(){
        this.view=new View();
    }

    public String addRecipes(String phoneNo) throws Exception{
        ArrayList<Component> size=new ArrayList<Component>();
        ArrayList<Component> bread=new ArrayList<Component>();
        ArrayList<Component> ingredient=new ArrayList<Component>();
        ArrayList<Component> sauce=new ArrayList<Component>();
        ArrayList<Component> beverage=new ArrayList<Component>();
        ArrayList<Component> side=new ArrayList<Component>();
        String recipe="";
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

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
            System.out.print("Enter number: ");
            st=new StringTokenizer(br.readLine());
            select=Integer.parseInt(st.nextToken());

            if(select>=1&&select<=sizeList.length) {
                String[] sizeInfo = sizeList[select - 1].split(",");
                name = sizeInfo[0];
                price = sizeInfo[1];

                forAdd = new Size();
                forAdd.setName(name);
                forAdd.setPrice(Integer.parseInt(price));

                size.add(forAdd);
            }else{
                throw new NoSuchElementException("Wrong Input! [size]");
            }
            System.out.println();

            /*
             * 빵 선택 및 선택 저장
             * */
            component=new Bread();
            String breadAll=component.loadComponents();
            String[] breadList=breadAll.split("\n");

            view.viewComponentsPage(breadAll);
            System.out.print("Enter number: ");
            st=new StringTokenizer(br.readLine());
            select=Integer.parseInt(st.nextToken());

            if(select>=1&&select<= breadList.length) {
                String[] breadInfo = breadList[select - 1].split(",");
                name = breadInfo[0];
                price = breadInfo[1];

                forAdd = new Bread();
                forAdd.setName(name);
                forAdd.setPrice(Integer.parseInt(price));

                bread.add(forAdd);
            }else{
                throw new NoSuchElementException("Wrong Input! [bread]");
            }
            System.out.println();

            /*
             * 속재료 선택 및 선택 저장
             * */
            component=new Ingredient();
            String ingredientAll=component.loadComponents();
            String[] ingredientList=ingredientAll.split("\n");

            view.viewComponentsPage(ingredientAll);

            System.out.print("Enter all number (0 is skip): ");
            st=new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                select = Integer.parseInt(st.nextToken());
                if(select==0&&ingredient.size()==0) {
                    break;
                }else if(select==0&&ingredient.size()!=0){
                    throw new NoSuchElementException("Wrong Input! [ingredient]");
                }

                if(select>=1&&select<= ingredientList.length) {
                    String[] ingredientInfo = ingredientList[select - 1].split(",");
                    name = ingredientInfo[0];
                    price = ingredientInfo[1];

                    forAdd = new Ingredient();
                    forAdd.setName(name);
                    forAdd.setPrice(Integer.parseInt(price));

                    ingredient.add(forAdd);
                }else{
                    throw new NoSuchElementException("Wrong Input! [ingredient]");
                }
            }
            System.out.println();

            /*
             * 소스 선택 및 선택 저장
             * */
            component=new Sauce();
            String sauceAll=component.loadComponents();
            String[] sauceList=sauceAll.split("\n");

            view.viewComponentsPage(sauceAll);

            System.out.print("Enter all number (0 is skip): ");
            st=new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                select = Integer.parseInt(st.nextToken());
                if(select==0&&sauce.size()==0) {
                    break;
                }else if(select==0&&sauce.size()!=0){
                    throw new NoSuchElementException("Wrong Input! [sauce]");
                }

                if(select>=1&&select<= sauceList.length) {
                    String[] sauceInfo = sauceList[select - 1].split(",");
                    name = sauceInfo[0];
                    price = sauceInfo[1];

                    forAdd = new Sauce();
                    forAdd.setName(name);
                    forAdd.setPrice(Integer.parseInt(price));

                    sauce.add(forAdd);
                }else{
                    throw new NoSuchElementException("Wrong Input! [sauce]");
                }
            }
            System.out.println();

            /*
             * 음료수 선택 및 선택 저장
             * */
            component=new Beverage();
            String beverageAll=component.loadComponents();
            String[] beverageList=beverageAll.split("\n");

            view.viewComponentsPage(beverageAll);

            System.out.print("Enter all number (0 is skip): ");
            st=new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                select = Integer.parseInt(st.nextToken());
                if(select==0&&beverage.size()==0) {
                    break;
                }else if(select==0&&beverage.size()!=0){
                    throw new NoSuchElementException("Wrong Input! [beverage]");
                }

                if(select>=1&&select<= beverageList.length) {
                    String[] beverageInfo = beverageList[select - 1].split(",");
                    name = beverageInfo[0];
                    price = beverageInfo[1];

                    forAdd = new Beverage();
                    forAdd.setName(name);
                    forAdd.setPrice(Integer.parseInt(price));

                    beverage.add(forAdd);
                }else{
                    throw new NoSuchElementException("Wrong Input! [beverage]");
                }
            }
            System.out.println();

            /*
             * 사이드 선택 및 선택 저장
             * */
            component=new Side();
            String sideAll=component.loadComponents();
            String[] sideList=sideAll.split("\n");

            view.viewComponentsPage(sideAll);

            System.out.print("Enter all number (0 is skip): ");
            st=new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                select = Integer.parseInt(st.nextToken());
                if(select==0&&side.size()==0) {
                    break;
                }else if(select==0&&side.size()!=0){
                    throw new NoSuchElementException("Wrong Input! [side]");
                }

                if(select>=1&&select<= sizeList.length) {
                    String[] sideInfo = sideList[select - 1].split(",");
                    name = sideInfo[0];
                    price = sideInfo[1];

                    forAdd = new Side();
                    forAdd.setName(name);
                    forAdd.setPrice(Integer.parseInt(price));

                    side.add(forAdd);
                }else{
                    throw new NoSuchElementException("Wrong Input! [side]");
                }
            }
            System.out.println();


            while(true) {
                System.out.println("Do you want to save?");
                System.out.println("1. yes  2. no");
                st=new StringTokenizer(br.readLine());
                select = Integer.parseInt(st.nextToken());
                if(select==1||select==2){
                    break;
                }else {
                    System.out.println("Wrong input. Enter the number.");
                }
            }

            String recipeName;
            if(select==1){
                System.out.print("Enter recipe name:");
                st=new StringTokenizer(br.readLine());
                recipeName=st.nextToken();

                System.out.println();
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

            if(select==1){
                saveRecipe(phoneNo+"/"+recipe);
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return recipe;
    }
    public void saveRecipe(String recipe){
        try {
            FileWriter fw = new FileWriter("../DB/recipe.txt", true);
            fw.write(recipe + "\r\n");
            fw.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
    public String loadRecipe(String phoneNo){
        try {
            BufferedReader br = new BufferedReader(new FileReader("../DB/recipe.txt"));
            String recipe = "";
            while(true) {
                String recipeLine = br.readLine();
                if(recipeLine == null)
                    break;
                String[] recipeInfo = recipeLine.split("/");
                if(!recipeInfo[0].equals(phoneNo))
                    continue;
                else {
                    recipe += recipeLine.substring(recipeInfo[0].length() + 1)+ "\r\n";
                }
            }
            return recipe;
        }
        catch(IOException e) {
            System.out.println(e);
            return e.toString();
        }
    }
    public void modifyRecipes(String phoneNo){
        Scanner sc = new Scanner(System.in);

        boolean doModify=false;
        String recipeName;
        System.out.print("Select recipe for modifying(Input recipe name) : ");
        recipeName = sc.nextLine();

        System.out.println("Which component do you want to modify? ");
        int componentNum = view.selectComponentPage();

        int selectOperation;
        while (true) {
            System.out.println("1. Add  2. Delete");
            selectOperation=sc.nextInt();
            sc.nextLine();
            if(selectOperation==1||selectOperation==2) {
                break;
            }else{
                System.out.println("Wrong input. Enter the number.");
            }
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("../DB/recipe.txt"));
            String recipe = "";
            while(true) {
                String recipeLine = br.readLine();
                if(recipeLine == null)
                    break;
                String[] recipeInfo = recipeLine.split("/");
                if(!recipeInfo[0].equals(phoneNo)) {
                    recipe += recipeLine + "\r\n";
                }
                else {
                    if(!recipeInfo[1].equals(recipeName))
                        recipe += recipeLine + "\r\n";
                    else {
                        doModify=true;
                        String beforeTargetComponent="";
                        String targetComponent="";
                        String afterTargetComponent="";

                        for(int i=1; i<componentNum+1; i++) {
                            beforeTargetComponent += recipeInfo[i]+"/";
                        }
                        targetComponent=recipeInfo[componentNum+1];
                        for(int i=componentNum+2; i<recipeInfo.length-1; i++){
                            afterTargetComponent += recipeInfo[i]+"/";
                        }
                        afterTargetComponent += recipeInfo[recipeInfo.length-1];

                        System.out.println(beforeTargetComponent+targetComponent+"/"+afterTargetComponent);
                        if(selectOperation==1) {
                            if(componentNum==1){
                                component=new Size();
                            }else if(componentNum==2){
                                component=new Bread();
                            }else if(componentNum==3){
                                component=new Ingredient();
                            }else if(componentNum==4){
                                component=new Sauce();
                            }else if(componentNum==5){
                                component=new Beverage();
                            }else if(componentNum==6){
                                component=new Side();
                            }

                            String componentAll=component.loadComponents();
                            String[] componentList=componentAll.split("\n");

                            view.viewComponentsPage(componentAll);
                            int select=sc.nextInt();
                            sc.nextLine();

                            if(select>=1&&select<=componentList.length) {
                                String[] componentInfo = componentList[select - 1].split(",");
                                String name = componentInfo[0];
                                String price = componentInfo[1];

                                targetComponent += name + "_" + price + ",/";

                                recipeLine = phoneNo + "/" + beforeTargetComponent + targetComponent + afterTargetComponent;
                                recipe += recipeLine + "\r\n";
                            }else{
                                throw new NoSuchElementException("Wrong Input! [modify]");
                            }
                        }else if(selectOperation==2){
                            System.out.println("Input the name of what you want to delete.");
                            String name=sc.nextLine();
                            Pattern pattern=Pattern.compile(name+"_(([0-9])\\w+),");
                            Matcher matcher=pattern.matcher(targetComponent);

                            String willDelete="";
                            if(matcher.find()){
                                willDelete=matcher.group();
                            }else{
                                throw new NoSuchElementException("Modify recipe error [delete][nothing matches the name]");
                            }
                            targetComponent=targetComponent.replaceAll(willDelete, "");
                            targetComponent+="/";

                            recipeLine=phoneNo+"/"+beforeTargetComponent+targetComponent+afterTargetComponent;
                            recipe += recipeLine + "\r\n";
                        }else {
                            System.out.println("Modify recipe error [Wrong Input]");
                            return;
                        }
                    }
                }
            }
            if(!doModify){
                throw new NoSuchElementException("Modify recipe error [nothing matches recipes name]");
            }
            FileWriter fw = new FileWriter("../DB/recipe.txt");
            fw.write(recipe);
            fw.close();
            br.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
    public void deleteRecipes(String phoneNo){
        Scanner sc = new Scanner(System.in);
        String deleteRecipes;

        System.out.print("Select recipes for deleting(Input recipes' name) : ");
        deleteRecipes = sc.nextLine();
        String[] recipeList = deleteRecipes.split(" ");
        ArrayList<String> deleteList = new ArrayList<String>(Arrays.asList(recipeList));
        int before = 0, after = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("../DB/recipe.txt"));
            String recipe = "";
            while(true) {
                String recipeLine = br.readLine();
                before++;
                if(recipeLine == null)
                    break;
                String[] recipeInfo = recipeLine.split("/");
                if(!phoneNo.equals(recipeInfo[0])) {
                    recipe += recipeLine + "\r\n";
                    after++;
                }
                else {
                    if(!deleteList.contains(recipeInfo[1])) {
                        recipe += recipeLine + "\r\n";
                        after++;
                    }
                }
            }

            if((before - 1 - recipeList.length) != after) {
                System.out.println("Wrong Input!!!");
                return;
            }

            FileWriter fw = new FileWriter("../DB/recipe.txt");
            fw.write(recipe);
            fw.close();
            br.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}

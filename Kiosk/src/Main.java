import Component.*;

import java.io.*;
import java.nio.Buffer;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static String phoneNo;
    private static String userType;
    private static View view;
    private static Component component;
    private static Recipe recipe;
    private static Order order;

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));;
        StringTokenizer st;
        try {
            view=new View();
            recipe=new Recipe();
            order=new Order();

            view.viewLoginPage();
            st=new StringTokenizer(br.readLine());
            phoneNo=st.nextToken();
            userType=login(phoneNo);

            if(userType.equals("admin")) {
                while(true) {
                    int menuNum = view.viewMenuPage(userType);

                    if(menuNum == 1) {
                        System.out.println("Which component do you want to add ?");
                        int componentNum = view.selectComponentPage();

                        switch(componentNum) {
                            case 1:
                                component = new Size();
                                component.addComponents();
                                break;
                            case 2:
                                component = new Bread();
                                component.addComponents();
                                break;
                            case 3:
                                component = new Ingredient();
                                component.addComponents();
                                break;
                            case 4:
                                component = new Sauce();
                                component.addComponents();
                                break;
                            case 5:
                                component = new Beverage();
                                component.addComponents();
                                break;
                            case 6:
                                component = new Side();
                                component.addComponents();
                                break;
                        }
                    }
                    else if(menuNum == 2) {
                        System.out.println("Which component do you want to modify ?");
                        int componentNum = view.selectComponentPage();

                        switch(componentNum) {
                            case 1:
                                component = new Size();
                                view.viewComponentsPage(component.loadComponents());
                                component.modifyComponents();
                                break;
                            case 2:
                                component = new Bread();
                                view.viewComponentsPage(component.loadComponents());
                                component.modifyComponents();
                                break;
                            case 3:
                                component = new Ingredient();
                                view.viewComponentsPage(component.loadComponents());
                                component.modifyComponents();
                                break;
                            case 4:
                                component = new Sauce();
                                view.viewComponentsPage(component.loadComponents());
                                component.modifyComponents();
                                break;
                            case 5:
                                component = new Beverage();
                                view.viewComponentsPage(component.loadComponents());
                                component.modifyComponents();
                                break;
                            case 6:
                                component = new Side();
                                view.viewComponentsPage(component.loadComponents());
                                component.modifyComponents();
                                break;
                        }
                    }
                    else if(menuNum == 3) {
                        System.out.println("Which component do you want to delete ?");
                        int componentNum = view.selectComponentPage();

                        switch(componentNum) {
                            case 1:
                                component = new Size();
                                view.viewComponentsPage(component.loadComponents());
                                component.deleteComponents();
                                break;
                            case 2:
                                component = new Bread();
                                view.viewComponentsPage(component.loadComponents());
                                component.deleteComponents();
                                break;
                            case 3:
                                component = new Ingredient();
                                view.viewComponentsPage(component.loadComponents());
                                component.deleteComponents();
                                break;
                            case 4:
                                component = new Sauce();
                                view.viewComponentsPage(component.loadComponents());
                                component.deleteComponents();
                                break;
                            case 5:
                                component = new Beverage();
                                view.viewComponentsPage(component.loadComponents());
                                component.deleteComponents();
                                break;
                            case 6:
                                component = new Side();
                                view.viewComponentsPage(component.loadComponents());
                                component.deleteComponents();
                                break;
                        }
                    }
                    else if(menuNum == 4) {
                        BufferedReader br1 = new BufferedReader(new FileReader("../DB/customer.txt"));
                        Scanner sc = new Scanner(System.in);
                        String customerPhoneNo;
                        while(true) {
                            String line = br1.readLine();
                            if(line == null)
                                break;
                            System.out.println(line);
                        }
                        br1.close();

                        System.out.print("Which customer do you want to delete? : ");
                        customerPhoneNo = sc.next();
                        deleteCustomerInfo(customerPhoneNo);
                    }
                    else if(menuNum == 5) {
                        logout();
                        break;
                    }
                }
            }
            else {
                while(true) {
                    try {
                        int menuNum = view.viewMenuPage(userType);

                        if(menuNum == 1) {
                            order.order(recipe.addRecipes(phoneNo));
                            return;
                        }
                        else if(menuNum == 2) {
                            Scanner sc = new Scanner(System.in);
                            int selectMenu;
                            view.viewRecipesPage(recipe.loadRecipe(phoneNo));

                            System.out.println("--------Select Menu--------");
                            System.out.println("1. Order");
                            System.out.println("2. Modify recipes");
                            System.out.println("3. Delete recipes");
                            selectMenu = sc.nextInt();
                            sc.nextLine();

                            switch(selectMenu) {
                                case 1:
                                    String orders;
                                    String recipes = "";
                                    BufferedReader br2 = new BufferedReader(new FileReader("../DB/recipe.txt"));
                                    System.out.print("Enter recipes' name you want to order : ");
                                    orders = sc.nextLine();
                                    String[] orderList = orders.split(" ");
                                    while(true) {
                                        String recipeLine = br2.readLine();
                                        if(recipeLine == null)
                                            break;
                                        String[] recipeInfo = recipeLine.split("/");
                                        if(!recipeInfo[0].equals(phoneNo))
                                            continue;
                                        else {
                                            for(int i = 0;i < orderList.length;i++) {
                                                if(orderList[i].equals(recipeInfo[1]))
                                                    recipes += recipeLine.substring(recipeInfo[0].length() + 1) + "\r\n";
                                            }
                                        }
                                    }
                                    br2.close();
                                    order.order(recipes);
                                    return;
                                case 2:
                                    view.viewRecipesPage(recipe.loadRecipe(phoneNo));
                                    recipe.modifyRecipes(phoneNo);
                                    break;
                                case 3:
                                    view.viewRecipesPage(recipe.loadRecipe(phoneNo));
                                    recipe.deleteRecipes(phoneNo);
                                    break;
                                default:
                                    System.out.println("Wrong Input!!");
                                    break;
                            }
                        }
                    }catch (NoSuchElementException e){
                        System.out.println(e);
                    }catch (InvalidObjectException e){
                        System.out.println(e);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static String login(String phoneNo){
        if(phoneNo.equals("admin")){
            return "admin";
        }
        else {
            try {
                boolean flag = false;
                BufferedReader br = new BufferedReader(new FileReader("../DB/customer.txt"));
                while(true) {
                    String customerLine = br.readLine();
                    if(customerLine == null)
                        break;
                    if(customerLine.equals(phoneNo))
                        flag = true;
                }
                if(!flag) {
                    FileWriter fw = new FileWriter("../DB/customer.txt", true);
                    fw.write(phoneNo+"\r\n");
                    fw.close();
                }
                br.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }

            return "customer";
        }
    }

    private static void deleteCustomerInfo(String phoneNo){
        try {
            BufferedReader br = new BufferedReader(new FileReader("../DB/customer.txt"));
            String customer = "";
            while(true) {
                String customerLine = br.readLine();
                if(customerLine == null)
                    break;
                if(!customerLine.equals(phoneNo))
                    customer += customerLine + "\r\n";
            }
            br.close();

            FileWriter fw = new FileWriter("../DB/customer.txt");
            fw.write(customer);
            fw.close();

            BufferedReader br1 = new BufferedReader(new FileReader("../DB/recipe.txt"));
            String recipe = "";
            while(true) {
                String recipeLine = br1.readLine();
                if(recipeLine == null)
                    break;
                String[] recipeInfo = recipeLine.split("/");
                if(recipeInfo[0] != phoneNo)
                    recipe += recipeLine + "\r\n";
            }
            br1.close();

            FileWriter fw1 = new FileWriter("../DB/recipe.txt");
            fw1.write(recipe);
            fw1.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    private static void logout(){
        return;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {

    public void viewLoginPage() {
        System.out.println("--------Login Page--------");
        System.out.println("Enter your phone number");
    }

    int viewMenuPage(String userType) {

        Scanner sc = new Scanner(System.in);
        int selectMenu = 0;

        switch(userType) {
            case "customer":
                while(true) {
                    System.out.println("--------Select Menu--------");
                    System.out.println("1. Add Recipe");
                    System.out.println("2. Load Recipe");
                    System.out.println("3. Modify Recipe");
                    System.out.println("4. Delete Recipe");
                    System.out.print("Select Menu : ");
                    selectMenu = sc.nextInt();

                    if(selectMenu == 1 || selectMenu == 2 || selectMenu == 3 || selectMenu == 4) {
                        break;
                    }
                    else {
                        System.out.println("Wrong Input!!!\n");
                    }
                }
                break;
            case "admin":
                while(true) {
                    System.out.println("--------Select Menu--------");
                    System.out.println("1. Add Ingredient");
                    System.out.println("2. Modify Ingredient");
                    System.out.println("3. Delete Ingredient");
                    System.out.println("4. Delete Customer Info");
                    System.out.print("Select Menu : ");
                    selectMenu = sc.nextInt();

                    if(selectMenu == 1 || selectMenu == 2 || selectMenu == 3 || selectMenu == 4) {
                        break;
                    }
                    else {
                        System.out.println("Wrong Input!!!\n");
                    }
                }
                break;
        }

        return selectMenu;
    }

    int selectComponentPage() {
        Scanner sc = new Scanner(System.in);
        int selectComponent;

        while(true) {
            System.out.println("--------Select Component--------");
            System.out.println("1. Size");
            System.out.println("2. Bread");
            System.out.println("3. Ingredient");
            System.out.println("4. Sauce");
            System.out.println("5. Beverage");
            System.out.println("6. Side Menu");
            System.out.print("Select Component : ");
            selectComponent = sc.nextInt();

            if(selectComponent == 1 || selectComponent == 2 || selectComponent == 3 ||
            selectComponent == 4 || selectComponent == 5 || selectComponent == 6) {
                break;
            }
            else {
                System.out.println("Wrong Input!!!\n");
            }
        }

        return selectComponent;
    }

<<<<<<< Updated upstream
    void viewComponentsPage(String components) {
=======
    void viewComponentsPage(String components) throws IOException {

        BufferedReader br;
        int i;

        switch(components) {
            case "size":
                br = new BufferedReader(new FileReader("DB/size.txt"));
                i = 1;

                while(true) {
                    String line = br.readLine();
                    if(line == null)
                        break;
                    String[] lineList = line.split(",");
                    System.out.println(Integer.toString(i) + ". " + lineList[0] + " Price : " + lineList[1]);
                    i++;
                }

                br.close();
                break;
            case "bread":
                br = new BufferedReader(new FileReader("DB/bread.txt"));
                i = 1;

                while(true) {
                    String line = br.readLine();
                    if(line == null)
                        break;
                    String[] lineList = line.split(",");
                    System.out.println(Integer.toString(i) + ". " + lineList[0] + " Price : " + lineList[1]);
                    i++;
                }

                br.close();
                break;
            case "ingredient":
                br = new BufferedReader(new FileReader("DB/ingredient.txt"));
                i = 1;

                while(true) {
                    String line = br.readLine();
                    if(line == null)
                        break;
                    String[] lineList = line.split(",");
                    System.out.println(Integer.toString(i) + ". " + lineList[0] + " Price : " + lineList[1]);
                    i++;
                }

                br.close();
                break;
            case "sauce":
                br = new BufferedReader(new FileReader("DB/sauce.txt"));
                i = 1;

                while(true) {
                    String line = br.readLine();
                    if(line == null)
                        break;
                    String[] lineList = line.split(",");
                    System.out.println(Integer.toString(i) + ". " + lineList[0] + " Price : " + lineList[1]);
                    i++;
                }

                br.close();
                break;
            case "beverage":
                br = new BufferedReader(new FileReader("DB/beverage.txt"));
                i = 1;

                while(true) {
                    String line = br.readLine();
                    if(line == null)
                        break;
                    String[] lineList = line.split(",");
                    System.out.println(Integer.toString(i) + ". " + lineList[0] + " Price : " + lineList[1]);
                    i++;
                }

                br.close();
                break;
            case "side":
                br = new BufferedReader(new FileReader("DB/side.txt"));
                i = 1;

                while(true) {
                    String line = br.readLine();
                    if(line == null)
                        break;
                    String[] lineList = line.split(",");
                    System.out.println(Integer.toString(i) + ". " + lineList[0] + " Price : " + lineList[1]);
                    i++;
                }

                br.close();
                break;
        }
>>>>>>> Stashed changes
    }

    void viewRecipesPage(String recipes) {
        String[] recipe=recipes.split("\n");
        for(int i=0; i<recipe.length; i++) {
            String[] components = recipe[i].split("/");

            String recipeName=components[0];

            int recipePrice=0;
            Pattern pattern=Pattern.compile("_(([0-9])\\w+),");
            for(int j=1; j<components.length; j++){
                Matcher matcher=pattern.matcher(components[j]);
                while (matcher.find()){
                    recipePrice+=Integer.parseInt(matcher.group(1));
                }
            }

            System.out.println("Recipe Name:"+recipeName+" Recipe price:"+recipePrice);
        }
    }
}

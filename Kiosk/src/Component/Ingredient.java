package Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ingredient implements Component{
    private String name;
    private int price;
    private int amount;

    public Ingredient(String name, String price, String amount) {
        this.name = name;
        this.price = Integer.parseInt(price);
        this.amount = Integer.parseInt(amount);
    }

    public Ingredient() {
        this.name = "";
        this.price = 0;
        this.amount = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String loadComponents() {
        String contents = "";
        File file = new File("../DB/ingredient.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                contents = contents + line + "\r\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

    @Override
    public void addComponents() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter new ingredient name : ");
        String str = scan.nextLine();
        setName(str);

        System.out.print("Enter price of new ingredient : ");
        int price = scan.nextInt();
        scan.nextLine();
        setPrice(price);

        System.out.print("Enter amount of new ingredient : ");
        int amount = scan.nextInt();
        scan.nextLine();
        setAmount(amount);

        try{
            File file = new File("../DB/ingredient.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(getName() + "," + getPrice() + "," + getAmount()+"\r\n");
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyComponents(){
        ArrayList<Component> list = new ArrayList<>();
        try{
            File file = new File("../DB/ingredient.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] box = line.split(",");
                Ingredient ingredient = new Ingredient(box[0], box[1], box[2]);
                list.add(ingredient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter ingredient name you want to modify : ");
        String str = scan.nextLine();
        int i = 0;
        for(Component ingredient : list){
            if(str.equals(ingredient.getName())){
                break;
            }
            else i++;
        }

        System.out.println("1. Modify ingredient name");
        System.out.println("2. Modify price of ingredient");
        System.out.println("3. Modify amount of ingredient");
        System.out.print(">>");
        int input = scan.nextInt();
        scan.nextLine();

        if(input == 1){
            System.out.print("Enter new ingredient name : ");
            str = scan.nextLine();
            list.get(i).setName(str);
        }
        else if(input == 2){
            System.out.print("Enter new price of ingredient : ");
            int newprice = scan.nextInt();
            scan.nextLine();
            list.get(i).setPrice(newprice);
        }
        else if(input == 3){
            System.out.print("Enter new amount of ingredient : ");
            int newamount = scan.nextInt();
            scan.nextLine();
            list.get(i).setAmount(newamount);
        }

        try{
            File file = new File("../DB/ingredient.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component ingredient : list) {
                writer.write(ingredient.getName() + "," + ingredient.getPrice() + "," + ingredient.getAmount()+"\r\n");
                
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteComponents() {
        ArrayList<Component> list = new ArrayList<>();
        try{
            File file = new File("../DB/ingredient.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] box = line.split(",");
                Ingredient ingredient = new Ingredient(box[0], box[1], box[2]);
                list.add(ingredient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter ingredient name you want to delete : ");
        String name = scan.nextLine();
        int i = 0;

        for(Component ingredient : list){
            if(name.equals(ingredient.getName()))
                break;
            else
                i++;
        }

        list.remove(i);

        try{
            File file = new File("../DB/ingredient.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component ingredient : list) {
                writer.write(ingredient.getName() + "," + ingredient.getPrice() + "," + ingredient.getAmount()+"\r\n");
                
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayComponents() {
        System.out.println("재료 이름 : " + this.getName() + ", 재료 가격 : " + this.getPrice() + ", 재료의 양 : " + this.getAmount());
    }
}


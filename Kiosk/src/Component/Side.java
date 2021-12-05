package Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Side implements Component{
    private String name;
    private int price;
    private int amount;

    public Side(String name, String price, String amount) {
        this.name = name;
        this.price = Integer.parseInt(price);
        this.amount = Integer.parseInt(amount);
    }

    public Side() {
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
        File file = new File("../DB/side.txt");
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
        System.out.print("Enter new side menu name : ");
        String str = scan.nextLine();
        setName(str);

        System.out.print("Enter price of new side menu : ");
        int price = scan.nextInt();
        scan.nextLine();
        setPrice(price);

        System.out.print("Enter amount of new side menu : ");
        int amount = scan.nextInt();
        scan.nextLine();
        setAmount(amount);

        try{
            File file = new File("../DB/side.txt");
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
    public void modifyComponents() {
        ArrayList<Component> list = new ArrayList<>();
        try{
            File file = new File("../DB/side.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] box = line.split(",");
                Side side = new Side(box[0], box[1], box[2]);
                list.add(side);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        String str = "";
        int i;
        do{
            i = 0;
            System.out.print("Enter side menu name you want to modify : ");
            str = scan.nextLine();

            for(Component side : list){
                if(!str.equals(side.getName()))
                    i++;
                else break;
            }

            if(i == list.size())
                System.out.println("The side menu does not exist. Please try again.");
        }while(i == list.size());


        System.out.println("1. Modify side menu name");
        System.out.println("2. Modify price of side menu");
        System.out.println("3. Modify amount of side menu");
        System.out.print(">>");
        int input = scan.nextInt();
        scan.nextLine();

        if(input == 1){
            System.out.print("Enter new side menu name : ");
            str = scan.nextLine();
            list.get(i).setName(str);
        }
        else if(input == 2){
            System.out.print("Enter new price of side menu : ");
            int newprice = scan.nextInt();
            scan.nextLine();
            list.get(i).setPrice(newprice);
        }
        else if(input == 3){
            System.out.print("Enter new amount of side menu : ");
            int newamount = scan.nextInt();
            scan.nextLine();
            list.get(i).setAmount(newamount);
        }

        try{
            File file = new File("../DB/side.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component side : list) {
                writer.write(side.getName() + "," + side.getPrice() + "," + side.getAmount()+"\r\n");

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
            File file = new File("../DB/side.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] box = line.split(",");
                Side side = new Side(box[0], box[1], box[2]);
                list.add(side);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter side menu name you want to delete : ");
        String name = scan.nextLine();
        int i = 0;

        for(Component side : list){
            if(name.equals(side.getName()))
                break;
            else
                i++;
        }

        list.remove(i);

        try{
            File file = new File("../DB/side.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component side : list) {
                writer.write(side.getName() + "," + side.getPrice() + "," + side.getAmount()+"\r\n");

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayComponents() {
        System.out.println("사이드메뉴 이름 : " + this.getName() + ", 사이드메뉴 가격 : " + this.getPrice() + ", 사이드메뉴 양 : " + this.getAmount());
    }
}
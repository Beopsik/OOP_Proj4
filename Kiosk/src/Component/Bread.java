package Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Bread implements Component{
    private String name;
    private int price;
    private int amount;

    public Bread(String name, String price, String amount) {
        this.name = name;
        this.price = Integer.parseInt(price);
        this.amount = Integer.parseInt(amount);
    }

    public Bread() {
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
        File file = new File("../DB/bread.txt");
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
        System.out.print("Enter new bread name : ");
        String str = scan.nextLine();
        setName(str);

        System.out.print("Enter price of new bread : ");
        int price = scan.nextInt();
        scan.nextLine();
        setPrice(price);

        System.out.print("Enter amount of new bread : ");
        int amount = scan.nextInt();
        scan.nextLine();
        setAmount(amount);

        Path relativePath = Paths.get("");
        String path = relativePath.toAbsolutePath().toString();
        System.out.println("Working Directory = " + path);
        try{
            File file = new File("../DB/bread.txt");
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
            File file = new File("../DB/bread.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] box = line.split(",");
                Bread bread = new Bread(box[0], box[1], box[2]);
                list.add(bread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter bread name you want to modify : ");
        String str = scan.nextLine();
        int i = 0;
        for(Component bread : list){
            if(str.equals(bread.getName())){
                break;
            }
            else i++;
        }

        System.out.println("1. Modify bread name");
        System.out.println("2. Modify price of bread");
        System.out.println("3. Modify amount of bread");
        System.out.print(">>");
        int input = scan.nextInt();
        scan.nextLine();

        if(input == 1){
            System.out.print("Enter new bread name : ");
            str = scan.nextLine();
            list.get(i).setName(str);
        }
        else if(input == 2){
            System.out.print("Enter new price of bread : ");
            int newprice = scan.nextInt();
            scan.nextLine();
            list.get(i).setPrice(newprice);
        }
        else if(input == 3){
            System.out.print("Enter new amount of bread : ");
            int newamount = scan.nextInt();
            scan.nextLine();
            list.get(i).setAmount(newamount);
        }

        try{
            File file = new File("../DB/bread.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component bread : list) {
                writer.write(bread.getName() + "," + bread.getPrice() + "," + bread.getAmount()+"\r\n");
                
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
            File file = new File("../DB/bread.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] box = line.split(",");
                Bread bread = new Bread(box[0], box[1], box[2]);
                list.add(bread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter bread name you want to delete : ");
        String name = scan.nextLine();
        int i = 0;

        for(Component bread : list){
            if(name.equals(bread.getName()))
                break;
            else
                i++;
        }

        list.remove(i);

        try{
            File file = new File("../DB/bread.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component bread : list) {
                writer.write(bread.getName() + "," + bread.getPrice() + "," + bread.getAmount()+"\r\n");

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayComponents() {
        System.out.println("빵 이름 : " + this.getName() + ", 빵 가격 : " + this.getPrice() + ", 빵의 갯수 : " + this.getAmount());
    }
}

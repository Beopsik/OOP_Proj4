package Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Size implements Component {
    private String name;
    private int price;
    private int amount;

    public Size(String name, String price, String amount) {
        this.name = name;
        this.price = Integer.parseInt(price);
        this.amount = Integer.parseInt(amount);
    }

    public Size() {
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
        File file = new File("../DB/size.txt");
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
        System.out.print("Enter size name : ");
        String str = scan.nextLine();
        setName(str);

        System.out.print("Enter new price of size : ");
        int price = scan.nextInt();
        scan.nextLine();
        setPrice(price);

        setAmount(0);

        try{
            File file = new File("../DB/size.txt");
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
            File file = new File("../DB/size.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";

            while((line = reader.readLine()) != null){
                String[] box = line.split(",");
                Size size = new Size(box[0], box[1], box[2]);
                list.add(size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        String str = "";
        int i;
        do{
            i = 0;
            System.out.print("Enter size name you want to modify : ");
            str = scan.nextLine();

            for(Component size : list){
                if(!str.equals(size.getName()))
                    i++;
                else break;
            }

            if(i == list.size())
                System.out.println("The size does not exist. Please try again.");
        }while(i == list.size());

        System.out.println("1. Modify size");
        System.out.println("2. Modify price of size");
        System.out.print(">>");
        int input = scan.nextInt();
        scan.nextLine();

        if(input == 1){
            System.out.print("Enter new size : ");
            str = scan.nextLine();
            list.get(i).setName(str);
        }
        else if(input == 2){
            System.out.print("Enter price of new size : ");
            int newprice = scan.nextInt();
            scan.nextLine();
            list.get(i).setPrice(newprice);
        }

        try{
            File file = new File("../DB/size.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component size : list) {
                writer.write(size.getName() + "," + size.getPrice() + "," + size.getAmount()+"\r\n");

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
            File file = new File("../DB/size.txt");
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
        System.out.print("Enter size you want to delete : ");
        String name = scan.nextLine();
        int i = 0;

        for(Component size : list){
            if(name.equals(size.getName()))
                break;
            else
                i++;
        }

        list.remove(i);

        try{
            File file = new File("../DB/size.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component size : list) {
                writer.write(size.getName() + "," + size.getPrice() + "," + size.getAmount()+"\r\n");

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayComponents() {
        System.out.println("크기 : " + this.getName() + ", 크기의 가격 : " + this.getPrice() + ", 크기의 양 : " + this.getAmount());
    }
}

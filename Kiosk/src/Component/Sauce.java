package Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Sauce implements Component{
    private String name;
    private int price;
    private int amount;

    public Sauce(String name, String price, String amount) {
        this.name = name;
        this.price = Integer.parseInt(price);
        this.amount = Integer.parseInt(amount);
    }

    public Sauce() {
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
        File file = new File("../DB/Sauce.txt");
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
        System.out.print("추가할 소스의 이름 : ");
        String str = scan.nextLine();
        setName(str);

        System.out.print("추가할 소스의 가격 : ");
        int price = scan.nextInt();
        setPrice(price);

        System.out.print("추가할 소스의 양 : ");
        int amount = scan.nextInt();
        setAmount(amount);

        try{
            File file = new File("../DB/Sauce.txt");
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
            File file = new File("../DB/Sauce.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                String[] box = line.split(",");
                Sauce sauce = new Sauce(box[0], box[1], box[2]);
                list.add(sauce);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        String str = "";
        int i;
        do{
            i = 0;
            System.out.print("Enter sauce name you want to modify : ");
            str = scan.nextLine();

            for(Component sauce : list){
                if(!str.equals(sauce.getName()))
                    i++;
                else break;
            }

            if(i == list.size())
                System.out.println("The sauce does not exist. Please try again.");
        }while(i == list.size());

        System.out.println("1. 소스의 이름 수정하기");
        System.out.println("2. 소스의 가격 수정하기");
        System.out.println("3. 소스의 갯수 수정하기");
        System.out.print(">>");
        int input = scan.nextInt();

        if(input == 1){
            System.out.print("수정한 소스의 이름 : ");
            str = scan.nextLine();
            list.get(i).setName(str);
        }
        else if(input == 2){
            System.out.print("수정한 소스의 가격 : ");
            int newprice = scan.nextInt();
            list.get(i).setPrice(newprice);
        }
        else if(input == 3){
            System.out.print("수정한 소스의 갯수 : ");
            int newamount = scan.nextInt();
            list.get(i).setAmount(newamount);
        }

        try{
            File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Bread.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component sauce : list) {
                writer.write(sauce.getName() + "," + sauce.getPrice() + "," + sauce.getAmount()+"\r\n");
                
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
            File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Bread.txt");
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
        System.out.print("삭제할 소스 이름 : ");
        String name = scan.nextLine();
        int i = 0;

        for(Component sauce : list){
            if(name.equals(sauce.getName()))
                break;
            else
                i++;
        }

        list.remove(i);

        try{
            File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Bread.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component sauce : list) {
                writer.write(sauce.getName() + "," + sauce.getPrice() + "," + sauce.getAmount()+"\r\n");
                
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayComponents() {
        System.out.println("소스 이름 : " + this.getName() + ", 소스 가격 : " + this.getPrice() + ", 소스 양 : " + this.getAmount());
    }
}

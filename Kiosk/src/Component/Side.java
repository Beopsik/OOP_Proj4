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
        File file = new File("DB/Side.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null){
                contents = contents + line + "\r\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(contents);
        return contents;
    }

    @Override
    public void addComponents() {
        Scanner scan = new Scanner(System.in);
        System.out.print("추가할 사이드메뉴의 이름 : ");
        String str = scan.nextLine();
        setName(str);

        System.out.print("추가할 사이드메뉴의 가격 : ");
        int price = scan.nextInt();
        setPrice(price);

        System.out.print("추가할 사이드메뉴의 양 : ");
        int amount = scan.nextInt();
        setAmount(amount);

        try{
            File file = new File("DB/Side.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(getName() + "," + getPrice() + "," + getAmount());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void modifyComponents() {
        ArrayList<Component> list = new ArrayList<>();
        try{
            File file = new File("DB/Side.txt");
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
        System.out.print("수정할 사이드메뉴의 이름 : ");
        String str = scan.nextLine();
        int i = 0;
        for(Component side : list){
            if(str.equals(side.getName())){
                break;
            }
            else i++;
        }

        System.out.println("1. 사이드메뉴의 이름 수정하기");
        System.out.println("2. 사이드메뉴의 가격 수정하기");
        System.out.println("3. 사이드메뉴의 갯수 수정하기");
        System.out.print(">>");
        int input = scan.nextInt();

        if(input == 1){
            System.out.print("수정한 사이드메뉴의 이름 : ");
            str = scan.nextLine();
            list.get(i).setName(str);
        }
        else if(input == 2){
            System.out.print("수정한 사이드메뉴의 가격 : ");
            int newprice = scan.nextInt();
            list.get(i).setPrice(newprice);
        }
        else if(input == 3){
            System.out.print("수정한 사이드메뉴의 갯수 : ");
            int newamount = scan.nextInt();
            list.get(i).setAmount(newamount);
        }

        try{
            File file = new File("DB/Side.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component side : list) {
                writer.write(side.getName() + "," + side.getPrice() + "," + side.getAmount());
                writer.newLine();
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
            File file = new File("DB/Side.txt");
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
        System.out.print("삭제할 사이드메뉴 이름 : ");
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
            File file = new File("DB/Side.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component side : list) {
                writer.write(side.getName() + "," + side.getPrice() + "," + side.getAmount());
                writer.newLine();
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


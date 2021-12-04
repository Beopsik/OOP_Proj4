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
        File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Size.txt");
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
        System.out.print("추가할 크기 : ");
        String str = scan.nextLine();
        setName(str);

        System.out.print("추가할 크기의 : ");
        int price = scan.nextInt();
        setPrice(price);

        System.out.print("추가할 크기의 양 : ");
        int amount = scan.nextInt();
        setAmount(amount);

        try{
            File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Size.txt");
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
    public void modifyComponents(){
        ArrayList<Component> list = new ArrayList<>();
        try{
            File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Size.txt");
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
        System.out.print("수정할 크기 : ");
        String str = scan.nextLine();
        int i = 0;
        for(Component size : list){
            if(str.equals(size.getName())){
                break;
            }
            else i++;
        }

        System.out.println("1. 크기 수정하기");
        System.out.println("2. 크기의 가격 수정하기");
        System.out.println("3. 크기의 양 수정하기");
        System.out.print(">>");
        int input = scan.nextInt();

        if(input == 1){
            System.out.print("크기 : ");
            str = scan.nextLine();
            list.get(i).setName(str);
        }
        else if(input == 2){
            System.out.print("크기의 가격 : ");
            int newprice = scan.nextInt();
            list.get(i).setPrice(newprice);
        }
        else if(input == 3){
            System.out.print("크기의 양 : ");
            int newamount = scan.nextInt();
            list.get(i).setAmount(newamount);
        }

        try{
            File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Size.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component size : list) {
                writer.write(size.getName() + "," + size.getPrice() + "," + size.getAmount());
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
            File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Size.txt");
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
        System.out.print("삭제할 크기 : ");
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
            File file = new File("C:\\Users\\hanjonguk\\IdeaProjects\\OOPpro4\\Size.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Component size : list) {
                writer.write(size.getName() + "," + size.getPrice() + "," + size.getAmount());
                writer.newLine();
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


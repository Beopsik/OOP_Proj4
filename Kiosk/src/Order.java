import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Order {
    private View view;
    public Order(){
        this.view=new View();
    }

    public void order(String recipes){
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st=new StringTokenizer(br.readLine());

            String result=view.viewRecipesPage(recipes);

            int checkOrder;
            while(true) {
                System.out.println("Is the order information correct?");
                System.out.println("1. yes  2. no");
                checkOrder=Integer.parseInt(st.nextToken());

                if(checkOrder==1||checkOrder==2){
                    break;
                }else{
                    System.out.println("Wrong input. Enter the number.");
                }
            }
            if(checkOrder==1){

            }else{
                return;
            }


        }catch (IOException e){
            System.out.println(e);
        }
    }
}

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;

public class Order {
    private View view;
    public Order(){
        this.view=new View();
    }

    public void order(String recipes) throws Exception{
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            System.out.println("--------Order Detail--------");
            String orderRequest=view.viewRecipesPage(recipes);

            int checkOrder;
            while(true) {
                System.out.println("Is the order information correct?");
                System.out.println("1. yes(pay)  2. no(cancel)");
                st=new StringTokenizer(br.readLine());
                checkOrder=Integer.parseInt(st.nextToken());

                if(checkOrder==1||checkOrder==2){
                    break;
                }else{
                    System.out.println("Wrong input. Enter the number.");
                }
            }
            if(checkOrder==1){
                Socket socket = new Socket("127.0.0.1", 1234); // 소켓 서버에 접속
                System.out.println("Successfully accessed the server.");

                // OutputStream - 클라이언트에서 Server로 메세지 발송
                OutputStream out = socket.getOutputStream();
                // socket의 OutputStream 정보를 OutputStream out에 넣은 뒤
                PrintWriter writer = new PrintWriter(out, true);
                // PrintWriter에 위 OutputStream을 담아 사용

                writer.println(orderRequest+"EOF");
                // 클라이언트에서 서버로 메세지 보내기

                // InputStream - Server에서 보낸 메세지 클라이언트로 가져옴
                InputStream input = socket.getInputStream();
                // socket의 InputStream 정보를 InputStream in에 넣은 뒤
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                // BufferedReader에 위 InputStream을 담아 사용

                System.out.println("--------Payment Detail--------");
                String orderResponse="";
                String orderStr="";
                while(!(orderStr=reader.readLine()).equals("EOF")) {
                    orderResponse+=orderStr+"\n";
                }
                System.out.println(orderResponse);

                int checkPay;
                while(true) {
                    System.out.println("Is the payment details correct?");
                    System.out.println("1. yes  2. no");
                    st = new StringTokenizer(br.readLine());
                    checkPay = Integer.parseInt(st.nextToken());

                    if (checkPay == 1 || checkPay == 2) {
                        break;
                    } else {
                        System.out.println("Wrong input. Enter the number.");
                    }
                }
                if(checkPay==1){
                    writer.println("yes\n"+"EOF");
                    String payResponse="";
                    String payStr="";
                    while(!(payStr=reader.readLine()).equals("EOF")) {
                        payResponse+=payStr+"\n";
                    }
                    System.out.println(payResponse);

                    String completeResponse="";
                    String completeStr="";
                    while(!(completeStr=reader.readLine()).equals("EOF")) {
                        completeResponse+=completeStr+"\n";
                    }
                    System.out.println(completeResponse);
                }else{
                    writer.println("no\n"+"EOF");
                    throw new InvalidObjectException("Wrong payment detail");
                }
//                System.out.println(reader.readLine());
                // 서버에서 온 메세지 확인
                System.out.println("CLIENT SOCKET CLOSE");
                socket.close(); // 소켓 종료
            }else{
                throw new InvalidObjectException("Wrong order information");
            }
        }catch (SocketException e){
            System.out.println(e);
        }
    }
}

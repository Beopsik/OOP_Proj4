import java.io.*;
import java.net.Socket;
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
                    Socket socket = new Socket("127.0.0.1", 1234); // 소켓 서버에 접속
                    System.out.println("Successfully accessed the server.");

                    // OutputStream - 클라이언트에서 Server로 메세지 발송
                    OutputStream out = socket.getOutputStream();
                    // socket의 OutputStream 정보를 OutputStream out에 넣은 뒤
                    PrintWriter writer = new PrintWriter(out, true);
                    // PrintWriter에 위 OutputStream을 담아 사용

                    writer.println(result);
                    // 클라이언트에서 서버로 메세지 보내기

                    // InputStream - Server에서 보낸 메세지 클라이언트로 가져옴
                    InputStream input = socket.getInputStream();
                    // socket의 InputStream 정보를 InputStream in에 넣은 뒤
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    // BufferedReader에 위 InputStream을 담아 사용

                    System.out.println(reader.readLine());
                    // 서버에서 온 메세지 확인
                    System.out.println("CLIENT SOCKET CLOSE");
                    socket.close(); // 소켓 종료
            }else{
                return;
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}

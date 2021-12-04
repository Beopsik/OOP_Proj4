import Component.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String phoneNo;
    private static String userType;
    private static View view;
    private static Component component;
    private static Recipe recipe;

    public static void main(String[] args) {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));;
        StringTokenizer st;
        try {
            view=new View();
//            /*
//            * 로그인으로 고객과 관리자를 구분합니다.
//            * */
//            view.viewLoginPage();
//            st=new StringTokenizer(br.readLine());
//            phoneNo=st.nextToken();
//            userType=login(phoneNo);
//
//            view.viewMenuPage(userType);


        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static String login(String phoneNo){
        if(phoneNo.equals("admin")){
            return "admin";
        }

        return "customer";
    }

    private static void deleteCustomerInfo(String phoneNo){

    }

    private static void logout(){

    }
}

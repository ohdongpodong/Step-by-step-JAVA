package Board;
import java.util.Scanner;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


class DBConnection {

    Connection con = null;
    Statement stmt = null; // SQL 문을 데이터베이스에 보내기위한 객체
    ResultSet result = null; // SQL 질의에 의해 생성된 테이블을 저장하는 객체
    String Select_Data[];
    HashMap<String, String> list_contents = new HashMap();

    // # 1. JDBC Mysql 세팅 정보
    final String driver = "com.mysql.jdbc.Driver";
    final String url = "jdbc:mysql://localhost:3306/command_type_board?useSSL=false";
    final String user = "root";
    final String password = "space0707";


    public void Connect() {
        try {
            // # 드라이버 로딩
            Class.forName(driver);

            // # DB 연결하기
            con = DriverManager.getConnection(url,user, password);

            // # 쿼리 수행을 위한 Statement 객체를 생성
            stmt = con.createStatement();

        } catch ( ClassNotFoundException e1 ){
            System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");
        } catch ( SQLException e ) {
            System.out.println("SQL 에러 : " + e);
        }

    }

    public void QueryExcute(String sql) {

        try {
            result = stmt.executeQuery(sql);
        } catch( SQLException e){
            System.out.println("SQL 실행 에러 " + e);
        }
        System.out.println(" 쿼리가 정상 실행되었습니다.");

    }

    public HashMap SelectResult(String sql) {

        try {
            result = stmt.executeQuery(sql);
            ResultSetMetaData resultmd = result.getMetaData();
            // # 열의 개수 구하기
            int colCount = resultmd.getColumnCount();

            // # 행의 개수 구하기
            result.last();
            int rowCount = result.getRow();

            // # 모든 데이터 배열에 넣기
            result.absolute(0);
            for(int i = 1; i <= rowCount; i++){ // 행
                result.next();
                for(int j = 1; j <= colCount; j++ ){

                    list_contents.put(resultmd.getColumnName(j),result.getString(j));
                }
            }

            //Select_Data[0] = "123";

        } catch (SQLException e){
            System.out.println("SELECT문 실행 에러 " + e);
        }
        return list_contents;
    }

}

public class BoardList {

    static int page_list_count = 10;
    static int now_page;
    static int all_list_count = 14;
    static int action;


    public static void View_List(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("   1                제목입니다.             오동진     2019-07-23     3   ");
            if (i == count - 1) {
                System.out.println("-----------------------------------------------------------------------");
            }
        }
    }


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        DBConnection selectall = new DBConnection();

        selectall.Connect();
        String SQL = "SELECT * FROM user_infomation";
        HashMap hashmap = selectall.SelectResult(SQL);

        System.out.println("해쉬맵 : " + hashmap.keySet()  + " : " +hashmap.values());

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("| 번호 |              제목              |  글쓴이  |     날짜     |  조회수  |");
        System.out.println("-----------------------------------------------------------------------");
        if (all_list_count >= page_list_count) {
            View_List(page_list_count);
        } else {
            View_List(all_list_count);
        }
        System.out.println("                     1 2 3 4 5 6 7 8 9 10                              ");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("1.게시글 상세보기  2.글쓰기  3.다른 페이지 글 보기");
        System.out.printf("원하는 행동의 번호를 입력해주세요 : ");
        action = Integer.parseInt(scanner.nextLine());


        if(action == 1){
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("  제목  |    제목입니다.                                                              ");
            System.out.println(" 글쓴이  |   오동진                                                           ");
            System.out.println("  날짜  |   2019-07-23                                                           ");
            System.out.println("  내용  |                                                              ");
            System.out.println("       |                                                              ");
            System.out.println("       |                                                              ");
            System.out.println("       |                                                              ");
            System.out.println("-----------------------------------------------------------------------");

        } else if (action == 2){

            while(true) {
                System.out.print("  제목  |  ");
                String title = scanner.nextLine();
                System.out.print("  내용  |  ");
                String contents = scanner.nextLine();
                System.out.println("  작성한 내용을 저장하시겠습니까? ( 예 / 아니요 )  ");
                String save_yn = scanner.nextLine();
                if (save_yn == "예") {
                    break;
                } else if (save_yn == "아니요") {
                    break;
                } else {
                    System.out.println(" 잘못된 값을 입력하셨습니다.");
                }
            }



        } else if (action == 3){

        } else {

        }
    }
}
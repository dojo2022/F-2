package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Test2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection conn = null;

		try {


		//Map<String,String> taskmap = new HashMap<>();

		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/database/imoketu", "sa", "");

		// SQL文を準備する
		String sql = "SELECT Task_Id,Task_Limit,State_Flag FROM Task";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();


		//結果表をコレクションにコピーする


		while(rs.next()) {
			//DateTime x = rs.getDateTime("Task_Limit");
			//String TI = rs.getDateTime(0);

			Integer x = rs.getInt("Task_Id");
			String taskid = x.toString();
			Timestamp tasklimit = rs.getTimestamp("Task_Limit");
			//String tasklimit = (rs.getTimestamp("Task_Limit")).toString();
			Integer y = rs.getInt("State_Flag");
			String stateflag = y.toString();

			System.out.println("DBの期限をだしていく（timestampクラス）：" + tasklimit);

			Calendar currenttime = Calendar.getInstance();
		    System.out.println("現在時刻を取得する（calendarクラス）" + currenttime.getTime());

		    //日時を格納するためのDateクラスを宣言(現在時刻)
	        Date date2day = new Date();
	        Date date1day = new Date();
	        Date date0day = new Date();
	        Date date3hours = new Date();
	        Date date1hour = new Date();

//		    //Date型の持つ日時の2日後を表示(日時の加算)
//		    currenttime.add(Calendar.DAY_OF_MONTH, +2);
//	        date2day = currenttime.getTime();
//	        //System.out.println("2日後の日時：" + date2day);
//
//	        //Date型の持つ日時の1日後を表示(日時の加算)
//	        currenttime.add(Calendar.DAY_OF_MONTH, +1);
//	        date1day = currenttime.getTime();
//	        System.out.println("1日後の日時：" + date1day);
//
//	        //Date型の持つ日時の0日後を表示(日時の加算)
//	        currenttime.add(Calendar.DAY_OF_MONTH, +0);
//	        date0day = currenttime.getTime();
//
//	        //Date型の持つ日時の3時間後を表示(日時の加算)
//	        currenttime.add(Calendar.HOUR_OF_DAY, +3);
//	        date3hours = currenttime.getTime();

	        //可変長型配列を作成
	        //ArrayList<String> alert = new ArrayList<>();
	        String[][] alert = {
	        		{"/imoketu/audio/006_未着手・未着手1時間前.wav","バカ兄貴ーはやしくしろー！！"},
	        		{"/imoketu/audio/005_未完了・未着手3時間前.wav","3時間前よ、はやくしなさいって！"},
	        		{"/imoketu/audio/004_未着手・未完了当日ver2.wav","今日締め切りのタスクがあるわよ、わかってるんでしょうね"},
	        		{"/imoketu/audio/003_未着手一日前ver2.wav","明日締め切りのタスクがあるわよ、大丈夫なの？"},
	        		{"/imoketu/audio/002_未着手二日前.wav","明後日締め切りのタスクがあるわよ、ちゃんと余裕を持ってやりなさいよ"}
	        };

	        //Date型の持つ日時の1時間後を表示(日時の加算)
	        currenttime.add(Calendar.HOUR_OF_DAY, +1);
	        date1hour = currenttime.getTime();
	        System.out.println("1時間後の日時：" + date1hour);
	        if(tasklimit.compareTo(date1hour) < 1) {
	        	//アラートの音声パスとアラート文を格納
//	        	alert.add("/imoketu/audio/001_タスク追加時.wav");
//	        	alert.add("バカ兄貴ーはやしくしろー！！");
//	        	System.out.println("音声データ取得：" + alert.get(0));
//	        	System.out.println("アラート文字取得:" + alert.get(1));
	        }

	        //System.out.println("DEBUG:"+alert.get(0));

	        //Date型の持つ日時の3時間後を表示(日時の加算)
	        currenttime.add(Calendar.HOUR_OF_DAY, +2);
	        date3hours = currenttime.getTime();
	        System.out.println("3時間後の日時：" + date3hours);
	        if(tasklimit.compareTo(date3hours) < 1){
	        	//アラートの音声パスとアラート文を格納
//	        	alert.add("/imoketu/audio/001_タスク追加時.wav");
//	        	alert.add("3時間前よ、はやくしなさいって！");
//	        	System.out.println("音声データ取得：" + alert.get(0));
//	        	System.out.println("アラート文字取得" + alert.get(1));
	        }

	        //タスク期限が現在時刻から2日前に入っているのか判定
//	        System.out.println("タスク期限が「2日前の場合-1、同じなら0、違うなら1」を表示：" + tasklimit.compareTo(date2day));

	        //タスク期限が現在時刻から1日前に入っているのか判定
//	        System.out.println("タスク期限が「1日前の場合-1、同じなら0、違うなら1」を表示：" + tasklimit.compareTo(date1day));

	        //タスク期限が現在時刻から当日に入っているのか判定
//	        System.out.println("タスク期限が「当日の場合-1、同じなら0、違うなら1」を表示：" + tasklimit.compareTo(date0day));

	        //タスク期限が現在時刻から3時間前に入っているのか判定
//	        System.out.println("タスク期限が「3時間前の場合-1、同じなら0、違うなら1」を表示：" + tasklimit.compareTo(date3hours));

	        //タスク期限が現在時刻から1時間前に入っているのか判定
//	        System.out.println("タスク期限が「1時間前の場合-1、同じなら0、違うなら1」を表示：" + tasklimit.compareTo(date1hour));

	        //可変長型配列を準備
//	        ArrayList<String> alert = new ArrayList<>();

//	        if(tasklimit.compareTo(date1hour) < 1) {
//	        	//アラートの音声パスとアラート文を格納
//	        	ArrayList<String> alert = new ArrayList<>();
//	        	alert.add("/imoketu/audio/001_タスク追加時.wav");
//	        	alert.add("バカ兄貴ーはやしくしろー！！");
//	        	System.out.println("音声データ取得：" + alert.get(0));
//	        	System.out.println("アラート文字取得" + alert.get(1));
//	        }else if(tasklimit.compareTo(date3hours) < 1){
//	        	//アラートの音声パスとアラート文を格納
//	        	ArrayList<String> alert = new ArrayList<>();
//	        	alert.add("/imoketu/audio/001_タスク追加時.wav");
//	        	alert.add("3時間前よ、はやくしなさいって！");
//	        	System.out.println("音声データ取得：" + alert.get(0));
//	        	System.out.println("アラート文字取得" + alert.get(1));
//	        }//else if(tasklimit.compareTo(date0day) < 1){
//	        	//アラートの音声パスとアラート文を格納
//	        	ArrayList<String> alert = new ArrayList<>();
//	        	alert.add("/imoketu/audio/001_タスク追加時.wav");
//	        	alert.add("今日締め切りのタスクがあるわよ、わかってるんでしょうね");
//	        	System.out.println("音声データ取得：" + alert.get(0));
//	        	System.out.println("アラート文字取得" + alert.get(1));
//	        }else if(tasklimit.compareTo(date1day) < 1) {
//	        	//アラートの音声パスとアラート文を格納
//	        	ArrayList<String> alert = new ArrayList<>();
//	        	alert.add("/imoketu/audio/001_タスク追加時.wav");
//	        	alert.add("明日締め切りのタスクがあるわよ、大丈夫なの？");
//	        	System.out.println("音声データ取得：" + alert.get(0));
//	        	System.out.println("アラート文字取得" + alert.get(1));
//	        }else if(tasklimit.compareTo(date2day) < 1) {
//	        	//アラートの音声パスとアラート文を格納
//	        	ArrayList<String> alert = new ArrayList<>();
//	        	alert.add("/imoketu/audio/001_タスク追加時.wav");
//	        	alert.add("明後日締め切りのタスクがあるわよ、ちゃんと余裕を持ってやりなさいよ");
//	        	System.out.println("音声データ取得：" + alert.get(0));
//	        	System.out.println("アラート文字取得" + alert.get(1));
//
//	        }


	    	//HTMLを出力
//	    	response.setContentType("text/html; charset=UTF-8");
//	    	PrintWriter out = response.getWriter();
//	    	out.println("<html>");
//	    	out.println("<head>");
//	    	out.println("<title>スッキリ占い</title>");
//	    	out.println("</head>");
//	    	out.println("<body>");
//	    	out.println("<p>" + today + "の運勢は「" + luck + "」です</p>");
//	    	out.println("</body>");
//	    	out.println("</html>");








	        System.out.println("");






		};
	}
		catch (SQLException e) {
			e.printStackTrace();
			//cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			//cardList = null;
		}


		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					//cardList = null;
				}
			}
		}



	}

}

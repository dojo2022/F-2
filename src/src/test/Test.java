package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO 自動生成されたメソッド・スタブ

		Connection conn = null;

		//5種類のセリフ最終再生時間を格納する変数を宣言し古い時刻を代入
		SimpleDateFormat LastPlaysdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String str = "2019/12/12 12:00";

		Date NextPlayTime1 = LastPlaysdf.parse(str);
		Date NextPlayTime2 = LastPlaysdf.parse(str);
		Date NextPlayTime3 = LastPlaysdf.parse(str);
		Date NextPlayTime4 = LastPlaysdf.parse(str);


        /*
		Timestamp LastPlayTime1 = new Timestamp(System.currentTimeMillis());
		Timestamp LastPlayTime2 = new Timestamp(System.currentTimeMillis());
		Timestamp LastPlayTime3 = new Timestamp(System.currentTimeMillis());
		Timestamp LastPlayTime4 = new Timestamp(System.currentTimeMillis());
		*/

		try {


		Map<String,String> taskmap = new HashMap<>();

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

			//タスクID・制限時刻・状態フラッグを各変数に代入
			Integer x = rs.getInt("Task_Id");
			String taskid = x.toString();
			//String tasklimit = (rs.getTimestamp("Task_Limit")).toString();
			Timestamp tasklimit = (rs.getTimestamp("Task_Limit"));
			Integer y = rs.getInt("State_Flag");
			String stateflag = y.toString();


			//現在時刻をタイムスタンプ型で代入
			 Timestamp currenttimestamp = new Timestamp(System.currentTimeMillis());
			 Date dt = new Date(currenttimestamp.getTime());
			 Date dt2 = new Date(tasklimit.getTime());

			// コンソール表示用の文字列を取得
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
            //String yyyyMMddHHmmssStr = sdf.format(currenttimestamp);
            System.out.println("タスクid "+taskid+"の制限時刻:"+sdf.format(dt2));



            //tasklimitが1時間以内であるかの判定値を代入
            Calendar cal = Calendar.getInstance();
			cal.setTime(dt2);

			cal.add(Calendar.HOUR, -1);
			System.out.println("1時間前：" + sdf.format(cal.getTime()));
			Date oneHourBefore = new Date((cal.getTime()).getTime());

			int oneHourBeforeFlag = currenttimestamp.compareTo(oneHourBefore);
			System.out.println(oneHourBeforeFlag);



			//tasklimitが3時間以内であるかの判定値を代入
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(dt2);

			cal2.add(Calendar.HOUR, -3);
			System.out.println("3時間前：" + sdf.format(cal2.getTime()));
			Date threeHourBefore = new Date((cal2.getTime()).getTime());

			int threeHourBeforeFlag = currenttimestamp.compareTo(threeHourBefore);
			System.out.println(threeHourBeforeFlag);


			//tasklimitが1日以内であるかの判定値を代入
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(dt2);

			cal3.add(Calendar.DATE, -1);
			System.out.println("1日前：" + sdf.format(cal3.getTime()));
			Date oneDayBefore = new Date((cal3.getTime()).getTime());

			int oneDayBeforeFlag = currenttimestamp.compareTo(oneDayBefore);
			System.out.println(oneDayBeforeFlag);


			//tasklimitが2日以内であるかの判定値を代入
			Calendar cal4 = Calendar.getInstance();
			cal4.setTime(dt2);

			cal4.add(Calendar.DATE, -2);
			System.out.println("2日前：" + sdf.format(cal4.getTime()));
			Date twoDayBefore = new Date((cal4.getTime()).getTime());

			int  twoDayBeforeFlag = currenttimestamp.compareTo(twoDayBefore);
			System.out.println(twoDayBeforeFlag);
			//System.out.println();

			if((currenttimestamp.compareTo(tasklimit))!=1 ) { //現在時刻がタスク期限を過ぎていないかどうか

				if(oneHourBeforeFlag==1  && (currenttimestamp.compareTo(NextPlayTime1))==1 ) { //1時間以内かつ3時間以内の再生が無いかどうか
					System.out.println("1時間前");




					//3時間後のタイムスタンプを格納
					Calendar calLP1 = Calendar.getInstance();
					calLP1.setTime(dt);
					calLP1.add(Calendar.HOUR, +3);
					NextPlayTime1 = new Date((calLP1.getTime()).getTime());
					System.out.println("LP1:"+NextPlayTime1);

				}else if(threeHourBeforeFlag==1  && (currenttimestamp.compareTo(NextPlayTime2))==1 ) {  //3時間以内かつ3時間以内の再生が無いかどうか

					System.out.println("3時間前");


					//3時間後のタイムスタンプを格納
					Calendar calLP2 = Calendar.getInstance();
					calLP2.setTime(dt);
					calLP2.add(Calendar.HOUR, +3);
					NextPlayTime2 = new Date((calLP2.getTime()).getTime());
					System.out.println("LP2:"+NextPlayTime2);
				}else if(oneDayBeforeFlag==1 && (currenttimestamp.compareTo(NextPlayTime3))==1 ) { //1日以内かつであり、3時間以内の再生が無いかどうか

					System.out.println("1日前");

					//3時間後のタイムスタンプを格納
					Calendar calLP3 = Calendar.getInstance();
					calLP3.setTime(dt);
					calLP3.add(Calendar.HOUR, +3);
					NextPlayTime3 = new Date((calLP3.getTime()).getTime());
					System.out.println("LP3:"+NextPlayTime3);
				}else if(twoDayBeforeFlag==1 && (currenttimestamp.compareTo(NextPlayTime4))==1 ) { //2日以内かつであり、3時間以内の再生が無いかどうか


					System.out.println("2日前");

					//3時間後のタイムスタンプを格納
					Calendar calLP4 = Calendar.getInstance();
					calLP4.setTime(dt);
					calLP4.add(Calendar.HOUR, +3);
					NextPlayTime1 = new Date((calLP4.getTime()).getTime());
					System.out.println("LP4:"+NextPlayTime4);
				}
			}else {
				Calendar caldelete = Calendar.getInstance();
				caldelete.setTime(dt2);

				caldelete.add(Calendar.MINUTE, +10);
				System.out.println("2日前：" + sdf.format(caldelete.getTime()));
				Date timeOverRange = new Date((caldelete.getTime()).getTime());

				if((currenttimestamp.compareTo(timeOverRange))!=1) {
					System.out.println("超過音声再生期間");
				}

			}
			System.out.println();





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

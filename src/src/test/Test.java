package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection conn = null;

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
			//DateTime x = rs.getDateTime("Task_Limit");
			//String TI = rs.getDateTime(0);

			Date x = rs.getDate("Task_Limit");
			Time y = rs.getTime("Task_Limit");

			String DATEstr = new SimpleDateFormat("yyyy-MM-dd").format(x);
			String TIMEstr = new SimpleDateFormat("hh:mm").format(y);

            System.out.println("String型 = " + DATEstr);
            System.out.println("String型 = " + TIMEstr);

			//int i = 1;
			//Integer t = rs.getInt("Task_Id");
			//taskmap.put("ID" + i, t.toString());
			//taskmap.put("TL" + i, rs.getString("Task_Limit"));
			//taskmap.put("SF" + i, rs.getString("Task_Flag"));
			//i++;
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

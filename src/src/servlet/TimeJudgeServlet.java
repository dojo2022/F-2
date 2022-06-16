package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class TimeJudgeServlet
 */
@WebServlet("/TimeJudgeServlet")
public class TimeJudgeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//HttpSession session = request.getSession();

		//Map<String,String> taskmap = new HashMap<>();
		//ArrayList<Integer> hoge = new ArrayList<>();
/*
	//定期的に処理を実行させる
	Timer timer = new Timer(false);
	TimerTask task = new TimerTask() {

		@Override
		public void run() {
*/
			Connection conn = null;

			try {

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

				Integer x = rs.getInt("Task_Id");
				String taskid = x.toString();
				String tasklimit = (rs.getTimestamp("Task_Limit")).toString();
				Integer y = rs.getInt("State_Flag");
				String stateflag = y.toString();

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
			// 結果を返す
			//request.setAttribute("path", audiopath);

/*		}
	};
	timer.schedule(task, 0, 1000);
*/
	}


}
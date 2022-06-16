package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = (Connection) DriverManager.getConnection("jdbc:h2:file:C:/database/imoketu", "sa", "");

				// SQL文を準備する<ここを改造>
				String tid = request.getParameter("tid");
				String tasksw = request.getParameter("tasksw");
				String sql = null;

				//taskswの値で分岐
				switch (tasksw) {
				case "1":
					sql = "update TASK set state_flag = 2 WHERE TASK_ID ="+tid;
				break;

				case "2":
					sql = "update TASK set state_flag = 1 WHERE TASK_ID ="+tid;
				break;

				case "3":
					sql = "delete from TASK  WHERE TASK_ID ="+tid;
				break;

				}
				PreparedStatement pStmt = ((java.sql.Connection) conn).prepareStatement(sql);
				// SQL文を実行し、結果表を取得する
				pStmt.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}



		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

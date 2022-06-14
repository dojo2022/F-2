package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tasks;
import model.User;

/**
 * Servlet implementation class TaskListServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/TaskListServlet" })
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// もしもログインしていなかったらログインサーブレットにリダイレクトする


		HttpSession session = request.getSession();

		if (session.getAttribute("id") == null) {
			response.sendRedirect("/imoketu/LoginServlet");
			return;
		}


		User user = (User)session.getAttribute("id");
		String loginId = user.getId();
		System.out.println(loginId);

		 Connection conn = null;
		 List<Tasks> taskList = new ArrayList<Tasks>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = (Connection) DriverManager.getConnection("jdbc:h2:file:C:/database/imoketu", "sa", "");

				// SQL文を準備する<ここを改造>
				String sql = "select * from Task WHERE USER_ID = ?";
				//String sql = "select * from Task ";

				PreparedStatement pStmt = ((java.sql.Connection) conn).prepareStatement(sql);
				pStmt.setString(1, loginId);


				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					Tasks tasks = new Tasks(
					rs.getInt("TASK_ID"),
					rs.getString("TASK_NAME"),
					rs.getString("TASK_LIMIT"),
					rs.getInt("STATE_FLAG"),
					rs.getString("USER_ID")
					);
					taskList.add(tasks);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				taskList = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				taskList = null;
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



		// 結果をリクエストスコープに格納する
		request.setAttribute("taskList", taskList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tasklist.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}

package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tasks;

/**
 * Servlet implementation class TaskAdd
 */
@WebServlet("/TaskAddServlet")
public class TaskAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @return
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/imoketu/LoginServlet");
			return;
		}

		//タスク追加ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/taskadd.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String taskName = request.getParameter("taskname");
		String taskLimit = request.getParameter("tasklimit");

		Connection conn = null;
		Tasks task = new Tasks(taskName, taskLimit, 0,(String) request.getSession().getAttribute("id"));

		try {

			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/database/imoketu", "sa", "");

			//SQL文を準備
			String sql = "INSERT INTO TASK (TASK_NAME, TASK_LIMIT, STATE_FLAG, USER_ID)"
					+ " VALUES (?, ?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			//登録する為に必要な条件と登録の仕方↓
			if (task.getTaskname() != null && !task.getTaskname().equals("")) {
				pStmt.setString(1, task.getTaskname());
			} else {
				pStmt.setString(1, null);
			}
			if (task.getTasklimit() != null) {
				pStmt.setString(2, task.getTasklimit());
			} else {
				pStmt.setString(2, null);
			}
			if (task.getStateflag() != null) {
				pStmt.setInt(3, task.getStateflag());
			} else {
				pStmt.setObject(3, 0);
			}
			if (task.getUserid() != null) {
				pStmt.setString(4, task.getUserid());
			} else {
				pStmt.setObject(4, "");
			}
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		/*登録したらタスク追加場面に戻る
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/taskadd.jsp");
		dispatcher.forward(request, response);*/
	}

}

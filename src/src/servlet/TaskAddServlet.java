package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

		/*HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/imoketu/LoginServlet");
			return;
		}*/
		// タスク追加モードにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/taskadd.jsp");
		dispatcher.forward(request, response);

		Connection conn = null;
		Tasks task = new Tasks();
		boolean result = false;
		try {
			List<Tasks> taskList = new ArrayList<Tasks>();

			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/database/imoketu", "sa", "");
			//SQL文を準備
			String sql = "INSERT INTO TASK (TASK_NAME, TASK_LIMIT, STATE_FLAG, USER_ID)"
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			if (task.getTaskname() != null && task.getTaskname().equals("")) {
				pStmt.setString(1, task.getTaskname());
			}
			else {
				pStmt.setString(1, null);
			}
			if (task.getTasklimit() != null && task.getTasklimit().equals("")) {
				pStmt.setString(2, task.getTasklimit());
				/*String df = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(task.getTasklimit());
				pStmt.setString(2, df);*/
			}
			else {
				pStmt.setString(2, null);
			}
			if (task.getStateflag() !=null) {
				pStmt.setInt(3, task.getStateflag());
			}
			else {
				pStmt.setInt(3, (Integer) null);
			}
			if (task.getUserid() != null) {
				pStmt.setString(4, task.getUserid());
			}
			else {
				pStmt.setString(4,null);
			}

			int linenum = pStmt.executeUpdate(sql);
			System.out.print("行"+ linenum);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
			return;
		}
		catch (SQLException e) {
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

		// 結果を返す
		return;

		// もしもログインしていなかったらログインサーブレットにリダイレクトする

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/imoketu/LoginServlet");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/taskadd.jsp");
		dispatcher.forward(request, response);
	}

}

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		/*HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/imoketu/LoginServlet");
			return;
		}*/

		//List<Task> taskList = bDao.list();

		/*
		 Connection conn = null;
			List<Task> taskList = new ArrayList<Task>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

				// SQL文を準備する<ここを改造>
				String sql = "select * from Task ORDER BY NUMBER";
				PreparedStatement pStmt = conn.prepareStatement(sql);


				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					Task task = new Task(
					rs.getString("NUMBER"),
					rs.getString("NAME"),
					rs.getString("ZIPCODE"),
					rs.getString("ADDRESS"),
					rs.getString("COMPANY"),
					rs.getString("DEPARTMENT"),
					rs.getString("TEL"),
					rs.getString("MAIL")
					);
					taskList.add(task);
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
					}
					catch (SQLException e) {
						e.printStackTrace();
						taskList = null;
					}
				}
			}

		  */

		// 結果をリクエストスコープに格納する
		//request.setAttribute("taskList", taskList);

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

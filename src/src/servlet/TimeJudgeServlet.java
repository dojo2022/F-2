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
import javax.servlet.http.HttpSession;


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
		HttpSession session = request.getSession();
		Connection conn = null;

	try {

		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/database/imoketu", "sa", "");

		// SQL文を準備する
		String sql = "SELECT Audio_Path FROM Audio WHERE Audio_Id=1";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		rs.next();
		//int audioid = rs.getInt("Audio_Id");
		String audiopath = rs.getString("Audio_Path");

		//String[] Audio = {Integer.toString(audioid), audiopath};

		request.setAttribute("path", audiopath);

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


	}


}
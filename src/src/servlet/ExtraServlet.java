package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExtraServlet
 */
@WebServlet("/ExtraServlet")
public class ExtraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/imoketu/LoginServlet");
			return;
		}
*/
		String audiopath = "";
		Connection conn = null;
		Map<String,String> audio = new HashMap<>();

	try {

		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/database/imoketu", "sa", "");

		// SQL文を準備する
		//String sql = "SELECT * FROM Audio WHERE Audio_Id in (1, 2, 3)";
		String sql = "SELECT * FROM Audio";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();

		//結果表をコレクションにコピーする
		//int audioid = rs.getInt("Audio_Id");

		int i = 1;

		while(rs.next()){
			Integer audi = rs.getInt("Audio_Id");
			audio.put("ID" + i, audi.toString());
			audio.put("VP" + i, rs.getString("Audio_Path"));
			i++;
		};

		//String[] Audio = {Integer.toString(audioid), audiopath};



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
		request.setAttribute("PathList", audio);


		// エクストラモードにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/extra.jsp");
		dispatcher.forward(request, response);
	}



}

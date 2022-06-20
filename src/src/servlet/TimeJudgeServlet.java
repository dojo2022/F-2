package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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

		//5種類のセリフ最終再生時間を格納する変数を宣言し古い時刻を代入
//		SimpleDateFormat LastPlaysdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//		Calendar cale = Calendar.getInstance();
//		cale.set(2018, 12, 12, 12, 00, 00);

		Date NextPlayTime1=new Date(118, 12, 12, 12, 00, 00);
		Date NextPlayTime2=new Date(118, 12, 12, 12, 00, 00);
		Date NextPlayTime3=new Date(118, 12, 12, 12, 00, 00);
		Date NextPlayTime4=new Date(118, 12, 12, 12, 00, 00);


		//Calendar型からDate型に変換し、格納
//		NextPlayTime1 = cale.getTime();
//		NextPlayTime2 = cale.getTime();
//		NextPlayTime3 = cale.getTime();
//		NextPlayTime4 = cale.getTime();





//		try {
//
//			NextPlayTime1 = LastPlaysdf.parse(str);
//			NextPlayTime2 = LastPlaysdf.parse(str);
//			NextPlayTime3 = LastPlaysdf.parse(str);
//			NextPlayTime4 = LastPlaysdf.parse(str);
//
//		} catch (ParseException e1) {
//			// TODO 自動生成された catch ブロック
//			e1.printStackTrace();
//		}

		//定期的に処理を実行させる
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {

		@Override
		public void run() {

		Date Npt1=NextPlayTime1;
		Date Npt2=NextPlayTime2;
		Date Npt3=NextPlayTime3;
		Date Npt4=NextPlayTime4;


		Connection conn = null;

        /*
		Timestamp LastPlayTime1 = new Timestamp(System.currentTimeMillis());
		Timestamp LastPlayTime2 = new Timestamp(System.currentTimeMillis());
		Timestamp LastPlayTime3 = new Timestamp(System.currentTimeMillis());
		Timestamp LastPlayTime4 = new Timestamp(System.currentTimeMillis());
		*/


		//可変長型配列を作成
        //ArrayList<String> alert = new ArrayList<>();
//        String[][] alert = {
//        		{"/imoketu/audio/006_未着手・未着手1時間前.wav","バカ兄貴ーはやしくしろー！！"},
//        		{"/imoketu/audio/005_未完了・未着手3時間前.wav","3時間前よ、はやくしなさいって！"},
//        		{"/imoketu/audio/004_未着手・未完了当日ver2.wav","今日締め切りのタスクがあるわよ、わかってるんでしょうね"},
//        		{"/imoketu/audio/003_未着手一日前ver2.wav","明日締め切りのタスクがあるわよ、大丈夫なの？"},
//        		{"/imoketu/audio/002_未着手二日前.wav","明後日締め切りのタスクがあるわよ、ちゃんと余裕を持ってやりなさいよ"}
//        };


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

				if(oneHourBeforeFlag==1  && (currenttimestamp.compareTo(Npt1))==1 ) { //1時間以内かつ3時間以内の再生が無いかどうか

					System.out.println("1時間前");

//					System.out.println("音声データ取得：" + alert[0][0]);
//					System.out.println("アラート文字取得:" + alert[0][1]);


					int role = Integer.parseInt(request.getParameter("role"));
					//Map<Integer,String> data = new HashMap<>();

					String audioPath = null;

					try {

						// データベースとの接続の確立
						Class.forName("org.h2.Driver");
						String url = "jdbc:h2:file:C:/database/imoketu";
						Connection con = DriverManager.getConnection(url, "sa", "");
						//SQL文のテンプレート作成
						String sql2 =
								"SELECT Audio_Path  " +
								"FROM AUDIO WHERE Audio_Id = 6";
						//SQLインジェクション対策
						PreparedStatement prepStmt = con.prepareStatement(sql);
						//SQL文"?"の箇所に値を埋める
						//prepStmt.setInt(1, role);
						//DBに対しQuery実行。rsに実行結果を蓄積。
						ResultSet rs2 = prepStmt.executeQuery();
						//SQLの実行結果の処理
						rs2.next();
						audioPath = rs.getString("AUDIO_PATH");

							/*
								String role_name="";
								String user = rs.getString("USERNAME");
								Integer id = rs.getInt("ID");
								role = rs.getInt("ROLE");
								if( role == 1) role_name = "管理者";
								else if( role == 2) role_name = "編集者";
								else role_name = "寄稿者";
								String email = rs.getString("EMAIL");
								String zdata = user + ":" + email + ":" + role_name;
								data.put(id,zdata);
								*/

						//DBのクローズ

						rs.close();
						prepStmt.close();
						con.close();
						} catch (ClassNotFoundException e) {
									e.printStackTrace();
						} catch (SQLException e) {
									e.printStackTrace();
						}

					//ネットストリームに書き込む

					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<div id=\"modal-overlay\">");
					out.println("<div class=\"modal-mask\"></div>");
					out.println("<div class=\"modal-container\">");
					out.println("<div class=\"modal-inner\">");
					out.println("<div class=\"modal-title\">バカ兄貴ーはやしくしろー！！</div>");
					out.println("<div class=\"modal-text\">");
					out.println("<p>タスクの期限が１時間前です。</p>");
					out.println("</div>");
					out.println("<button class=\"close\">×</button>");
					out.println("</div></div></div>");
					out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
					out.println("<script src=\"/imoketu/js/common.js\"></script>");
					out.println("<script>");
					out.println("const music = new Audio(" + audioPath + ");");
					out.println("music.play();");
					out.println("</script>");



//					response.setContentType("text/html; charset=UTF-8");
//					PrintWriter out = response.getWriter();
//					out.println("<div id='result_box'>");
//					out.println("<h2>検索結果</h2>");
//
//					for(Map.Entry<Integer,String> entry:data.entrySet()) {
//						out.print(entry.getKey()+":"+entry.getValue()+"<br>");
//					}
//					out.println("</div>");


					//3時間後のタイムスタンプを格納
					Calendar calLP1 = Calendar.getInstance();
					calLP1.setTime(dt);
					calLP1.add(Calendar.HOUR, +3);
					Npt1 = new Date((calLP1.getTime()).getTime());
					System.out.println("LP1:"+Npt1 );

				}else if(threeHourBeforeFlag==1  && (currenttimestamp.compareTo(Npt2))==1 ) {  //3時間以内かつ3時間以内の再生が無いかどうか

					System.out.println("3時間前");
//					System.out.println("音声データ取得：" + alert[1][0]);
//					System.out.println("アラート文字取得:" + alert[1][1]);


					int role = Integer.parseInt(request.getParameter("role"));
					//Map<Integer,String> data = new HashMap<>();

					String audioPath = null;

					try {

						// データベースとの接続の確立
						Class.forName("org.h2.Driver");
						String url = "jdbc:h2:file:C:/database/imoketu";
						Connection con = DriverManager.getConnection(url, "sa", "");
						//SQL文のテンプレート作成
						String sql2 =
								"SELECT Audio_Path  " +
								"FROM AUDIO WHERE Audio_Id = 5";
						//SQLインジェクション対策
						PreparedStatement prepStmt = con.prepareStatement(sql);
						//SQL文"?"の箇所に値を埋める
						//prepStmt.setInt(1, role);
						//DBに対しQuery実行。rsに実行結果を蓄積。
						ResultSet rs2 = prepStmt.executeQuery();
						//SQLの実行結果の処理
						rs2.next();
						audioPath = rs.getString("AUDIO_PATH");

							/*
								String role_name="";
								String user = rs.getString("USERNAME");
								Integer id = rs.getInt("ID");
								role = rs.getInt("ROLE");
								if( role == 1) role_name = "管理者";
								else if( role == 2) role_name = "編集者";
								else role_name = "寄稿者";
								String email = rs.getString("EMAIL");
								String zdata = user + ":" + email + ":" + role_name;
								data.put(id,zdata);
								*/

						//DBのクローズ

						rs.close();
						prepStmt.close();
						con.close();
						} catch (ClassNotFoundException e) {
									e.printStackTrace();
						} catch (SQLException e) {
									e.printStackTrace();
						}

					//ネットストリームに書き込む

					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<div id=\"modal-overlay\">");
					out.println("<div class=\"modal-mask\"></div>");
					out.println("<div class=\"modal-container\">");
					out.println("<div class=\"modal-inner\">");
					out.println("<div class=\"modal-title\">3時間前よ、はやくしなさいって！</div>");
					out.println("<div class=\"modal-text\">");
					out.println("<p>タスクの期限が3時間前です。</p>");
					out.println("</div>");
					out.println("<button class=\"close\">×</button>");
					out.println("</div></div></div>");
					out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
					out.println("<script src=\"/imoketu/js/common.js\"></script>");
					out.println("<script>");
					out.println("const music = new Audio(" + audioPath + ");");
					out.println("music.play();");
					out.println("</script>");



					//3時間後のタイムスタンプを格納
					Calendar calLP2 = Calendar.getInstance();
					calLP2.setTime(dt);
					calLP2.add(Calendar.HOUR, +3);
					Npt2 = new Date((calLP2.getTime()).getTime());
					System.out.println("LP2:"+Npt2);
				}else if(oneDayBeforeFlag==1 && (currenttimestamp.compareTo(Npt3))==1 ) { //1日以内かつであり、3時間以内の再生が無いかどうか

					System.out.println("1日前");
//					System.out.println("音声データ取得：" + alert[2][0]);
//					System.out.println("アラート文字取得:" + alert[2][1]);


					int role = Integer.parseInt(request.getParameter("role"));
					//Map<Integer,String> data = new HashMap<>();

					String audioPath = null;

					try {

						// データベースとの接続の確立
						Class.forName("org.h2.Driver");
						String url = "jdbc:h2:file:C:/database/imoketu";
						Connection con = DriverManager.getConnection(url, "sa", "");
						//SQL文のテンプレート作成
						String sql2 =
								"SELECT Audio_Path  " +
								"FROM AUDIO WHERE Audio_Id = 3";
						//SQLインジェクション対策
						PreparedStatement prepStmt = con.prepareStatement(sql);
						//SQL文"?"の箇所に値を埋める
						//prepStmt.setInt(1, role);
						//DBに対しQuery実行。rsに実行結果を蓄積。
						ResultSet rs2 = prepStmt.executeQuery();
						//SQLの実行結果の処理
						rs2.next();
						audioPath = rs.getString("AUDIO_PATH");

							/*
								String role_name="";
								String user = rs.getString("USERNAME");
								Integer id = rs.getInt("ID");
								role = rs.getInt("ROLE");
								if( role == 1) role_name = "管理者";
								else if( role == 2) role_name = "編集者";
								else role_name = "寄稿者";
								String email = rs.getString("EMAIL");
								String zdata = user + ":" + email + ":" + role_name;
								data.put(id,zdata);
								*/

						//DBのクローズ

						rs.close();
						prepStmt.close();
						con.close();
						} catch (ClassNotFoundException e) {
									e.printStackTrace();
						} catch (SQLException e) {
									e.printStackTrace();
						}

					//ネットストリームに書き込む

					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<div id=\"modal-overlay\">");
					out.println("<div class=\"modal-mask\"></div>");
					out.println("<div class=\"modal-container\">");
					out.println("<div class=\"modal-inner\">");
					out.println("<div class=\"modal-title\">明日締め切りのタスクがあるわよ、大丈夫なの？</div>");
					out.println("<div class=\"modal-text\">");
					out.println("<p>タスクの期限が1日前です。</p>");
					out.println("</div>");
					out.println("<button class=\"close\">×</button>");
					out.println("</div></div></div>");
					out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
					out.println("<script src=\"/imoketu/js/common.js\"></script>");
					out.println("<script>");
					out.println("const music = new Audio(" + audioPath + ");");
					out.println("music.play();");
					out.println("</script>");


					//3時間後のタイムスタンプを格納
					Calendar calLP3 = Calendar.getInstance();
					calLP3.setTime(dt);
					calLP3.add(Calendar.HOUR, +3);
					Npt3 = new Date((calLP3.getTime()).getTime());
					System.out.println("LP3:"+Npt3);
				}else if(twoDayBeforeFlag==1 && (currenttimestamp.compareTo(Npt4))==1 ) { //2日以内かつであり、3時間以内の再生が無いかどうか


					System.out.println("2日前");
//					System.out.println("音声データ取得：" + alert[3][0]);
//					System.out.println("アラート文字取得:" + alert[3][1]);


					int role = Integer.parseInt(request.getParameter("role"));
					//Map<Integer,String> data = new HashMap<>();

					String audioPath = null;

					try {

						// データベースとの接続の確立
						Class.forName("org.h2.Driver");
						String url = "jdbc:h2:file:C:/database/imoketu";
						Connection con = DriverManager.getConnection(url, "sa", "");
						//SQL文のテンプレート作成
						String sql2 =
								"SELECT Audio_Path  " +
								"FROM AUDIO WHERE Audio_Id = 2";
						//SQLインジェクション対策
						PreparedStatement prepStmt = con.prepareStatement(sql);
						//SQL文"?"の箇所に値を埋める
						//prepStmt.setInt(1, role);
						//DBに対しQuery実行。rsに実行結果を蓄積。
						ResultSet rs2 = prepStmt.executeQuery();
						//SQLの実行結果の処理
						rs2.next();
						audioPath = rs.getString("AUDIO_PATH");

							/*
								String role_name="";
								String user = rs.getString("USERNAME");
								Integer id = rs.getInt("ID");
								role = rs.getInt("ROLE");
								if( role == 1) role_name = "管理者";
								else if( role == 2) role_name = "編集者";
								else role_name = "寄稿者";
								String email = rs.getString("EMAIL");
								String zdata = user + ":" + email + ":" + role_name;
								data.put(id,zdata);
								*/

						//DBのクローズ

						rs.close();
						prepStmt.close();
						con.close();
						} catch (ClassNotFoundException e) {
									e.printStackTrace();
						} catch (SQLException e) {
									e.printStackTrace();
						}

					//ネットストリームに書き込む

					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<div id=\"modal-overlay\">");
					out.println("<div class=\"modal-mask\"></div>");
					out.println("<div class=\"modal-container\">");
					out.println("<div class=\"modal-inner\">");
					out.println("<div class=\"modal-title\">明後日締め切りのタスクがあるわよ、ちゃんと余裕を持ってやりなさいよ</div>");
					out.println("<div class=\"modal-text\">");
					out.println("<p>タスクの期限が2日前です。</p>");
					out.println("</div>");
					out.println("<button class=\"close\">×</button>");
					out.println("</div></div></div>");
					out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
					out.println("<script src=\"/imoketu/js/common.js\"></script>");
					out.println("<script>");
					out.println("const music = new Audio(" + audioPath + ");");
					out.println("music.play();");
					out.println("</script>");


					//3時間後のタイムスタンプを格納
					Calendar calLP4 = Calendar.getInstance();
					calLP4.setTime(dt);
					calLP4.add(Calendar.HOUR, +3);
					Npt4 = new Date((calLP4.getTime()).getTime());
					System.out.println("LP4:"+Npt4);
				}
			}else {
				Calendar caldelete = Calendar.getInstance();
				caldelete.setTime(dt2);

				caldelete.add(Calendar.MINUTE, +10);
				System.out.println("2日前：" + sdf.format(caldelete.getTime()));
				Date timeOverRange = new Date((caldelete.getTime()).getTime());

				if((currenttimestamp.compareTo(timeOverRange))!=1) {
					System.out.println("超過音声再生期間");


					int role = Integer.parseInt(request.getParameter("role"));
					//Map<Integer,String> data = new HashMap<>();

					String audioPath = null;

					try {

						// データベースとの接続の確立
						Class.forName("org.h2.Driver");
						String url = "jdbc:h2:file:C:/database/imoketu";
						Connection con = DriverManager.getConnection(url, "sa", "");
						//SQL文のテンプレート作成
						String sql2 =
								"SELECT Audio_Path  " +
								"FROM AUDIO WHERE Audio_Id = 12";
						//SQLインジェクション対策
						PreparedStatement prepStmt = con.prepareStatement(sql);
						//SQL文"?"の箇所に値を埋める
						//prepStmt.setInt(1, role);
						//DBに対しQuery実行。rsに実行結果を蓄積。
						ResultSet rs2 = prepStmt.executeQuery();
						//SQLの実行結果の処理
						rs2.next();
						audioPath = rs.getString("AUDIO_PATH");

							/*
								String role_name="";
								String user = rs.getString("USERNAME");
								Integer id = rs.getInt("ID");
								role = rs.getInt("ROLE");
								if( role == 1) role_name = "管理者";
								else if( role == 2) role_name = "編集者";
								else role_name = "寄稿者";
								String email = rs.getString("EMAIL");
								String zdata = user + ":" + email + ":" + role_name;
								data.put(id,zdata);
								*/

						//DBのクローズ

						rs.close();
						prepStmt.close();
						con.close();
						} catch (ClassNotFoundException e) {
									e.printStackTrace();
						} catch (SQLException e) {
									e.printStackTrace();
						}

					//ネットストリームに書き込む

					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<div id=\"modal-overlay\">");
					out.println("<div class=\"modal-mask\"></div>");
					out.println("<div class=\"modal-container\">");
					out.println("<div class=\"modal-inner\">");
					out.println("<div class=\"modal-title\">はぁ...あきれた。何度いったらわかるの？</div>");
					out.println("<div class=\"modal-text\">");
					out.println("<p>タスクの期限が超過しています。</p>");
					out.println("</div>");
					out.println("<button class=\"close\">×</button>");
					out.println("</div></div></div>");
					out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
					out.println("<script src=\"/imoketu/js/common.js\"></script>");
					out.println("<script>");
					out.println("const music = new Audio(" + audioPath + ");");
					out.println("music.play();");
					out.println("</script>");


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
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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

		}
	};
	timer.schedule(task, 0, 5000);

	}


}
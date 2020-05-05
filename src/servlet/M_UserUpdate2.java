package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class M_UserUpdate2
 */
public class M_UserUpdate2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public M_UserUpdate2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = 0;
		if (CommonFunc.isNumber(request.getParameter("userId"))==false) {
			//画面にパラメータセット
			request.setAttribute("errorMessage", "Id is wrong.(DataType Error)");
			request.setAttribute("InsUpdKbn", "Update");
			//ページ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("ErrorPage.jsp");
			dispatch.forward(request, response);
			return;
		}
		if (!request.getParameter("userId").equals("")) {
			userId = Integer.valueOf(request.getParameter("userId"));
		}
		//パラメータ受け取り
		String userName = "";
		String password = "";
		String mailAddress = "";
		int point = 0;
		//IDチェック
		if (QueryGet.selectUserNameById(userId).equals("")){
			//画面にパラメータセット
			request.setAttribute("userId", String.valueOf(userId));
			request.setAttribute("errorMessage", "ID not found.");
			request.setAttribute("InsUpdKbn", "Update");
			//ページ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("ErrorPage.jsp");
			dispatch.forward(request, response);
			return;
		}
		//ユーザー情報を取得し画面にセット
		selectUserInfo(userId, userName, password, mailAddress, point, request, response);
	}

	/*
	 * ユーザー情報を取得
	 */
	private void selectUserInfo(int userId, String userName, String password, String mailAddress, int point, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select UserName, Password, MailAddress, Point from M_User where Id = ?;";

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			ps.setInt(1, userId);
			//SELECTを実行する
			rs = ps.executeQuery();

			while (rs.next()) {
				userName = rs.getString("UserName");
				password = rs.getString("Password");
				mailAddress = rs.getString("MailAddress");
				point = rs.getInt("Point");
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
		}
		//画面のコントロールに値をセット
		request.setAttribute("userId", String.valueOf(userId));
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("mailAddress", mailAddress);
		request.setAttribute("point", String.valueOf(point));

		// ページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("M_UserUpdate2.jsp");
		dispatch.forward(request, response);
	}
}

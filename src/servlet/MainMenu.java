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
 * Servlet implementation class MainMenu
 */
public class MainMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//戻るフラグ取得
		String backFlg = request.getParameter("backFlg");

		//戻るフラグが1以外の場合、ログインチェックを実施
		if (backFlg==null){
			//ログインチェック
			int errorNum = checkLoginInfo(request.getParameter("Id"), request.getParameter("Password"));
			if (errorNum != 0) {
				//画面のコントロールに値をセット
				request.setAttribute("errorNum", String.valueOf(errorNum));
				// LoginCheck.jsp にページ遷移
				RequestDispatcher dispatch = request.getRequestDispatcher("LoginCheck.jsp");
				dispatch.forward(request, response);
				return;
			}
		}

		int userId = Integer.valueOf(request.getParameter("Id"));
		selectUserData(request, userId);
		// MainMenu にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("MainMenu.jsp");
		dispatch.forward(request, response);
	}

	private int checkLoginInfo(String strId, String strPassword) {
		int errorNum = 0;
		int id = 0;

LABELCheck:{

		//ID型チェック
		if (CommonFunc.isNumber(strId)==false) {
			errorNum = 1;
			break LABELCheck;
		}
		//リクエストパラメータ取得
		id = Integer.valueOf(strId);

		//IDチェック
		if (QueryGet.selectUserNameById(id).equals("")) {
			//
			errorNum = 2;
			break LABELCheck;
		}
		//パスワードチェック
		if (QueryGet.checkPassword(id, strPassword)==false) {
			errorNum = 3;
			break LABELCheck;
		}
}
		return errorNum;
	}
	/*
	 * ユーザーデータを出力するメソッド
	 */
	private void selectUserData(HttpServletRequest request, int inputId) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select UserName, Point, Kengen from M_User where Id = ?;";

		String UserName = "";
		String kengen = "";
		int remainPoint = 0;

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			ps.setInt(1, inputId);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				UserName = rs.getString("UserName");
				remainPoint = rs.getInt("Point");
				kengen = rs.getString("Kengen");
			}

			//画面のコントロールに値をセット
			request.setAttribute("UserName", UserName);
			request.setAttribute("Point", String.valueOf(remainPoint));
			request.setAttribute("Id", String.valueOf(inputId));
			request.setAttribute("kengen", kengen);

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}

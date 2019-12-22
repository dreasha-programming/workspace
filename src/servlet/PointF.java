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

/**
 * Servlet implementation class Point
 */
public class PointF extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String strId = request.getParameter("Id");
		int userId = Integer.valueOf(request.getParameter("Id"));
		selectUserData(request, userId);
		// Point にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("Point.jsp");
		dispatch.forward(request, response);
	}

	/*
	 * ユーザーデータを出力するメソッド
	 */
	private void selectUserData(HttpServletRequest request, int inputId) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select UserName, Point from M_User where Id = ?;";

		String UserName = "";
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
			}

			//画面のコントロールに値をセット
			request.setAttribute("UserName", UserName);
			request.setAttribute("Point", String.valueOf(remainPoint));
			request.setAttribute("Id", String.valueOf(inputId));


		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}

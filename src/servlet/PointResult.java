package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class PointResult
 */
public class PointResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointResult() {
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
		String retVal = (String) request.getParameter("txtPoint");
		if (retVal==null) {
			retVal = request.getParameter("txtPoint");
		}
		String id = (String) request.getParameter("Id");
		String userName = (String) request.getParameter("UserName");
		String remainPoint = (String) request.getParameter("remainPoint");
		String toUser = (String) request.getParameter("txtTo");

		int calcPoint = Integer.parseInt(remainPoint) - Integer.parseInt(retVal);
		int toUserId = QueryGet.selectUserDataByName(toUser);

		updatePointData(Integer.parseInt(id), calcPoint);
		insertPointHistory(Integer.parseInt(id), Integer.parseInt(retVal), toUserId);

		request.setAttribute("UserName", userName);
		request.setAttribute("calcPoint", String.valueOf(calcPoint));
		request.setAttribute("Id", id);

		// PointResult にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("PointResult.jsp");
		dispatch.forward(request, response);

		//int userId = Integer.valueOf(request.getParameter("UserName"));
	}

	/*
	 * Point情報をユーザーマスターに登録
	 */
	private void updatePointData(int Id, int Point) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";

		String sqlUpd = "update M_User set Point = ? where Id = ?;";

		//変数定義
		PreparedStatement ps = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlUpd);
			ps.setInt(1, Point);
			ps.setInt(2, Id);
			//UPDATEを実行する
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	/*
	 * Point情報をユーザーマスターに登録
	 */
	private void insertPointHistory(int Id, int Point, int toUser) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";

		String sqlIns = "insert into T_PointHistory(FromUserId, ToUserId, Point, Comment, UpdateDate, UketoriFlg)";
		sqlIns = sqlIns + "values(?, ?, ?, ?, SYSDATE(), ?)";

		//変数定義
		PreparedStatement ps = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlIns);
			ps.setInt(1, Id);
			ps.setInt(2, toUser);
			ps.setInt(3, Point);
			ps.setString(4, "");
			ps.setInt(5, 0);

			//UPDATEを実行する
			ps.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}

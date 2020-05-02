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
import javax.swing.JOptionPane;

import sql.CommonFunc;
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
		//パラメータ受け取り（共通）
		String id = (String) request.getParameter("Id");	//ログインID
		String remainPoint = (String) request.getParameter("remainPoint");	//残ポイント

		String UketoriFlg = (String) request.getParameter("UketoriFlg");			//受け取りフラグ
		String txtPutPoint = "";	//付与ポイント
		String txtPurpose = "";	//目的
		String toUser = "";	//ポイント付与先
		String retVal = "";
		int toUserId = 0;
		int calcPoint = 0;
		int errorFlg = 0;

		if (UketoriFlg.equals("0")) {
			//パラメータ受け取り（Put)
			txtPutPoint = (String) request.getParameter("txtPutPoint");	//付与ポイント
			txtPurpose = (String) request.getParameter("txtPurpose");	//目的
			toUser = (String) request.getParameter("txtTo");;	//ポイント付与先
		} else {
			//パラメータ受け取り（Get)
			//何ポイント減るか
			retVal = (String) request.getParameter("txtPoint");
			if (retVal==null) {
				retVal = request.getParameter("txtPoint");
			}
		}

		//チェック処理
		if (UketoriFlg.equals("0")) {
LABELerror:{
			//PointがIntじゃない
			if (CommonFunc.isNumber(txtPutPoint)==false) {
				JOptionPane.showMessageDialog(null, "point is Integer");
				errorFlg = 1;
				break LABELerror;
			}
			//ユーザーがいない
			if (QueryGet.selectUserIdByName(toUser)==0) {
				JOptionPane.showMessageDialog(null, "There is no user by that name:" + toUser);
				errorFlg = 1;
				break LABELerror;
			}
			//Pointがオーバーしている
			if (Integer.parseInt(remainPoint) - Integer.parseInt(txtPutPoint) < 0) {
				JOptionPane.showMessageDialog(null, "Over point error.");
				errorFlg = 1;
				break LABELerror;
			}
}
			if (errorFlg == 1) {
				request.setAttribute("UserName", QueryGet.selectUserNameById(Integer.parseInt(id)));
				request.setAttribute("remainPoint", String.valueOf(remainPoint));
				request.setAttribute("Id", id);

				// PointPutPage.jsp にページ遷移
				RequestDispatcher dispatchBack = request.getRequestDispatcher("PointPutPage.jsp");
				dispatchBack.forward(request, response);
				return;
			}
		}

		//ポイント履歴マスタ更新
		if (UketoriFlg.equals("0")) {
			//Putの場合
			//ToUserId取得
			toUserId = QueryGet.selectUserIdByName(toUser);
			//計算値（残ポイントー付与ポイント）
			calcPoint = Integer.parseInt(remainPoint) - Integer.parseInt(txtPutPoint);
			//T_PointHistory登録
			insert_T_PointHistory(Integer.parseInt(id), Integer.parseInt(txtPutPoint), toUserId, txtPurpose);
			//M_User更新
			update_M_User(Integer.parseInt(id), calcPoint);
		} else {
			//Getの場合
			//計算値（残ポイントー得たポイント）
			calcPoint = Integer.parseInt(remainPoint) + Integer.parseInt(retVal);
			//T_PointHistory更新
			update_T_PointHistory(Integer.parseInt(id));
			//M_User更新
			update_M_User(Integer.parseInt(id), calcPoint);
		}

		request.setAttribute("UserName", toUser);
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
	private void update_M_User(int Id, int Point) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";

		String sqlUpd = "update M_User set Point = ?, UpdateDate = SYSDATE() where Id = ?;";

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
	private void insert_T_PointHistory(int Id, int Point, int toUser, String comment) {
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
			ps.setInt(1, Id);			//FromUser
			ps.setInt(2, toUser);		//ToUser
			ps.setInt(3, Point);		//PutPoint
			ps.setString(4, comment);	//Comment
			ps.setInt(5, 0);			//UketoriFlg

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
	 * Point履歴情報更新
	 */
	private void update_T_PointHistory(int Id) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";

		String sqlUpd = "update T_PointHistory set UketoriFlg = 1, UpdateDate = SYSDATE() where ToUserId = ? and UketoriFlg = 0;";

		//変数定義
		PreparedStatement ps = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlUpd);
			ps.setInt(1, Id);
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

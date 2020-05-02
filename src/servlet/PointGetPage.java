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
 * Servlet implementation class PointGetPage
 */
public class PointGetPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointGetPage() {
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
		// doGet(request, response);
		int userId = Integer.valueOf(request.getParameter("Id"));
		selectPointGetData(request, userId);
		// PointGetPage.jsp にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("PointGetPage.jsp");
		dispatch.forward(request, response);
	}

	/*
	 * ユーザーデータを出力するメソッド
	 */
	private void selectPointGetData(HttpServletRequest request, int inputId) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String 	sqlGet = "select MAX(b.UserName) as UserName, SUM(a.Point) as GetPoint, MAX(b.Point) as RemainPoint " ;
				sqlGet = sqlGet + "from T_PointHistory a inner join M_User b on a.ToUserId = b.Id ";
				sqlGet = sqlGet + "where a.ToUserId = ? and a.UketoriFlg = 0;";
		String UserName = "";
		int getPoint = 0;
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
				getPoint = rs.getInt("GetPoint");
				remainPoint = rs.getInt("RemainPoint");
			}

			//画面のコントロールに値をセット
			request.setAttribute("UserName", UserName);
			request.setAttribute("GetPoint", String.valueOf(getPoint));
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

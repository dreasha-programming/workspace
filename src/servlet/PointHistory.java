package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class PointHistory
 */
public class PointHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//パラメータ取得
		int userId = Integer.parseInt((String) request.getParameter("Id"));
		//T_PointHistory取得
		select_T_PointHistory(userId, request, response);
	}

	/*
	 * T_PointHistory取得
	 */
	private void select_T_PointHistory(int userId, HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	String drv = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
	String id = "root";
	String pass = CommonFunc.getDBPassword();
	String sqlGet = "select FromUserId,ToUserId,Point,Comment,UketoriFlg,UpdateDate from T_PointHistory where FromUserId=? or ToUserId=?;";

	//変数定義
	PreparedStatement ps = null;
	ResultSet rs = null;
	ArrayList<String> fromUserName = new ArrayList<String>();
	ArrayList<String> toUserName = new ArrayList<String>();
	ArrayList<String> comment = new ArrayList<String>();
	ArrayList<String> point = new ArrayList<String>();
	ArrayList<String> uketoriFlg = new ArrayList<String>();
	ArrayList<String> updateDate = new ArrayList<String>();
	int maxArraySize = 0;

	try {
		Class.forName(drv);
		Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
		//実行するSQL文とパラメータを指定する
		ps = con.prepareStatement(sqlGet);
		ps.setInt(1, userId);
		ps.setInt(2, userId);

		//SELECTを実行する
		rs = ps.executeQuery();

		while (rs.next()) {
			fromUserName.add(String.valueOf(QueryGet.selectUserNameById(rs.getInt("FromUserId"))));
			toUserName.add(String.valueOf(QueryGet.selectUserNameById(rs.getInt("ToUserId"))));
			comment.add(rs.getString("Comment"));
			point.add(String.valueOf(rs.getInt("Point")));
			if (rs.getInt("UketoriFlg")==0) {
				uketoriFlg.add(String.valueOf("Not yet."));
			} else {
				uketoriFlg.add(String.valueOf("Done."));
			}
			updateDate.add(String.valueOf(rs.getDate("updateDate")));
		}
	} catch (ClassNotFoundException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}  finally {
	}
	//配列変換
	String[] arrFromUserName = fromUserName.toArray(new String[fromUserName.size()]);
	String[] arrToUserName = toUserName.toArray(new String[toUserName.size()]);
	String[] arrComment = comment.toArray(new String[comment.size()]);
	String[] arrPoint= point.toArray(new String[point.size()]);
	String[] arrUketoriFlg = uketoriFlg.toArray(new String[uketoriFlg.size()]);
	String[] arrUpdateDate = updateDate.toArray(new String[updateDate.size()]);
	maxArraySize = fromUserName.size();
	for(int i=0;i<maxArraySize;i++) {
		if(arrComment[i]==null) {
			arrComment[i]="";
		}
		if(arrUpdateDate[i]==null) {
			arrUpdateDate[i]="";
		}
	}

	//画面のコントロールに値をセット
	request.setAttribute("arrFromUserName", arrFromUserName);
	request.setAttribute("arrToUserName", arrToUserName);
	request.setAttribute("arrComment", arrComment);
	request.setAttribute("arrPoint", arrPoint);
	request.setAttribute("arrUketoriFlg", arrUketoriFlg);
	request.setAttribute("arrUpdateDate", arrUpdateDate);
	request.setAttribute("maxArraySize", String.valueOf(maxArraySize));
	request.setAttribute("Id", String.valueOf(userId));

	// ページ遷移
	RequestDispatcher dispatch = request.getRequestDispatcher("PointHistory.jsp");
	dispatch.forward(request, response);
	CommonFunc.insertAccessLog(Integer.valueOf(userId), "PointHistory.jsp");
	}
}

package servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class PointHistoryCSV
 */
public class PointHistoryCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointHistoryCSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//パラメータ取得
		int Id = Integer.valueOf(request.getParameter("Id"));
		//データ取得＆CSV出力
		select_T_PointHistory(Id, request, response);
	}

	/*
	 * T_PointHistory取得
	 */
	private void select_T_PointHistory(int userId, HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	String drv = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
	String id = "root";
	String pass = "";
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

	//CSV出力
	outputCSV(arrFromUserName, arrToUserName, arrComment, arrPoint, arrUketoriFlg, arrUpdateDate);

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
	}

	/*
	 * CSV出力
	 */
	private void outputCSV(String[] arrFromUserName, String[] arrToUserName, String[] arrComment,
			String[] arrPoint, String[] arrUketoriFlg, String[] arrUpdateDate) {
        try {
        	String home = System.getProperty("user.home");
        	File file = new File(home + File.separator + "Test" + File.separator + "PointHistory.csv");

            // 出力ファイルの作成
            FileWriter f = new FileWriter(file.getAbsolutePath(), false);
            PrintWriter p = new PrintWriter(new BufferedWriter(f));

            // ヘッダーを指定する
            p.print("From");
            p.print(",");
            p.print("To");
            p.print(",");
            p.print("Comment");
            p.print(",");
            p.print("Point");
            p.print(",");
            p.print("GetStatus");
            p.print(",");
            p.print("UpdateDate");
            p.println();

            // 内容をセットする
            for(int i = 0; i < arrFromUserName.length; i++){
                p.print(arrFromUserName[i]);
                p.print(",");
                p.print(arrToUserName[i]);
                p.print(",");
                p.print(arrComment[i]);
                p.print(",");
                p.print(arrPoint[i]);
                p.print(",");
                p.print(arrUketoriFlg[i]);
                p.print(",");
                p.print(arrUpdateDate[i]);
                p.println();    // 改行
            }

            // ファイルに書き出し閉じる
            p.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}

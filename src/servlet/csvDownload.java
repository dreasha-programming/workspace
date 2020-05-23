package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class csvDownload
 */
public class csvDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public csvDownload() {
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
	   	String drv = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
    	String id = "root";
    	String pass = CommonFunc.getDBPassword();
    	String sqlGet = "select FromUserId,ToUserId,Point,Comment,UketoriFlg,UpdateDate from T_PointHistory where FromUserId=? or ToUserId=?;";

		//パラメータ取得
		int userId = Integer.valueOf(request.getParameter("Id"));

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

        //文字コードと出力するCSVファイル名を設定
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");

        //try-with-resources文を使うことでclose処理を自動化
        try (PrintWriter pw = response.getWriter()) {
            //List<User> all_users = service.findAll();
            for (int i = 0; i < arrFromUserName.length; i++) {
                String strFromUserName = arrFromUserName[i];
                String strToUserName = arrToUserName[i];
                String strComment = arrComment[i];
                String strPoint = arrPoint[i];
                String strUketoriFlg = arrUketoriFlg[i];
                String strUpdateDate = arrUpdateDate[i];

                //CSVファイル内部に記載する形式で文字列を設定
                String outputString = strFromUserName + "," + strToUserName + "," + strComment + "," + strPoint + "," + strUketoriFlg + "," + strUpdateDate
                         + "\r\n";

                //CSVファイルに書き込み
                pw.print(outputString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}

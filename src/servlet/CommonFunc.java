package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommonFunc {

	/*
	 * 数値型かどうかチェック
	 */
	static public boolean isNumber(String num) {
	    try {
	        Integer.parseInt(num);
	        return true;
	        } catch (NumberFormatException e) {
	        return false;
	    }
	}
	/*
	 *  エラーチェック
	 */
	static public String errorCheck(String userId, String userName, String password, String mailAddress, String point) {
		//型チェック（数値型）
		if (isNumber(userId)==false) {
			return "Id is wrong.(DataType Error)";
		}
		if (isNumber(point)==false) {
			return "Point is wrong.(DataType Error)";
		}
		//サイズチェック
		if (userName.length()>100) {
			return "UserName is wrong.(DataSize Error)";
		}
		if (password.length()>50) {
			return "Password is wrong.(DataSize Error)";
		}
		if (password.length()>255) {
			return "MailAddress is wrong.(DataSize Error)";
		}
		//空チェック
		if (userName.length()==0) {
			return "UserName is empty.";
		}
		if (password.length()==0) {
			return "Password is empty.";
		}
		if (password.length()==0) {
			return "MailAddress is empty.";
		}

		return "";
	}

	/*
	 * DBパスワード
	 */
	static public String getDBPassword() {
		return "root1234";
	}

	/*
	 * アクセスログ登録
	 */
	static public void insertAccessLog(int userId, String jspPage) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = CommonFunc.getDBPassword();

		String sqlIns = "insert into T_AccessLog(Id, JspPage, AccessDate)";
		sqlIns = sqlIns + "values(?, ?, SYSDATE())";

		//変数定義
		PreparedStatement ps = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlIns);
			ps.setInt(1, userId);
			ps.setString(2, jspPage);

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

package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryGet {

	/*
	 * ユーザーデータを出力するメソッド
	 */
	@SuppressWarnings("finally")
	static public int selectUserDataByName(String userName) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select Id from M_User where UserName = ?;";

		int userId = 0;

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			ps.setString(1, userName);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getInt("Id");
			}


		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
			return userId;
		}

	}
}

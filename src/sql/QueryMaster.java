package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryMaster {

	/*
	 * ユーザーマスターに登録
	 */
	static public void insert_M_User(int Id, String userName, String password, String mailAddress, int point, String kengen) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";

		String sqlIns = "insert into M_User(Id, UserName, Password, MailAddress, Point, Kengen, UpdateDate)";
		sqlIns = sqlIns + "values(?, ?, ?, ?, ?, ?, SYSDATE())";

		//変数定義
		PreparedStatement ps = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlIns);
			ps.setInt(1, Id);
			ps.setString(2, userName);
			ps.setString(3, password);
			ps.setString(4, mailAddress);
			ps.setInt(5, point);
			ps.setString(6, kengen);

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
	 * ユーザーマスターに登録
	 */
	static public void update_M_User(int Id, String userName, String password, String mailAddress, int point, String kengen) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";

		String sqlIns = "update M_User set UserName=?, Password=?, MailAddress=?, Point=?, Kengen=?, UpdateDate=SYSDATE() ";
		sqlIns = sqlIns + "where Id=?;";

		//変数定義
		PreparedStatement ps = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlIns);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, mailAddress);
			ps.setInt(4, point);
			ps.setString(5, kengen);
			ps.setInt(6, Id);

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
	 * ユーザーマスターから削除
	 */
	static public void delete_M_User(int Id) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";

		String strSql = "delete from M_User where Id=? ";

		//変数定義
		PreparedStatement ps = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(strSql);
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

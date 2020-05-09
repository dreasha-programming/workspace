package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryGet {

	/*
	 * ユーザーIdをユーザー名から取得
	 */
	@SuppressWarnings("finally")
	static public int selectUserIdByName(String userName) {
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

	/*
	 * ユーザーIdからユーザー名を取得
	 */
	@SuppressWarnings("finally")
	static public String selectUserNameById(int userId) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select UserName from M_User where id = ?;";

		String userName = "";

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			ps.setInt(1, userId);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				userName = rs.getString("UserName");
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
			return userName;
		}
	}

	/*
	 * ユーザーIdからポイントを取得
	 */
	@SuppressWarnings("finally")
	static public int selectUserPoint(int userId) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select Point from M_User where Id = ?;";
		int Point = 0;

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			ps.setInt(1, userId);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				Point = rs.getInt("Point");
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
			return Point;
		}
	}

	/*
	 * パスワードチェック
	 */
	@SuppressWarnings("finally")
	static public Boolean checkPassword(int userId, String strPassword) {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select Password from M_User where Id = ?;";
		String dbPassword = "";

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			ps.setInt(1, userId);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				dbPassword = rs.getString("Password");
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
			if (strPassword.equals(dbPassword)) {
				return true;
			} else {
				return false;
			}
		}
	}
	/*
	 *  最新ID取得
	 */
	@SuppressWarnings("finally")
	static public int selectLatestId() {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select MAX(Id) + 1 as latestId from M_User;";

		int latestId = 0;

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				latestId = rs.getInt("latestId");
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
			return latestId;
		}
	}

	@SuppressWarnings("finally")
	static public List<String> getPointList(){
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select Point from M_User order by Id asc;";
		List<String> retList = new ArrayList<String>();

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				retList.add(String.valueOf(rs.getInt("Point")));
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
			return retList;
		}
	}
	@SuppressWarnings("finally")
	static public List<String> getIdList(){
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select Id from M_User order by Id asc;";
		List<String> retList = new ArrayList<String>();

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				retList.add(String.valueOf(rs.getInt("Id")));
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
			return retList;
		}
	}
	/*
	 *  最新ID取得
	 */
	@SuppressWarnings("finally")
	static public int selectMaxPoint() {
		String drv = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql"; // DB URL
		String id = "root";
		String pass = "";
		String sqlGet = "select MAX(Point) as maxPoint from M_User;";

		int maxPoint = 0;

		//変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(drv);
			Connection con = DriverManager.getConnection(url, id, pass); //データベースに接続
			//実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sqlGet);
			//SELECTを実行する
			rs = ps.executeQuery();
			while (rs.next()) {
				maxPoint = rs.getInt("maxPoint");
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}  finally {
			return maxPoint;
		}
	}
}

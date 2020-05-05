package servlet;

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

}

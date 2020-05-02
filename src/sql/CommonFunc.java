package sql;

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
}

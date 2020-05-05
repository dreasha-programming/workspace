package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryMaster;

/**
 * Servlet implementation class M_UserRegister
 */
public class M_UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public M_UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//更新区分取得
		String InsUpdKbn = request.getParameter("InsUpdKbn");
		//ID取得
		int userId = Integer.valueOf(request.getParameter("userId"));

		//エラーチェック
		String strErrorMessage = CommonFunc.errorCheck(request.getParameter("userId"), request.getParameter("userName"), request.getParameter("password"),
				request.getParameter("mailAddress"), request.getParameter("point"));
		if (strErrorMessage!="") {
			//画面にパラメータセット
			request.setAttribute("userId", String.valueOf(userId));
			request.setAttribute("errorMessage", strErrorMessage);
			request.setAttribute("InsUpdKbn", InsUpdKbn);
			//ページ遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("ErrorPage.jsp");
			dispatch.forward(request, response);
			return;
		}

		//パラメータ受け取り
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String mailAddress = request.getParameter("mailAddress");
		int point = Integer.valueOf(request.getParameter("point"));

		if (InsUpdKbn.equals("Insert")) {
			//区分がInsertの場合
			//ユーザーマスタに登録
			QueryMaster.insert_M_User(userId, userName, password, mailAddress, point);

		} else if (InsUpdKbn.equals("Update")) {
			//区分がUpdateの場合
			//ユーザーマスタ更新
			QueryMaster.update_M_User(userId, userName, password, mailAddress, point);

		} else if (InsUpdKbn.equals("Delete")){
			//区分がDeleteの場合
			QueryMaster.delete_M_User(userId);

		} else {
			//何もしない
		}

		//画面のコントロールに値をセット
		request.setAttribute("userId", String.valueOf(userId));
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("mailAddress", mailAddress);
		request.setAttribute("point", String.valueOf(point));
		request.setAttribute("InsUpdKbn", InsUpdKbn);
		// ページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("M_UserRegister.jsp");
		dispatch.forward(request, response);

	}
}

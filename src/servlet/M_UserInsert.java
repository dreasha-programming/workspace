package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class M_UserMaintenance
 */
public class M_UserInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public M_UserInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//最新ID取得（MaxID＋１）
		int latestId = QueryGet.selectLatestId();
		String Id = request.getParameter("Id");	//ログインID

		//画面にパラメータセット
		request.setAttribute("latestId", String.valueOf(latestId));
		request.setAttribute("Id", Id);

		//ページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("M_UserInsert.jsp");
		dispatch.forward(request, response);
	}

}

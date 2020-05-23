package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class PointPutPage
 */
public class PointPutPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointPutPage() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		int userId = Integer.valueOf(request.getParameter("Id"));
		int remainPoint = QueryGet.selectUserPoint(userId);
		String userName = QueryGet.selectUserNameById(userId);
		//画面のコントロールに値をセット
		request.setAttribute("UserName", userName);
		request.setAttribute("remainPoint", String.valueOf(remainPoint));
		request.setAttribute("Id", String.valueOf(userId));

		// PointPutPage.jsp にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("PointPutPage.jsp");
		dispatch.forward(request, response);
		CommonFunc.insertAccessLog(Integer.valueOf(userId), "PointPutPage.jsp");
	}
}

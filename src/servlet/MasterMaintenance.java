package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class MasterMaintenance
 */
public class MasterMaintenance extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterMaintenance() {
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
		//パラメータ受け取り
		String Id = request.getParameter("Id");
		String userName = QueryGet.selectUserNameById(Integer.valueOf(Id));
		//画面のコントロールに値をセット
		request.setAttribute("Id", Id);
		request.setAttribute("userName", userName);

		// ページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("MasterMaintenance.jsp");
		dispatch.forward(request, response);
	}

}

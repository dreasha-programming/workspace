package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class PointGraph
 */
public class PointGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointGraph() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//パラメータ受け取り
		String id = (String) request.getParameter("Id");	//ログインID
		//リストでポイント一覧取得
		List<String> pointList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();
		pointList = QueryGet.getPointList();
		idList = QueryGet.getIdList();
		//配列変換
		String[] arrPoint = pointList.toArray(new String[pointList.size()]);
		String[] arrId = idList.toArray(new String[idList.size()]);
		String[] arrDiff = new String[pointList.size()];
		int i = 0;
		int maxPoint = QueryGet.selectMaxPoint();

		for (i=0;i<pointList.size();i++) {
			arrDiff[i] = String.valueOf(maxPoint - Integer.valueOf(arrPoint[i]));
		}

		int arrSize = arrPoint.length;

		//画面にパラメータセット
		request.setAttribute("arrPoint", arrPoint);
		request.setAttribute("arrId", arrId);
		request.setAttribute("arrDiff", arrDiff);
		request.setAttribute("Id", id);
		request.setAttribute("arrSize", String.valueOf(arrSize));

		//ページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("PointGraph.jsp");
		dispatch.forward(request, response);
	}

}

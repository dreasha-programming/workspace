package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import sql.QueryGet;


/**
 * Servlet implementation class T_AccessLogCheck
 */
public class T_AccessLogCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public T_AccessLogCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FileOutputStream fos = null;

		try {
		    // 日本語が文字化けしないテーマ
		    ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());

		    ArrayList<String> accessLogList = new ArrayList<String>();
		    accessLogList.addAll(QueryGet.getAccessLogList());
			String[] arrAccessLog = accessLogList.toArray(new String[accessLogList.size()]);
			int i = 0;
			String[] strTemp = new String[3];

		    // グラフデータを設定する
		    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		    for (i=0;i<accessLogList.size();i++) {
		    	strTemp = arrAccessLog[i].split(",");
		    	dataset.addValue(Integer.valueOf(strTemp[2]), strTemp[0], strTemp[1]);
		    	strTemp[0]="";
		    	strTemp[1]="";
		    	strTemp[2]="";
		    }

		    // グラフを生成する
		    JFreeChart chart = ChartFactory.createLineChart("AccessLog", "YearMonth", "Count", dataset, PlotOrientation.VERTICAL, true, false, false);

		    // 背景色を設定
		    chart.setBackgroundPaint(ChartColor.WHITE);

		    // ファイルへ出力する
		    String realPath = this.getServletContext().getRealPath(this.getClass().getSimpleName() + ".png");
		    String filePath = this.getClass().getSimpleName() + ".png";
		    fos = new FileOutputStream(realPath);
		    ChartUtilities.writeChartAsPNG(fos, chart, 600, 400);

		    //パラメータセット
	        String Id = request.getParameter("Id");
    		request.setAttribute("Id", String.valueOf(Id));
    		request.setAttribute("filePath", filePath);

    		//ページ遷移
    		RequestDispatcher dispatch = request.getRequestDispatcher("T_AccessLogCheck.jsp");
    		dispatch.forward(request, response);

		} catch (IOException e) {
		    // エラー処理
		} finally {
		    //IOUtils.closeQuietly(fos);
			fos.close();
		}
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.QueryGet;

/**
 * Servlet implementation class SendMail
 */
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String fromUserId = request.getParameter("Id");
        String toUserId = request.getParameter("toUserId");
        String toUserPoint = request.getParameter("txtPutPoint");
        final String strGmailAddress = QueryGet.selectMailAddressFromId(0);
        final String strGmailPassword = QueryGet.selectPasswordFromId(0);
        //String title = request.getParameter("title");
        //String message = request.getParameter("message");
        String title = "Please check your PointGetPage.";
        String message = QueryGet.selectUserNameById(Integer.valueOf(fromUserId)) + " put " + toUserPoint + " point.";
        message = message + " Please check your PointGetPage, and get your point.";
        String strToAddress = QueryGet.selectMailAddressFromId(Integer.valueOf(toUserId));
        String strToName = QueryGet.selectUserNameById(Integer.valueOf(toUserId));
        String strFromAddress = QueryGet.selectMailAddressFromId(Integer.valueOf(fromUserId));
        String strFromName = QueryGet.selectUserNameById(Integer.valueOf(fromUserId));

        //System.out.println("タイトル：" + title);
        //System.out.println("メッセージ" + message);

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        try{
            Properties property = new Properties();

            property.put("mail.smtp.host","smtp.gmail.com");

            //GmailのSMTPを使う場合
            property.put("mail.smtp.auth", "true");
            property.put("mail.smtp.starttls.enable", "true");
            property.put("mail.smtp.host", "smtp.gmail.com");
            property.put("mail.smtp.port", "587");
            property.put("mail.smtp.debug", "true");

            Session session = Session.getInstance(property, new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(strGmailAddress, strGmailPassword);
                }
            });

            /*
            //一般的なSMTPを使う場合

            //ポートが25の場合は省略可能
            property.put("mail.smtp.port", 25);

            Session session =
                    Session.getDefaultInstance(property, null);
            */

            MimeMessage mimeMessage = new MimeMessage(session);

            InternetAddress toAddress =
                    new InternetAddress(strToAddress, strToName);

            mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);

            InternetAddress fromAddress =
                    new InternetAddress(strFromAddress,strFromName);

            mimeMessage.setFrom(fromAddress);

            mimeMessage.setSubject(title, "ISO-2022-JP");

            mimeMessage.setText(message, "ISO-2022-JP");

            Transport.send(mimeMessage);

            //パラメータセット
    		request.setAttribute("Id", String.valueOf(fromUserId));
    		request.setAttribute("errorFlg", "0");

    		//ページ遷移
    		RequestDispatcher dispatch = request.getRequestDispatcher("SendMail.jsp");
    		dispatch.forward(request, response);

        }
        catch(Exception e){
            //パラメータセット
    		request.setAttribute("Id", String.valueOf(fromUserId));
    		request.setAttribute("errorFlg", "1");

    		//ページ遷移
    		RequestDispatcher dispatch = request.getRequestDispatcher("SendMail.jsp");
    		dispatch.forward(request, response);
    		CommonFunc.insertAccessLog(Integer.valueOf(fromUserId), "SendMail.jsp");
        }

        out.close();
	}
}

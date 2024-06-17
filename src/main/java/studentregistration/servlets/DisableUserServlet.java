package studentregistration.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.persistant.UserRepository;
import studentregistration.utils.Result;

/**
 * Servlet implementation class DisableUserServlet
 */
@WebServlet("/disable_user")
public class DisableUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserRepository user_repo=new UserRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisableUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isLogin")!=null) {
			if((int)request.getSession().getAttribute("userRole")!=3) {
				user_repo.disable(Integer.parseInt(request.getParameter("id")));
				request.getSession().setAttribute("result", new Result(1, "Disabled user"));
				response.sendRedirect("users_list");
			}else {
				request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
				response.sendRedirect("login");
			}
		}else {
			request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

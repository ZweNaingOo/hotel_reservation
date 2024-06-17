package studentregistration.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentregistration.models.UserResponseDTO;
import studentregistration.services.AuthService;
import studentregistration.utils.Result;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final AuthService authService = new AuthService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isLogin")==null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
			response.sendRedirect("welcome");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserResponseDTO user=authService.checkAndGetUser(email, password);
		if(user!=null) {
			if(user.getStatus()==0) {
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("error", "This account has not verified.");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}else if(user.getStatus()==2) {
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("error", "Your account has been temporary banned.");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}else {
				request.getSession().setAttribute("userId", user.getId());
				request.getSession().setAttribute("userRole", user.getRole());
				request.getSession().setAttribute("user_name", user.getName());
				request.getSession().setAttribute("isLogin", true);
				request.getSession().setAttribute("result", new Result(1, "Login completed."));
				response.sendRedirect("welcome");
			}
		}else {
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			request.setAttribute("error", "Incorrect email or password.");
			request.getRequestDispatcher("login.jsp").include(request, response);
		}
	}

}

package studentregistration.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.models.UserRequestDTO;
import studentregistration.persistant.UserRepository;
import studentregistration.utils.EmailData;
import studentregistration.utils.Result;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserRepository user_repo = new UserRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isLogin")==null || request.getSession().getAttribute("admin")!=null) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
			response.sendRedirect("welcome");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRequestDTO user=new UserRequestDTO();
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setRole(Integer.parseInt(request.getParameter("role")));
		Result result=user_repo.add(user);
		if(result.getResult()==1) {
			request.getSession().setAttribute("OTPEmail", new EmailData(user.getEmail(),1));
			response.sendRedirect("otp");
		}else {
			request.setAttribute("user", user);
			request.setAttribute("confirmPassword", request.getParameter("confirmPassword"));
			request.setAttribute("error", result.getMessage());
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

}

package studentregistration.servlets;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.models.UserResponseDTO;
import studentregistration.persistant.UserRepository;
import studentregistration.services.EmailService;
import studentregistration.utils.EmailData;
import studentregistration.utils.Result;

/**
 * Servlet implementation class OTPServlet
 */
@WebServlet("/otp")
public class OTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserRepository user_repo = new UserRepository();
    
    public OTPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmailData email=(EmailData)request.getSession().getAttribute("OTPEmail");
		if(!"".equals(email.getEmail()) && email.getEmail()!=null) {
			int otp=new Random().nextInt(100000,999999);
			user_repo.setOTP(otp, email.getEmail());
			if(email.getReason()==1) EmailService.sendEmail(email.getEmail(),"Student Registration - Register Verification", "OTP :"+otp);
			request.getRequestDispatcher("otp.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmailData email=(EmailData)request.getSession().getAttribute("OTPEmail");
		if(email!=null) {
			int OTPCode=user_repo.getOTP(email.getEmail());
			if(OTPCode==0) {
				request.setAttribute("error", "OTP code expired.");
				request.getRequestDispatcher("otp.jsp").forward(request, response);
			}else if(OTPCode!=Integer.parseInt(request.getParameter("user_input"))) {
				request.setAttribute("error","Invalid OTP please check your email.");
				request.getRequestDispatcher("otp.jsp").forward(request, response);
			}else {
				UserResponseDTO user=user_repo.findByEmail(email.getEmail());
				if(user!=null) {
					if(email.getReason()==1) {
						user_repo.enable(user.getId());
						if(request.getSession().getAttribute("admin")==null) {
							request.getSession().setAttribute("result", new Result(1,"Successfully created your account<br>Please signin to your account."));
							response.sendRedirect("login");
						}else {
							request.getSession().removeAttribute("admin");
							request.getSession().setAttribute("result", new Result(1,"An admin added."));
							response.sendRedirect("users_list");
						}
					}
				}else {
					request.getSession().setAttribute("result", new Result(1,"This email is doesn't have account."));
					response.sendRedirect("register");
				}
			}
		}else {
			request.getSession().setAttribute("result", new Result(1,"Unknown error occured."));
			response.sendRedirect("register");
		}
	}

}

package studentregistration.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.persistant.CourseRepository;
import studentregistration.utils.Result;

/**
 * Servlet implementation class DisableCourseServlet
 */
@WebServlet("/disable_course")
public class DisableCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final CourseRepository course_repo = new CourseRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisableCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isLogin")!=null) {
			if((int)request.getSession().getAttribute("userRole")!=3) {
				course_repo.disable(Integer.parseInt(request.getParameter("id")));
				request.getSession().setAttribute("result", new Result(1, "Disabled course"));
				response.sendRedirect("courses_list");
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

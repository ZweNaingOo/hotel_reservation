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
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/add_course")
public class AddCourseServlet extends HttpServlet {
	private static final CourseRepository course_repo = new CourseRepository();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isLogin")!=null) {
			if((int)request.getSession().getAttribute("userRole")!=3)
				request.getRequestDispatcher("add_course.jsp").forward(request, response);
			else {
				request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
				response.sendRedirect("login");
			}
		}else {
			request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
			response.sendRedirect("login");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Result result =course_repo.add(request.getParameter("name"), (int)request.getSession().getAttribute("userId"));
		if(result.getResult()==0) {
			request.setAttribute("error", result.getMessage());
			request.getRequestDispatcher("add_course.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("result", result);
			response.sendRedirect("courses_list");
		}
	}

}

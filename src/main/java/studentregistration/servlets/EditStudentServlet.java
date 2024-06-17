package studentregistration.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentregistration.models.EditStudentDTO;
import studentregistration.persistant.CourseRepository;
import studentregistration.persistant.StudentRepository;
import studentregistration.utils.Result;

/**
 * Servlet implementation class EditStudentServlet
 */
@WebServlet("/edit_student")
@MultipartConfig
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final StudentRepository student_repo=new StudentRepository();
	private static final CourseRepository course_repo=new CourseRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isLogin")!=null) {
			if((int)request.getSession().getAttribute("userRole")!=3) {
				int id=Integer.parseInt(request.getParameter("id"));
				request.setAttribute("student", student_repo.findAllDetails(id));
				request.setAttribute("courses", course_repo.findAll());
				request.setAttribute("courseIds", student_repo.findCourses(id));
				request.setAttribute("id", id);
				request.getRequestDispatcher("edit_student.jsp").forward(request, response);
			}else {
				request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
				response.sendRedirect("login");
			}
		}else {
			request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
			response.sendRedirect("login");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EditStudentDTO student=new EditStudentDTO();
		student.setId(Integer.parseInt(request.getParameter("id")));
		student.setName(request.getParameter("name"));
		student.setDob(request.getParameter("dob"));
		student.setGender(Integer.parseInt(request.getParameter("gender")));
		student.setPhone(request.getParameter("phone"));
		student.setEducation(Integer.parseInt(request.getParameter("education")));
		student.setCourses(request.getParameterValues("courseIds"));
		if(request.getPart("image").getInputStream().toString().contains("FileInputStream"))student.setImage(request.getPart("image").getInputStream());
		else student.setImage(null);
		if(student.getCourses()==null) {
			request.setAttribute("student", student);
			request.setAttribute("courses", course_repo.findAll());
			request.setAttribute("error", "*Choose at least one attend.");
			request.getRequestDispatcher("edit_student.jsp").forward(request, response);
		}else {
			Result result=student_repo.edit(student, (int)request.getSession().getAttribute("userId"));
			if(result.getResult()!=0) response.sendRedirect("students_list");
			else {
				request.getSession().setAttribute("result", new Result(1, "Sorry, unknown error occurs."));
				response.sendRedirect("edit_student?id="+student.getId());
			}
		}
		
	}

}

package studentregistration.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentregistration.models.StudentRequestDTO;
import studentregistration.persistant.CourseRepository;
import studentregistration.persistant.StudentRepository;
import studentregistration.utils.Result;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/add_student")
@MultipartConfig
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final StudentRepository student_repo=new StudentRepository();
	private static final CourseRepository course_repo=new CourseRepository();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isLogin")!=null) {
			request.setAttribute("courses", course_repo.findAll());
			request.getRequestDispatcher("add_student.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("result", new Result(1, "Invalid rout"));
			response.sendRedirect("login");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentRequestDTO student=new StudentRequestDTO();
		student.setName(request.getParameter("name"));
		student.setDob(request.getParameter("dob"));
		student.setGender(Integer.parseInt(request.getParameter("gender")));
		student.setPhone(request.getParameter("phone"));
		student.setEducation(Integer.parseInt(request.getParameter("education")));
		student.setCourses(request.getParameterValues("courseIds"));
		student.setImage(request.getPart("image").getInputStream());
		if(student.getCourses()==null) {
			request.setAttribute("student", student);
			request.setAttribute("courses", course_repo.findAll());
			request.setAttribute("error", "*Choose at least one attend.");
			request.getRequestDispatcher("add_student.jsp").forward(request, response);
		}else {
			Result result=student_repo.add(student, (int)request.getSession().getAttribute("userId"));
			if(result.getResult()==1) {
				request.getSession().setAttribute("result", new Result(1, "Registered student."));
				if((int)request.getSession().getAttribute("userRole")==3) response.sendRedirect("add_student");
				else response.sendRedirect("students_list");
			}else {
				System.out.println("Fail add student");
				request.getRequestDispatcher("add_student.jsp").forward(request, response);
			}
		}
		
	}

}

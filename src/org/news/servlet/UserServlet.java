package org.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.news.entity.User;
import org.news.service.UserService;
import org.news.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 7308078748761515673L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        String opr = request.getParameter("opr");
        UserService userService = new UserServiceImpl();
        try {

            if ("login".equals(opr)) {
            	request.setCharacterEncoding("UTF-8");
                String uname = request.getParameter("uname");
                String password = request.getParameter("upwd");

                User user = new User();
                user.setUname(uname);
                user.setUpwd(password);
                user = userService.doLogin(user);
                if (user == null) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"用户名密码错误，请重新登录\");");
                    out.print("open(\"" + contextPath
                            + "/index.jsp\",\"_self\");");
                    out.print("</script>");
                } else {
                	
                	if(user.getRole().equals("管理员")) {
                		
                		request.getSession().setAttribute("user", user);
                		response.sendRedirect(contextPath + "/util/news?opr=list");
                		
                	}
                	else if(user.getRole().equals("新闻编辑员")) {
                		request.getSession().setAttribute("user", user);
                		response.sendRedirect(contextPath + "/util/news?opr=editorlist");
                		
                		
                	}
                	else if(user.getRole().equals("用户")) {
                		
                		request.getSession().setAttribute("user", user);
                		request.getRequestDispatcher("/index.jsp").forward(request, response);
                	}
                	
                }
          
                
            }
            else if(opr.equals("register")) {
            	request.setCharacterEncoding("UTF-8");
            	String uname=request.getParameter("uname");
            	String upwd=request.getParameter("upwd");
            	User user=new User();
            	user.setUname(uname);
            	user.setUpwd(upwd);
            	user.setRole("用户");
            	userService.register(user);
            	request.getRequestDispatcher("/index.jsp").forward(request, response);
            	
            	
            	
            	
            }
            
            else if(opr.equals("logout")) {
            	HttpSession session=request.getSession();

            	session.removeAttribute("user");
            	request.getRequestDispatcher("/index.jsp").forward(request, response);
            	
            }
            else if(opr.equals("toAddEditor")){
            	request.setCharacterEncoding("UTF-8");
            	String uname=request.getParameter("uname");
            	String upwd=request.getParameter("upwd");
            	User user=new User();
            	user.setUname(uname);
            	user.setUpwd(upwd);
            	user.setRole("新闻编辑员");
            	userService.register(user);
            	request.getRequestDispatcher("/util/user?opr=user_per").forward(request, response);
            	
            	
            }
            else if(opr.equals("user_per")) {
            	
            	List<User> userlist=userService.getAlluser();
            	request.setAttribute("userlist", userlist);
            	request.getRequestDispatcher("/newspages/user_per.jsp").forward(request, response);
            	
            }
            
            else if(opr.equals("delete")) {
            	String uid=request.getParameter("uid");
            	userService.delete(Integer.parseInt(uid));
            	request.getRequestDispatcher("/util/user?opr=user_per").forward(request, response);
            	
            	
            }
            else if(opr.equals("update")) {
            	
            	request.setCharacterEncoding("UTF-8");
            	String uid=request.getParameter("uid");
            	String uname=request.getParameter("uname");
            	String upwd=request.getParameter("upwd");
            	
            	User user=new User();
            	user.setUid(Integer.parseInt(uid));
            	user.setUname(uname);
            	user.setUpwd(upwd);
            	
            	userService.update(user);
            	request.getRequestDispatcher("/util/user?opr=user_per").forward(request, response);
            	
            }
            else if(opr.equals("toUpdateuser")) {
            	String uid=request.getParameter("uid");
            	User user=userService.findUserById(Integer.parseInt(uid));
            	request.setAttribute("user", user);
            	request.getRequestDispatcher("/newspages/user_modify.jsp").forward(request, response);
            	
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }

}

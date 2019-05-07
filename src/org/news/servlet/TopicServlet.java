package org.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.entity.Topic;
import org.news.service.TopicsService;
import org.news.service.impl.TopicsServiceImpl;

public class TopicServlet extends HttpServlet {
    private static final long serialVersionUID = -8823896301195695638L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();
        String opr = request.getParameter("opr");
        TopicsService topicsService = new TopicsServiceImpl();

        if ("del".equals(opr)) { // 删除主题
            String tid = request.getParameter("tid");
            try {
                int result = topicsService.deleteTopic(Integer.parseInt(tid));
                if (result == -1) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"该主题下还有文章，不能删除！\");");
                    out.print("location.href=\"" + contextPath
                            + "/util/topics?opr=list\";");
                    out.print("</script>");
                } else if (result == 0) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"未找到相关主题，点击确认返回主题列表\");");
                    out.print("location.href=\"" + contextPath
                            + "/util/topics?opr=list\";");
                    out.print("</script>");
                } else {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"已经成功删除主题，点击确认返回主题列表\");");
                    out.print("location.href=\"" + contextPath
                            + "/util/topics?opr=list\";");
                    out.print("</script>");
                }
            } catch (Exception e) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"删除失败，请联系管理员！点击确认返回主题列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?opr=list\";");
                out.print("</script>");
            }
        } else if ("update".equals(opr)) { // 更新主题
            String tid = request.getParameter("tid");
            String tname = request.getParameter("tname");
            Topic topic = new Topic();
            topic.setTid(Integer.parseInt(tid));
            topic.setTname(tname);
            try {
                int result = topicsService.updateTopic(topic);
                if (result == -1) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"当前主题已存在，请输入不同的主题！\");");
                    out.print("location.href=\"" + contextPath
                            + "/newspages/topic_modify.jsp?tid=" + tid
                            + "&tname=" + tname + "\";");
                    out.print("</script>");
                } else if (result == 0) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"未找到相关主题，点击确认返回主题列表\");");
                    out.print("location.href=\"" + contextPath
                            + "/util/topics?opr=list\";");
                    out.print("</script>");
                } else {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"已经成功更新主题，点击确认返回主题列表\");");
                    out.print("location.href=\"" + contextPath
                            + "/util/topics?opr=list\";");
                    out.print("</script>");
                }
            } catch (Exception e) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"更新失败，请联系管理员！点击确认返回主题列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?opr=list\";");
                out.print("</script>");
            }
        } else if (opr.equals("list")) {
            List<Topic> list = null;
            try {
                list = topicsService.findAllTopics();
            } catch (SQLException e) {
                e.printStackTrace();
                list = new ArrayList<Topic>();
            }
            request.setAttribute("list", list);
            request.getRequestDispatcher("/newspages/topic_list.jsp").forward(
                    request, response);
        } else if (opr.equals("add")) {// 添加主题
            String tname = request.getParameter("tname");
            try {
                int result = topicsService.addTopic(tname);
                if (result == -1) {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"当前主题已存在，请输入不同的主题！\");");
                    out.print("location.href=\"" + contextPath
                            + "/newspages/topic_add.jsp\";");
                    out.print("</script>");
                } else {
                    out.print("<script type=\"text/javascript\">");
                    out.print("alert(\"主题创建成功，点击确认返回主题列表！\");");
                    out.print("location.href=\"" + contextPath
                            + "/util/topics?opr=list\";");
                    out.print("</script>");
                }
            } catch (Exception e) {
                out.print("<script type=\"text/javascript\">");
                out.print("alert(\"添加失败，请联系管理员！点击确认返回主题列表\");");
                out.print("location.href=\"" + contextPath
                        + "/util/topics?opr=list\";");
                out.print("</script>");
            }
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

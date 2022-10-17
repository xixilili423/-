//package com.example.demo.action;
//import com.example.demo.pojo.user;
//import com.example.demo.service.loginimpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//
//@WebServlet(urlPatterns = "/user1.let",loadOnStartup = 1)//配置虚拟地址
//public class userServlet extends HttpServlet {
//    //构建UserBiz的对象，报错因为serverlet访问web下的jar包，lib中的包在部署中不存在
//   @Autowired
//    loginimpl userBiz = new loginimpl();
//   @Autowired
//    user US;
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
//
//    /**
//     * /user.let?type=login 登录  /表示项目的根目录:web文件夹
//     * /user.let?type=exit  安全退出
//     * /user.let?type=modifyPwd 修改密码
//     */
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        req.setCharacterEncoding("utf-8");//设置编码格式防止乱码
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//
//        //判读用户请求的类型为login
//        String method = req.getParameter("type");
//        switch (method){
//            case "login":
//                // 从login.html中获取用户名和密码,验证码
//                String name = req.getParameter("name");
//                String pwd = req.getParameter("pwd");
//                String userCode = req.getParameter("valcode");
//
//                //提取session中的验证码,进行判断,dopost中存在code中
//               String code =session.getAttribute("code").toString();
//
//               //不区分大小写
//                if(!code.equalsIgnoreCase(userCode)){
//                    out.println("<script>alert('验证码输入错误');location.href = 'login.html';</script>");
//                    return;
//                }
//
//                //调用UserBiz的getUser方法，根据用户名和密码获取对应的用户对象
//                boolean user = userBiz.log(name,pwd);
//
//                // 4.判断用户对象是否为null: 
//                if(user==false){
//                    // 如果是null表示用户名或密码不正确 ，提示错误，回到登录页面. 
//                 out.println("<script>alert('用户名或密码不存在');location.href = 'login.html';</script>");
//                }else {
//                    //  非空：表示登录成功, 将用户对象保存到session中,提示登录成功后,将页面跳转到主界面
//                  session.setAttribute("user",user);//user-->Object
//                  out.println("<script>alert('登录成功');location.href='index.jsp';</script>");
//                }
//                break;
//            case "exit":
//                //验证用户是否登录
//                if(session.getAttribute("user")==null){
//                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
//                    return;
//                }
//
//                //1.清除session
//                session.invalidate();
//                //2.跳转到login.html(框架中跳转回去)  top.jsp->parent->index.jsp
//                out.println("<script>parent.window.location.href='login.html';</script>");
//                break;
//            case "modifyPwd":
//
//                //验证用户是否登录
//                if(session.getAttribute("user")==null){
//                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
//                    return;
//                }
//
//                //修改密码
//                //1.获取用户输入的新的密码
//                String newPwd = req.getParameter("newpwd");
//                if(newPwd.length()<1){
//                    out.println("<script>alert('新密码不能为空');parent.window.location.href='index.jsp';</script>");
//                    break;
//                }
//                String spwd = req.getParameter("pwd");
//                if(spwd!=US.getPassword()){
//                    out.println("<script>alert('原密码输入错误');parent.window.location.href='index.jsp';</script>");
//                    break;
//                }
//
//                //2.获取用户的编号-session
//
//                String id = (((com.example.demo.pojo.user)session.getAttribute("user")).getId());
//                //3.调用biz层方法
//                boolean count= userBiz.changepassword(id,newPwd);
//                //4.响应-参考exit
//                if(count==true){
//                    out.println("<script>alert('密码修改成功');parent.window.location.href='login.html';</script>");
//                }else{
//                    out.println("<script>alert('密码修改失败');parent.window.location.href='login.html';</script>");
//                }
//                break;
//        }
//    }
//}

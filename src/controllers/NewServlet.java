package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //	response.getWriter().append("Served at: ").append(request.getContextPath());
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        // インスタンスを生成
        Tasks tsk = new Tasks();

     // 各フィールドにデータを代入
        String content = "hello";
        tsk.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        tsk.setCreated_at(currentTime);
        tsk.setUpdated_at(currentTime);

        // データベースに保存
        em.persist(tsk);
        em.getTransaction().commit();

        // 自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(tsk.getId()).toString());

        em.close();
    }


}

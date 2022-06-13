package com.example.javahaduynhat.controller.category;

import com.example.javahaduynhat.entity.Category;
import com.example.javahaduynhat.entity.myenum.CategoryStatus;
import com.example.javahaduynhat.model.CategoryModel;
import com.example.javahaduynhat.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public DeleteCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryModel.findById(id);
        if (category == null) {
            req.getRequestDispatcher("/admin/view/page/404.jsp").forward(req, resp);
            return;
        }
        try {
            if (categoryModel.delete(id)) {
                resp.sendRedirect("/admin/category/list");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

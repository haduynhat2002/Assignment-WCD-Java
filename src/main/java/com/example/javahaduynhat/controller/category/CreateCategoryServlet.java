package com.example.javahaduynhat.controller.category;

import com.example.javahaduynhat.entity.Category;
import com.example.javahaduynhat.model.CategoryModel;
import com.example.javahaduynhat.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCategoryServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public CreateCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            String name = req.getParameter("name");
            Category category = Category.CategoryBuilder.aCategory()
                    .name(name)
                    .build();
            if (category.isValid()) {
                boolean result = categoryModel.save(category);
                if (result) {
                    resp.sendRedirect("/admin/category/list");
                } else {
                    throw new Exception("Can save product");
                }
            } else {
                req.setAttribute("errors", category.getErrors());
                req.setAttribute("categories", categoryModel.findAll());
                req.setAttribute("category", category);
                req.getRequestDispatcher("/admin/product/list.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            req.getRequestDispatcher("/admin/error/500.jsp").forward(req, resp);
        }

    }
}

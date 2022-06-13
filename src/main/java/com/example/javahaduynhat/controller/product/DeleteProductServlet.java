package com.example.javahaduynhat.controller.product;

import com.example.javahaduynhat.entity.Category;
import com.example.javahaduynhat.entity.Product;
import com.example.javahaduynhat.entity.myenum.CategoryStatus;
import com.example.javahaduynhat.entity.myenum.ProductStatus;
import com.example.javahaduynhat.model.CategoryModel;
import com.example.javahaduynhat.model.MySqlCategoryModel;
import com.example.javahaduynhat.model.MySqlProductModel;
import com.example.javahaduynhat.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    private ProductModel productModel;

    public DeleteProductServlet() {
        this.productModel = new MySqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productModel.findById(id);
        if (product == null) {
            req.getRequestDispatcher("/admin/view/page/404.jsp").forward(req, resp);
            return;
        }
        try {
            if (productModel.delete(id)) {
                resp.sendRedirect("/admin/product/list");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

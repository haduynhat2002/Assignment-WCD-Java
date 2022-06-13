package com.example.javahaduynhat.controller.product;

import com.example.javahaduynhat.entity.Category;
import com.example.javahaduynhat.entity.Product;
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
import java.util.List;

public class CreateProductServlet extends HttpServlet {
    private ProductModel productModel;
    private CategoryModel categoryModel;

    public CreateProductServlet() {

        this.productModel = new MySqlProductModel();
        this.categoryModel = new MySqlCategoryModel();
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = this.categoryModel.findAll();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            String name = req.getParameter("name");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            double price = Double.parseDouble(req.getParameter("price"));
            int status = Integer.parseInt(req.getParameter("status"));
            Product product = Product.ProductBuilder.aProduct()
                    .name(name)
                    .thumbnail(thumbnail)
                    .price(price)
                    .categoryId(categoryId)
                    .description(description)
                    .status(ProductStatus.of(status))
                    .build();
            if (product.isValid()) {
                boolean result = productModel.save(product);
                if (result) {
                    resp.sendRedirect("/admin/product/list");
                } else {
                    throw new Exception("Can save product");
                }
            } else {
                req.setAttribute("errors", product.getErrors());
                req.setAttribute("categories", categoryModel.findAll());
                req.getRequestDispatcher("/admin/product/list.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            req.getRequestDispatcher("/admin/error/500.jsp").forward(req, resp);
        }
    }
}

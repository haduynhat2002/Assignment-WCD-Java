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
import java.time.LocalDateTime;
import java.util.List;

public class UpdateProductServlet extends HttpServlet {
    private ProductModel productModel;
    private CategoryModel categoryModel;

    public UpdateProductServlet() {

        this.productModel = new MySqlProductModel();
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productModel.findById(id);
            if (product == null) {
                req.setAttribute("message", "Product is not found!");
                req.getRequestDispatcher("/admin/error/404.jsp");
                return;
            }
            List<Category> categories = this.categoryModel.findAll();
            req.setAttribute("categories",categories);
            req.setAttribute("products", product);
            req.getRequestDispatcher("update.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/admin/error/500.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String name = req.getParameter("name");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String description = req.getParameter("description");
            String thumbnail = req.getParameter("thumbnail");
            double price = Double.parseDouble(req.getParameter("price"));
            int status = Integer.parseInt(req.getParameter("status"));
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productModel.findById(id);
            if (product == null) {
                req.setAttribute("message", "Product is not found!");
                req.getRequestDispatcher("/admin/view/page/404.jsp").forward(req, resp);
            }
            product = Product.ProductBuilder.aProduct()
                    .name(name)
                    .thumbnail(thumbnail)
                    .price(price)
                    .categoryId(categoryId)
                    .description(description)
                    .dateFix(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .status(ProductStatus.of(status))
                    .build();
            if (product.isValid()) {
                boolean result = productModel.update(id, product);
                if (result) {
                    resp.sendRedirect("/admin/product/list");
                } else {
                    throw new Exception("Can't update category");
                }
            } else {
                req.setAttribute("errors", product.getErrors());
                req.setAttribute("products", productModel.findAll());
                req.setAttribute("products", product);
                req.getRequestDispatcher("create.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/admin/error/500.jsp").forward(req, resp);
        }
    }
}

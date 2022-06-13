package com.example.javahaduynhat.entity;

import com.example.javahaduynhat.entity.BaseEntity.BaseEntity;
import com.example.javahaduynhat.entity.myenum.ProductStatus;

import java.time.LocalDateTime;
import java.util.HashMap;


public class Product extends BaseEntity {
    private int id;
    private String name;
    private int categoryId;
    private String description;
    private String thumbnail;
    private double price;
    private LocalDateTime saleDate;
    private LocalDateTime dateFix;
    private ProductStatus status;

    public Product() {
        this.setName("");
        this.setThumbnail("");
        this.setSaleDate(LocalDateTime.now());
        this.setDescription("");
        this.setPrice(0);
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.setCreatedBy(0);
        this.setUpdatedBy(0);
        this.setStatus(ProductStatus.ARESELLING);
    }

    public Product(int id, String name, int categoryId, String description, String thumbnail, double price, LocalDateTime saleDate, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.saleDate = saleDate;
        this.status = status;
    }

    public Product(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, int createdBy, int updatedBy, int deletedBy, int id, String name, int categoryId, String description, String thumbnail, double price, LocalDateTime saleDate, LocalDateTime dateFix, ProductStatus status) {
        super(createdAt, updatedAt, deletedAt, createdBy, updatedBy, deletedBy);
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.saleDate = saleDate;
        this.dateFix = dateFix;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public LocalDateTime getDateFix() {
        return dateFix;
    }

    public void setDateFix(LocalDateTime dateFix) {
        this.dateFix = dateFix;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    private HashMap<String, String> errors = new HashMap<>();

    public boolean isValid() {
        checkValid();
        return errors.size() == 0;
    }

    private void checkValid() {
        if (name == null || name.length() == 0) {
            errors.put("username", "Please enter category name.");
        }
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }


    public static final class ProductBuilder {
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;
        private int createdBy;
        private int updatedBy;
        private int deletedBy;
        private int id;
        private String name;
        private int categoryId;
        private String description;
        private String thumbnail;
        private double price;
        private LocalDateTime saleDate;
        private LocalDateTime dateFix;
        private ProductStatus status;

        private ProductBuilder() {
            this.name = ("");
            this.price = (0);
            this.createdAt = (LocalDateTime.now());
            this.updatedAt = (LocalDateTime.now());
            this.saleDate = (LocalDateTime.now());
            this.createdBy = (0);
            this.updatedBy = (0);
            this.status = (ProductStatus.ARESELLING);
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ProductBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ProductBuilder deletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public ProductBuilder createdBy(int createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public ProductBuilder updatedBy(int updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public ProductBuilder deletedBy(int deletedBy) {
            this.deletedBy = deletedBy;
            return this;
        }

        public ProductBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder categoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder thumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public ProductBuilder price(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder saleDate(LocalDateTime saleDate) {
            this.saleDate = saleDate;
            return this;
        }

        public ProductBuilder dateFix(LocalDateTime dateFix) {
            this.dateFix = dateFix;
            return this;
        }

        public ProductBuilder status(ProductStatus status) {
            this.status = status;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setCategoryId(categoryId);
            product.setDescription(description);
            product.setThumbnail(thumbnail);
            product.setPrice(price);
            product.setSaleDate(saleDate);
            product.setDateFix(dateFix);
            product.setCreatedAt(createdAt);
            product.setUpdatedAt(updatedAt);
            product.setDeletedAt(deletedAt);
            product.setCreatedBy(createdBy);
            product.setUpdatedBy(updatedBy);
            product.setDeletedBy(deletedBy);
            product.setStatus(status);
            return product;
        }
    }
}

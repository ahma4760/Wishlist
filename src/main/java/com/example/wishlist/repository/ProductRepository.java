package com.example.wishlist.repository;

import com.example.wishlist.model.Product;
import com.example.wishlist.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    //datebase-properties injects with value from application.properties


    @Value("jdbc:mysql:${DB_URL}")
    private String DB_URL;

    @Value("${UID}")
    private String UID;

    @Value("${PWD}")
    private String PWD;

    public List<Product> getAll(){
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection(DB_URL,UID,PWD);
            Statement statement = connection.createStatement();
            final String SQL_Query = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(SQL_Query);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                Product product = new Product(id, name, price);
                productList.add(product);
                System.out.println(product);
            }
        }
        catch (SQLException e){
            System.out.println("Could not query database");
            e.printStackTrace();
        }
        return productList;
    }
    public void addProduct(Product product){
        try {
            //connect to db
            Connection connection = ConnectionManager.getConnection(DB_URL,UID,PWD);
            final String CREATE_QUERY = "INSERT INTO products(name, price) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            //set attributes in preprared statement
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());

            // execute statement
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Could not create product");
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product){
        final String UPDATE_QUERY = "UPDATE products SET name = ?, price = ?, WHERE id = ?";
        try {
            //connect db
            Connection connection = ConnectionManager.getConnection(DB_URL,UID,PWD);

            //prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            //set parameters
            String name = product.getName();
            double price = product.getPrice();
            int id = product.getId();
            preparedStatement.setString(1,name);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3,id);

            //execute statement
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Could not update product");
            e.printStackTrace();
        }
    }

    public Product findProductById(int id){
        //SQL-statement
        final String FIND_QUERY = "SELECT * FROM products WHERE id = ?";
        Product product = new Product();
        product.setId(id);
        try {
            //db connection
            Connection connection = ConnectionManager.getConnection(DB_URL,UID,PWD);
            //prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);

            //set parameters
            preparedStatement.setInt(1,id);
            //execute statement
            ResultSet resultSet = preparedStatement.executeQuery();
            //get result from resultset
            resultSet.next();
            String name = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            product.setName(name);
            product.setPrice(price);

        }catch (SQLException e){
            System.out.println("Could not find product");
            e.printStackTrace();
        }
        System.out.println(product);
        return product;
    }
    public void deleteById(int id){
        final String DELETE_QUERY = "DELETE FROM products WHERE id=?";
        try {
            //connection to database
            Connection connection = ConnectionManager.getConnection(DB_URL,UID,PWD);
            //create statement
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            //set parameter
            preparedStatement.setInt(1,id);
            //execute statement
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Could not delete product");
            e.printStackTrace();
        }
    }
}

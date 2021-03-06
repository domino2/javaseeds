package com.hellokoding.jooq;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Properties;

import com.hellokoding.jooq.model.dbo.tables.Author;

public class Application {
    
    public static void main(String[] args) throws Exception {
        
        Properties prop = new Properties();
        prop.load(Application.class.getResourceAsStream ("db.properties"));
        
        System.out.println(System.getProperty("user.dir")); //C:\Users\bhorisk\Desktop\jooq-mysql-master
        System.out.println(System.getProperty("java.class.path")); //C:\Users\bhorisk\Desktop\jooq-mysql-master\target\classes
        
        String user = prop.getProperty("db.username");
        String password = prop.getProperty("db.password");
        String url = prop.getProperty("db.url");
        String driver = prop.getProperty("db.driver");
        
        
        Class.forName(driver).newInstance();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            DSLContext dslContext = DSL.using(connection, SQLDialect.DEFAULT);
            Result<Record> result = dslContext.select().from(Author.AUTHOR).fetch();

            for (Record r : result) {
                Integer id = (Integer)r.getValue(Author.AUTHOR.ID);
                String firstName = r.getValue(Author.AUTHOR.FIRST_NAME);
                String lastName = r.getValue(Author.AUTHOR.LAST_NAME);

                System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
            }
        }
        catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }
}

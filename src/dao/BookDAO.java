package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import util.DbUtil;

public class BookDAO {
    public void addBook(Book book){
        String sql = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";

        try(
            Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.isAvailable());

            ps.executeUpdate();
            System.out.println("Book successfully added!");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try(
            Connection conn = DbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){
            while (rs.next()){
                books.add(new Book(rs.getInt("book_id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> search(String value){
        List<Book> res = new ArrayList<>();
        String sql = "SELECT book_id, title, author, available FROM books WHERE title LIKE ?";

        try(
            Connection conn = DbUtil.getConnection();
        ){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + value + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                res.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getBoolean("available")));
            }

            ps.close();
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }


    public void updateAvailability(int bookId, boolean availability){
        String sql = "UPDATE books SET available = ? WHERE book_id = ?";

        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, availability);
            ps.setInt(2, bookId);

            ps.executeUpdate();
            System.out.print(bookId + " is now ");
            System.out.println(availability ? "available" : "not available");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

package dao;

import model.IssuedBook;
import util.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueDAO {
    public boolean issueBook(int bookId, int memberId) {
        String updateBook = "UPDATE books SET available = FALSE WHERE book_id = ? AND available = TRUE";
        String insertIssue = "INSERT INTO issued_books (book_id, member_id, issue_date) VALUES (?, ?, ?)";

        try (Connection conn = DbUtil.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement ps1 = conn.prepareStatement(updateBook);
                    PreparedStatement ps2 = conn.prepareStatement(insertIssue);
            ) {
                ps1.setInt(1, bookId);
                int rowsUpdated = ps1.executeUpdate();

                if (rowsUpdated == 0) {
                    System.out.println("Book is either not found or already issued");
                    conn.rollback();
                    return false;
                }

                ps2.setInt(1, bookId);
                ps2.setInt(2, memberId);
                ps2.setDate(3, Date.valueOf(LocalDate.now()));
                ps2.executeUpdate();

                conn.commit();
                System.out.println("Book issued successfully!");
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnBook(int issueId) {
        String updateBook = "UPDATE books SET available = TRUE WHERE book_id = (SELECT book_id FROM issued_books WHERE issue_id = ?)";
        String updateIssue = "UPDATE issued_books SET return_date = ? WHERE issue_id = ?";

        try (Connection conn = DbUtil.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement ps1 = conn.prepareStatement(updateBook);
                    PreparedStatement ps2 = conn.prepareStatement(updateIssue);
            ) {
                ps1.setInt(1, issueId);
                int rowsUpdated = ps1.executeUpdate();

                if (rowsUpdated == 0) {
                    System.out.println("Invalid issue id or book already returned.");
                    conn.rollback();
                    return false;
                }

                ps2.setDate(1, Date.valueOf(LocalDate.now()));
                ps2.setInt(2, issueId);
                ps2.executeUpdate();

                conn.commit();
                System.out.println("Book returned successfully");
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<IssuedBook> getIssuedBooks() {
        List<IssuedBook> issuedBooks = new ArrayList<>();
        String sql = "SELECT * FROM issued_books";

        try (
                Connection conn = DbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()){
                int issuedId = rs.getInt("issue_id");
                int bookId = rs.getInt("book_id");
                int memberId = rs.getInt("member_id");
                Date issueDate = rs.getDate("issue_date");
                Date returnDate = rs.getDate("return_date");
                issuedBooks.add(new IssuedBook(issuedId, bookId, memberId, issueDate, returnDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return issuedBooks;
    }

}

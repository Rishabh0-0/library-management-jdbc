import dao.BookDAO;
import dao.IssueDAO;
import dao.MemberDAO;
import model.Book;
import model.Member;

import java.util.List;

public class Main {
    public static void main(String[] args){
        BookDAO booksDao = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        IssueDAO issueDAO = new IssueDAO();


//       List<String> books = booksDao.getAllBooks();
//       books.forEach(System.out::println);

//       System.out.println(booksDao.search("The Hob"));
//       booksDao.updateAvailability(6, false);


//        booksDao.addBook(new Book("poggers", "me", true));
//        booksDao.getAllBooks().forEach(System.out::println);
//
//        MemberDAO memberDao = new MemberDAO();
//        memberDao.getAllMembers().forEach(System.out::println);



//        issueDAO.issueBook(4, 1);
//        issueDAO.getIssuedBooks();
//        booksDao.getAllBooks().forEach(System.out::println);

//        issueDAO.returnBook(1);
//        booksDao.getAllBooks().forEach(System.out::println);

    }

}


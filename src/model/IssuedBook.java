package model;

import java.sql.Date;

public class IssuedBook {
    private int issue_id;
    private int book_id;
    private int member_id;
    private Date issueDate;
    private Date returnDate;


    public IssuedBook(int issue_id, int book_id, int member_id, Date issueDate, Date returnDate) {
        this.issue_id = issue_id;
        this.book_id = book_id;
        this.member_id = member_id;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "IssuedBook{" +
                "issue_id=" + issue_id +
                ", book_id=" + book_id +
                ", member_id=" + member_id +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

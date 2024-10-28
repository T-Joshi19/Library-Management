import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Book {   //details of book
int bookID;
String title;
String author;
boolean isAvailable;
    
public Book(int id, String title, String author){
    this.bookID = id;
    this.title = title;
    this.author=author;
    this.isAvailable = true;
}
}
 class Library {  //system to add and search books
Book[] books = new Book[100];
int bookCount=0;

    void addBook(int bookId, String title, String author){
        books[bookCount++] = new Book(bookId, title, author);
    }
Book searchBook(String title){
    for(int i=0;i<bookCount;i++){
        if(books[i].title.equals(title)){
            return books[i];
        }
    }
    return null;
}
// public void addBook(int i, String string, String string2) {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'addBook'");
// }
}
class ReturnStack{   //Borrow books to manage recently returned books giving quick access to the ones wanting to borrow again
     Stack<Book> returnedBooks = new Stack<>();

     void pushReturnedBook(Book book){
returnedBooks.push(book);
     }
     Book popReturnedBook()
{
return returnedBooks.pop();
}
}
class User{ // details of user to create waitlist of users when book is unavailable
String name;
int userId;
public User(String name, int id){
    this.name=name;
    this.userId=id;
}
}
class Waitlist{ //queue to create waitlist
Queue<User> wait = new LinkedList<>();
void addUser(User user){
    wait.add(user);
}
User serveNextUser(){
    return wait.poll();
}
}

class BorrowedBooks{ //Linked List for borrowing history 
Book book;
BorrowedBooks next;
public BorrowedBooks(Book book){
this.book=book;
this.next=null;
}
}
class UserBorrowingHistory {
BorrowedBooks head;
void addBorrowedBooks(Book book){
    BorrowedBooks newNode = new BorrowedBooks(book);
    newNode.next = head;
    head=newNode;
}
void removeBorrowedBooks(Book book)
    {
        BorrowedBooks current = head;
        BorrowedBooks previous = null;
        while(current!=null && current.book!=book){
        previous=current;
        current=current.next;
    }
    if (current==null) {
        return;
    }
    if (previous==null) {
        head = current.next;
    }
    else{
        previous.next=current.next;
    }
    }
}




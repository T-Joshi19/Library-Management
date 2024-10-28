import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        ReturnStack returnStack = new ReturnStack();
        Scanner in = new Scanner(System.in);

        User user1 = new User("Tanashvi", 1);
        User user2 = new User("Tanu", 2);

        UserBorrowingHistory user1History = new UserBorrowingHistory();
        UserBorrowingHistory user2History = new UserBorrowingHistory();

        Waitlist waitlist = new Waitlist();
        lib.addBook(101, "Optics", "S. Chand");
        lib.addBook(102, "Plasma", "BittenCourt");
        lib.addBook(103, "Statistical", "Patharia");

        int choice;
        do{
            System.out.println("\nLibrary Management System:");
            System.out.println("1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Borrowing History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            in.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Books in library");
                    for (int i = 0; i < lib.bookCount; i++) {
                        Book book = lib.books[i];
                        System.out.println("ID: "+ book.bookID + ", Title: "+ book.title+",Author:"+book.author+", Available:"+book.isAvailable);
                    }
                    break;
                    case 2:
                    System.out.println("Enter book's title to borrow");
                    String borrowTitle  =in.nextLine();
                    Book bookToBorrow=lib.searchBook(borrowTitle);
                    if (bookToBorrow!=null && bookToBorrow.isAvailable) {
                        bookToBorrow.isAvailable=false;
                        user1History.addBorrowedBooks(bookToBorrow);
                        System.out.println(user1.name+"borrowed"+bookToBorrow.title);
                    }else if(bookToBorrow!=null){
                    System.out.println("Book is currently unavailable. Adding you to the waitlist.");
                    waitlist.addUser(user1);
                    }
                    else{
                        System.out.println("Book not found");
                    }
                    break;
                    case 3:
                    System.out.println("Enter book title to return:");
                    String returnTitle = in.nextLine();
                    Book bookToReturn = lib.searchBook(returnTitle);
                    if (bookToReturn!=null && !bookToReturn.isAvailable) {
                        bookToReturn.isAvailable=true;
                        returnStack.pushReturnedBook(bookToReturn);
                        user1History.removeBorrowedBooks(bookToReturn);
                        System.out.println(user1.name + "returned"+bookToReturn.title);
                        if (!waitlist.wait.isEmpty()) {
                            User nextUser = waitlist.serveNextUser();
                            System.out.println("Notifying"+ nextUser.name+"that"+ bookToReturn.title+"is now available.");
                        }
                    }
                    else{
System.out.println("Book not found or already returned.");
                    }
                    break;
                    case 4:
                    System.out.println(user1.name+"'s Borrowing History:");
                    BorrowedBooks current = user1History.head;
                    while(current!=null){
                        System.out.println("Title:"+ current.book.title+", Author:"+current.book.author);
                        current=current.next;
                    }
                    break;
            case 5:
            System.out.println("Exiting, Please wait...");
            break;
                default:
                System.out.println("Invalid Choice. Please try again!!");
            }
        }  while(choice!=5);
        in.close();
    }

}

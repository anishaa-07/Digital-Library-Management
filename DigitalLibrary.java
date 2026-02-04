import java.util.*;

class Book {
    int id;
    String name;
    String author;
    boolean issued;

    Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.issued = false;
    }
}

public class DigitalLibrary {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();

    static String adminId = "admin";
    static String adminPass = "1234";

    public static void main(String[] args) {

        // Sample Books
        books.add(new Book(1, "Java Basics", "James Gosling"));
        books.add(new Book(2, "DSA Guide", "Narasimha"));
        books.add(new Book(3, "Web Dev", "Tim Lee"));

        int choice;

        do {
            System.out.println("\n===== DIGITAL LIBRARY =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;

                case 2:
                    userMenu();
                    break;

                case 3:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 3);
    }

    static void adminLogin() {

        System.out.print("Enter Admin ID: ");
        String id = sc.next();

        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (id.equals(adminId) && pass.equals(adminPass)) {
            adminMenu();
        } else {
            System.out.println("Invalid Login");
        }
    }

    static void adminMenu() {

        int choice;

        do {
            System.out.println("\n===== ADMIN PANEL =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;

                case 2:
                    showBooks();
                    break;

                case 3:
                    deleteBook();
                    break;

                case 4:
                    System.out.println("Logged out");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);
    }

    static void addBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, name, author));

        System.out.println("Book Added Successfully");
    }

    static void deleteBook() {

        System.out.print("Enter Book ID to Delete: ");
        int id = sc.nextInt();

        boolean found = false;

        for (Book b : books) {
            if (b.id == id) {
                books.remove(b);
                found = true;
                System.out.println("Book Deleted");
                break;
            }
        }

        if (!found) {
            System.out.println("Book Not Found");
        }
    }

    static void userMenu() {

        int choice;

        do {
            System.out.println("\n===== USER PANEL =====");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showBooks();
                    break;

                case 2:
                    issueBook();
                    break;

                case 3:
                    returnBook();
                    break;

                case 4:
                    searchBook();
                    break;

                case 5:
                    System.out.println("Logged out");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 5);
    }

    static void showBooks() {

        System.out.println("\n----- BOOK LIST -----");

        if (books.isEmpty()) {
            System.out.println("No Books Available");
            return;
        }

        for (Book b : books) {
            System.out.println(
                    "ID: " + b.id +
                    " | Name: " + b.name +
                    " | Author: " + b.author +
                    " | Status: " + (b.issued ? "Issued" : "Available")
            );
        }
    }

    static void issueBook() {

        System.out.print("Enter Book ID to Issue: ");
        int id = sc.nextInt();

        for (Book b : books) {

            if (b.id == id) {

                if (!b.issued) {
                    b.issued = true;
                    System.out.println("Book Issued Successfully");
                } else {
                    System.out.println("Book Already Issued");
                }

                return;
            }
        }

        System.out.println("Book Not Found");
    }

    static void returnBook() {

        System.out.print("Enter Book ID to Return: ");
        int id = sc.nextInt();

        for (Book b : books) {

            if (b.id == id) {

                if (b.issued) {
                    b.issued = false;
                    System.out.println("Book Returned Successfully");
                } else {
                    System.out.println("Book Not Issued");
                }

                return;
            }
        }

        System.out.println("Book Not Found");
    }

    static void searchBook() {

        sc.nextLine();
        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Book b : books) {

            if (b.name.toLowerCase().contains(name.toLowerCase())) {

                System.out.println(
                        "ID: " + b.id +
                        " | Name: " + b.name +
                        " | Author: " + b.author +
                        " | Status: " + (b.issued ? "Issued" : "Available")
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("Book Not Found");
        }
    }
}


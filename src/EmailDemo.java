import java.util.Scanner;

public class EmailDemo {
    public static Scanner scanner;
    public static User[] users;
    public static Email[] emails;
    public static Integer index = 0;
    public static Integer indexEmail = 0;


    public static User onlineUser;

    public static void main(String[] args) {
        users = new User[10000];
        emails = new Email[10000];

        while (true) {

            showMainMenu();
            System.out.print("Select the operation => ");
            scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Sign In");   // logging in
        System.out.println("2. Sign Up");   // registering
        System.out.println("0. Exit");
    }

    private static void signUp() {
        scanner = new Scanner(System.in);
        User user = new User();

        System.out.print("Firstname: ");
        String firstname = scanner.next();
        System.out.print("Lastname: ");
        String lastname = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();
        while (true) {
            System.out.print("Retype Password: ");
            String retype_password = scanner.next();
            if (password.equals(retype_password))
                break;
        }

        while (true) {
            System.out.println("Email Address (alex@gmail.com): ");
            String email = scanner.next();
            boolean validateEmail = user.validateEmail(email);
            if (validateEmail) {
                user.setEmail(email);
                break;
            } else {
                System.out.println("Email Invalid");
            }
        }

        if (!firstname.isEmpty() && !lastname.isEmpty() && !password.isEmpty()) {
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setPassword(password);
        }
        users[index++] = user;
        System.out.println("Successfully Registered!\n");
        System.out.println("Will you continue the operation? true<===>false");

       while (true) {
           boolean variable = scanner.hasNextBoolean();
           if (!variable) {
               System.out.println("You have stopped the operation!");
               break;
           }
           else {return;}
       }
    }

    private static void signIn() {
        scanner = new Scanner(System.in);

        System.out.print("Email: ");
        String email = scanner.next();

        for (User user : users) {
            if (user != null) {
                if (user.getEmail().equals(email))
                    onlineUser = user;
            }
        }

        boolean success = false;
        if (onlineUser != null) {
            System.out.print("Password: ");
            String password = scanner.next();
            if (onlineUser.getPassword().equals(password))
                success = true;
        }

        if (success) {
            System.out.println(onlineUser.getFirstName() + ", Welcome To Gmail!");
            showEmailMenu();
        }
    }

    private static void showEmailMenu() {
        scanner = new Scanner(System.in);

        System.out.println("1. Send");
        System.out.println("2. Unread");
        System.out.println("3. Inbox");
        System.out.println("4. Outbox");
        System.out.println("0. Sign Out");

        System.out.print("Select the operation => ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                send();
                break;

            case 2:
                unread();
                break;

            case 3:
                inbox();
                break;

            case 4:
                outbox();
                break;
            case 0:
                return;

        }


}

    private static void unread() {

        for (Email email : emails) {
            if (email != null) {
                if (email.getReceiver().equals(onlineUser)) {
                    System.out.println("Sizga " + email.getSender().getEmail() + " dan yangi xabar keldi.");
                }
            }
        }

        System.out.println("==========================");

        System.out.println("1. Bosh Menu qaytish ");
        System.out.println("2. Email Menu qaytish");

        System.out.println("=========================");
        scanner = new Scanner(System.in);

        System.out.print("Select the operation => ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                break;
            case 2:
                showEmailMenu();
                break;
        }


    }

    private static void outbox() {
        for (Email email : emails) {
            if(email != null){
                if(email.getSender().equals(onlineUser)){
                    System.out.println("------------------------------");
                    System.out.println("Receiver: " + email.getReceiver().getEmail());
                    System.out.println("Title: " + email.getTitle());
                    System.out.println("Message: " + email.getBody());
                }

            }
        }
        System.out.println("======================");

        System.out.println("1. Bosh Menu qaytish ");
        System.out.println("2. Email Menu qaytish");

        System.out.println("===================");
        scanner = new Scanner(System.in);

        System.out.print("Select the operation => ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                break;
            case 2:
                showEmailMenu();
                break;
        }


    }

    private static void inbox() {

        for (Email email : emails) {
            if(email != null){
                if(email.getReceiver().equals(onlineUser)){

                    System.out.println("---------------------------------------");
                    System.out.println("Sender: " + email.getSender().getEmail());
                    System.out.println("Title: " + email.getTitle());
                    System.out.println("Message: " + email.getBody());



                }
            }
        }
        System.out.println("=========================");

        System.out.println("1. Bosh Menu qaytish ");
        System.out.println("2. Email Menu qaytish");

        System.out.println("========================");
        scanner = new Scanner(System.in);

        System.out.print("Select the operation => ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                break;
            case 2:
                showEmailMenu();
                break;
        }


    }

    private static void send() {

        System.out.println("---------->Email Addresses<----------");

        for (User user : users) {
            if(user != null){
                if(user.equals(onlineUser)){
                    System.out.println(user.getEmail());
                }
            }
        }
        System.out.println("-------------------------------------");
        System.out.print("To: ");
        String receiverEmailAddresses = scanner.next();
        User receiver = null;

        for (User user : users) {
            if(user != null){
                if(user.getEmail().equals(receiverEmailAddresses)){
                    receiver = user;
                }
            }
        }

        System.out.print("Subject: ");
        scanner = new Scanner(System.in);
        String subject = scanner.nextLine();
        System.out.print("Message: ");
        scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        emails[indexEmail++] = new Email(subject, message, onlineUser, receiver, true);
        System.out.println("Successfully sent!\n");

        System.out.println("1. Bosh Menu qaytish ");
        System.out.println("2. Email Menu qaytish");
        scanner = new Scanner(System.in);

        System.out.print("Select the operation => ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                break;
            case 2:
                showEmailMenu();
                break;
        }


    }

}

import java.util.*;

class contact {

    private String name;
    private String phno;
    private String email;

    public contact(String name, String phno, String email) {
        this.name = name;
        this.phno = phno;
        this.email = email;
    }

    public String getname() {
        return name;
    }

    public String getphno() {
        return phno;
    }

    public String getemail() {
        return email;
    }

    public boolean equalsIgnoreCase(String val) {
        return false;
    }
}

public class addressbook {
    private contact[] contacts;
    private int count;

    public addressbook(int count) {
        contacts = new contact[count];
        count = 0;
    }

    public void addcontact(contact cont) {
        if (count < contacts.length) {
            contacts[count] = cont;
            count++;
            System.out.println("Contact added successfully!");
        } else
            System.out.println("Address book is full!");
    }

    public void removecontact(String val) {
        int i, flag = 0;
        for (i = 0; i < contacts.length; i++) {
            if (contacts[i] != null && contacts[i].getname().equalsIgnoreCase(val)) {
                flag = 1;
                for (int j = i; j < contacts.length - 1; j++) {
                    contacts[j] = contacts[j + 1];
                    count = count - 1;
                    System.out.println("Contact deleted successfully!\nRemaining contacts: " + count);
                    break;
                }

            }

        }
        if (flag == 0)
            System.out.println("Contact not found!!!");
    }

    public void display() {
        System.out.println("All contacts\n");
        for (int i = 0; i < count; i++) {
            contact c = contacts[i];
            {
                System.out.println("Name: " + c.getname());
                System.out.println("Phone number: " + c.getphno());
                System.out.println("E-mail: " + c.getemail());
                System.out.println();
            }

        }
    }

    public int getcount() {
        return count;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the maximum capacity of address book");
        int c = sc.nextInt();
        addressbook address = new addressbook(c);
        while (true) {
            System.out.println("Enter choice\n");
            System.out.println("1. Add Contact");
            System.out.println("2.Remove Contact");
            System.out.println("3.Display Contacts");
            System.out.println("4. Exit the menu");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    sc.nextLine();
                    if (address.getcount() < c) {
                        System.out.println("\nEnter name:     ");
                        String n = sc.nextLine();
                        System.out.println("Enter phone number:     ");
                        String ph = sc.nextLine();
                        System.out.println("Enter e-mail:      ");
                        String mail = sc.nextLine();
                        contact newcon = new contact(n, ph, mail);
                        address.addcontact(newcon);
                    } else {
                        System.out.println("Address book is full! Cannot add more contacts.");
                    }
                    break;
                case 2:
                    System.out.println("Enter name of the contact to be deleted");
                    sc.nextLine();
                    String delname = sc.nextLine();
                    address.removecontact(delname);
                    break;
                case 3:
                    address.display();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
            }
        }

    }
}

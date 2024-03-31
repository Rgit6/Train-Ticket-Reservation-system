import Book.Passenger;
import Book.TicketBooker;

import javax.lang.model.element.Name;
import java.util.*;

public class Main {

    public static void bookTicket(Passenger p)
    {
        TicketBooker tb = new TicketBooker();
        if (TicketBooker.availableWaitinglist == 0)
        {
            System.out.println("NO TICKETS AVAILABLE RIGHT NOW");
            return;
        }
        if ((p.berthpreference.equals("L") && TicketBooker.availablelowerBerth>0)||
                (p.berthpreference.equals("M") && TicketBooker.availableMiddleBerth>0)||
                (p.berthpreference.equals("U") && TicketBooker.availableUpperBerth>0))
        {
            System.out.println("Preferred Berth Available");
            if (p.berthpreference.equals("L"))
            {
                System.out.println("Lower Berth Given");
                tb.setBookTicket(p,(TicketBooker.lbp.get(0)),"L");
                TicketBooker.lbp.remove(0);
                TicketBooker.availablelowerBerth--;

            }
            else if (p.berthpreference.equals("M"))
            {
                System.out.println("Middle Berth Given");
                tb.setBookTicket(p,(TicketBooker.mbp.get(0)),"M");
                TicketBooker.mbp.remove(0);
                TicketBooker.availableMiddleBerth--;
            }
            else if (p.berthpreference.equals("U"))
            {
                System.out.println("Upper Berth Given");
                tb.setBookTicket(p,(TicketBooker.ubp.get(0)),"U");
                TicketBooker.ubp.remove(0);
                TicketBooker.availableUpperBerth--;
            }
        }
        else if (TicketBooker.availablelowerBerth>0)
        {
            System.out.println("Lower Berth Given");
            tb.setBookTicket(p,(TicketBooker.lbp.get(0)),"L");
            TicketBooker.lbp.remove(0);
            TicketBooker.availablelowerBerth--;
        }
        else if (TicketBooker.availableMiddleBerth>0)
        {
            System.out.println("Middle Berth Given");
            tb.setBookTicket(p,(TicketBooker.mbp.get(0)),"M");
            TicketBooker.mbp.remove(0);
            TicketBooker.availableMiddleBerth--;
        }
        else if (TicketBooker.availableUpperBerth>0)
        {
            System.out.println("Upper Berth Given");
            tb.setBookTicket(p,(TicketBooker.ubp.get(0)),"U");
            TicketBooker.ubp.remove(0);
            TicketBooker.availableUpperBerth--;
        }
        else if (TicketBooker.availableRACTickets>0)
        {
            System.out.println("RAC Available");
            tb.addToRAC(p,(TicketBooker.racp.get(0)),"RAC");
        }
        else if (TicketBooker.availableWaitinglist>0)
        {
            System.out.println("Added to Waiting List");
            tb.addToWaitingList(p,(TicketBooker.wlp.get(0)),"WL");
        }


    }
    static void cancelTickets(int id)
    {
        TicketBooker tb = new TicketBooker();

        if (TicketBooker.passengers.containsKey(id))
        {
            System.out.println("Passenger Details Unknown");
        }
        else
        {
            tb.cancelTickets(id);
        }
    }

    public static void main(String[] args)
    {
     Scanner sc = new Scanner(System.in);
     boolean loop = true;
     while (loop)
     {
         System.out.println(" (1) Book Your Tickets \n (2) Check Available Tickets \n (3) View Booked Tickets \n (4) Cancel Tickets \n (5) Exit");
         int choice = sc.nextInt();
         switch (choice)
         {
             case 1:
             {
                 System.out.println("Enter Passenger Name :");
                 String name = sc.next();
                 System.out.println("Enter the Passengers Age :");
                 int age = sc.nextInt();
                 System.out.println("Enter Your Berth Preference");
                 String berthPreference = sc.next();
                 Passenger p =new Passenger(name, age, berthPreference);
                 bookTicket(p);
             }
             break;
             case 2:
             {
                 System.out.println("Enter Passenger ID to Cancel your Ticket");
                 int id = sc.nextInt();
                 cancelTickets(id);
             }
             break;
             case 3:
             {
                 TicketBooker tb = new TicketBooker();
                 tb.printAvailable();
             }
             break;
             case 4:
             {
                 TicketBooker tb = new TicketBooker();
                 tb.printPassengers();
             }
             break;
             case 5:
             {
                 loop = false;
             }
             break;
             default:
                 break;
         }
     }
    }
}
package Book;

import com.sun.tools.javac.Main;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;
public class TicketBooker
{
    Passenger passengerFromRAC;
    public static int availablelowerBerth = 1;
    public static int availableMiddleBerth = 1;
    public static int availableUpperBerth = 1;
    public static int availableRACTickets = 1;
    public static int availableWaitinglist = 1;

    public static Queue<Integer> waitinglist = new LinkedList<>();
    public static Queue<Integer> raclist = new LinkedList<>();
    public static List<Integer> bookTicketList = new ArrayList<>();


    public static List<Integer> lbp = new ArrayList<>(Arrays.asList(1));
    public static List<Integer> mbp = new ArrayList<>(Arrays.asList(1));
    public static List<Integer> ubp = new ArrayList<>(Arrays.asList(1));
    public static List<Integer> racp = new ArrayList<>(Arrays.asList(1));
    public static List<Integer> wlp = new ArrayList<>(Arrays.asList(1));

    public static Map<Integer, Passenger> passengers = new HashMap<Integer, Passenger>();


    public void setBookTicket(Passenger p, int berthInfo, String allotedBerth)
    {
        p.number = berthInfo;
        p.alloted = allotedBerth;
        passengers.put(p.passengerId,p);
        bookTicketList.add(p.passengerId);
        System.out.println("------------------------------Booked Successfully");
    }

    public void addToRAC(Passenger p, int racInfo, String allotedRAC)
    {
        p.number = racInfo;
        p.alloted = allotedRAC;
        passengers.put(p.passengerId,p);
        raclist.add(p.passengerId);
        availableRACTickets--;
        racp.remove(0);
        System.out.println("----------------------------------added to RAC Successfully");
    }

    public void addToWaitingList(Passenger p, int waitingListInfo, String allotedWL)
    {
        p.number = waitingListInfo;
        p.alloted = allotedWL;
        passengers.put(p.passengerId,p);
        waitinglist.add(p.passengerId);
        availableWaitinglist--;
        wlp.remove(0);
        System.out.println("-------------------------------added to Waiting List Successfully");
    }

    public void cancelTickets(int passengId)
    {
        Passenger p = passengers.get(passengId);

        passengers.remove(passengId);
        System.out.println("-----------------------------------Your Ticket has been cancelled Successfully");
        System.out.println(p);

        bookTicketList.remove(passengId);
        int positionBooked = p.number;
        System.out.println(positionBooked);

        if (p.alloted.equals("L"))
        {
            availablelowerBerth++;
            lbp.add(positionBooked);
        }
        else if (p.alloted.equals("M"))
        {
         availableMiddleBerth++;
         mbp.add(positionBooked);
        }
        else if (p.alloted.equals("U"))
        {
         availableUpperBerth++;
         ubp.add(positionBooked);
        }

        if (raclist.size() > 0)
        {
             passengerFromRAC = passengers.get(raclist.poll());
            int positionRac = passengerFromRAC.number;
            racp.add(positionRac);
            raclist.remove(Integer.valueOf(passengerFromRAC.passengerId));
            availableRACTickets++;

            if (waitinglist.size() > 0)
            {
                Passenger passengerFromWaitingList = passengers.get(waitinglist.poll());
                int positionWL = passengerFromWaitingList.number;
                wlp.add(positionWL);
                waitinglist.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

                 passengerFromWaitingList.number = racp.get(0);
                 passengerFromWaitingList.alloted = "RAC";
                 racp.remove(0);
                 raclist.add(passengerFromWaitingList.passengerId);
                 availableWaitinglist++;
                 availableRACTickets--;
            }

            Methcall.bookTicket(passengerFromRAC);
        }
    }
    public void printAvailable()
    {
        System.out.println("Available Lower Berth " + availablelowerBerth);
        System.out.println("Available Upper Berth " + availableUpperBerth);
        System.out.println("Available Middle Berth " + availableMiddleBerth);
        System.out.println("Available RAC " + availableRACTickets);
        System.out.println("Available Waiting List " + availableWaitinglist);
        System.out.println("---------------------------------");
    }

    public void printPassengers()
    {
        if (passengers.size()==0)
        {
            System.out.println("THERE ARE NO DETAILS ABOUT THE PASSENGERS");
            return;
        }
        for (Passenger p : passengers.values())
        {
            System.out.println("Passenger ID " + p.passengerId);
            System.out.println("Name " + p.name);
            System.out.println("Age " + p.age);
            System.out.println("Status " + p.number + p.alloted);
            System.out.println("-------------------------------");
        }
    }

}

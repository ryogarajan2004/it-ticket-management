//package service;
//
//import model.Ticket;
//import model.TicketStatus;
//import store.DataStore;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import static store.DataStore.tickets;
//
//public class AgentServices {
//    public List<Ticket> assignedTikets() {
//        List<Ticket> ticketList=new ArrayList<>();
//
//        for (Ticket ticket : DataStore.tickets) {
//            if (Objects.equals(ticket.getAssigneeId(), DataStore.currentuser.getId())) {
//
//                ticketList.add(ticket);
//            }
//        }
//        return ticketList;
//    }
//
//    public void viewTicket(Ticket ticket) {
//        System.out.println("Ticket ID: " + ticket.getId());
//        System.out.println("Ticket Reporter: " + ticket.getReporterId());
//        System.out.println("Ticket Title: " + ticket.getTitle());
//        System.out.println("Ticket Description: " + ticket.getDescription());
//        System.out.println("Ticket Status: "+ticket.getStatus());
//        System.out.println("Ticket Category: " + ticket.getCategory());
//        System.out.println("Ticket Notes: " + ticket.getNotes());
//        System.out.println("Ticket Remark: " + ticket.getRemark());
//    }
//
//    public Ticket findTicketById(Long id) {
//        for (Ticket ticket : tickets) {
//            if (Objects.equals(ticket.getId(), id)) {
//                return ticket;
//            }
//        }
//        return null;
//    }
//
//    public boolean changeTicketStatus(Ticket ticket, TicketStatus ticketStatus)
//    {
//        ticket.setStatus(ticketStatus);
//        return true;
//    }
//
//    public boolean addNoteOnTicket(Ticket ticket,String note)
//    {
//        ticket.setNotes(note);
//        return true;
//    }
//
//
//}

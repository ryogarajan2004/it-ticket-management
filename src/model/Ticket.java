package model;

public class Ticket {

    private Long id;
    private  String title;
    private  String description;
    private TicketStatus status;
    private  Long assigneeId; //Agent
    private Long reporterId; // User
    private Category category;
    private  String notes;
    private  String remark;

}

package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket implements Serializable {

    public static final long serialVersionUID = 1L;


    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private Long assigneeId; //Agent
    private Long reporterId; // User
    private Category category;
    private Priority priority;
    private String notes;
    private String remark;
    private Boolean isEscalated;
    private String escalationReason;


    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private LocalDateTime updatedAt;
    private List<String> changeHistory = new ArrayList<>();


    public Ticket() {
        this.status = TicketStatus.OPEN;
        this.priority = Priority.MEDIUM;
        this.isEscalated = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Boolean getEscalated() {
        return isEscalated;
    }

    public void setEscalated(Boolean escalated) {
        isEscalated = escalated;
    }

    public String getEscalationReason() {
        return escalationReason;
    }

    public void setEscalationReason(String escalationReason) {
        this.escalationReason = escalationReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getChangeHistory() {
        return changeHistory;
    }

    public void setChangeHistory(List<String> changeHistory) {
        this.changeHistory = changeHistory;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void escalate(String reason) {
        if (this.isEscalated) return;
        this.isEscalated = true;
        this.escalationReason = reason;
        addHistory("Ticket escalation " + reason);
    }

    public void resolve() {
        if (assigneeId == null) {
            System.out.println("Cannot resolve unassigned ticket");
            return;
        }
        if (this.status == TicketStatus.RESOLVED) return;
        this.status = TicketStatus.RESOLVED;
        this.resolvedAt = LocalDateTime.now();
        addHistory("Ticket resolved:  " + this.resolvedAt);
    }

    public void reassign(Long newAgentId) {
        addHistory("Reassigned from " + this.assigneeId + " to " + newAgentId);
        this.assigneeId = newAgentId;
    }


    private void addHistory(String history) {
        changeHistory.add(LocalDateTime.now() + " - " + history);
        this.updatedAt = LocalDateTime.now();
    }


}

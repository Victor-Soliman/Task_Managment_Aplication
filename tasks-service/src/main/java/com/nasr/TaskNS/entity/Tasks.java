package com.nasr.TaskNS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "status")
    private Status status;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Users assigned;

    public Long getUserId() {
        return assigned.getId();
    }

    public Long setUserId(Long id) {
        return assigned.getId();
    }

    public String getClientUserName() {
        return assigned.getUsername();
    }
    public String setClientUserName(String clientName) {
        return assigned.getUsername();
    }

}

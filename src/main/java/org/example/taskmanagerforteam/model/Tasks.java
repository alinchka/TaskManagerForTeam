package org.example.taskmanagerforteam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    private User assignedTo;
    private LocalDateTime createdAt;

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy'г' HH:mm", new Locale("ru"));
        return createdAt.format(formatter);
    }
    public String getFormattedDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy'г' HH:mm", new Locale("ru"));
        return deadline.format(formatter);
    }
}
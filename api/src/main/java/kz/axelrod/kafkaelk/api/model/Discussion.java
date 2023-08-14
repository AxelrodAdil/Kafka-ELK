package kz.axelrod.kafkaelk.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "discussions")
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discussionId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;
    private String message;

    @Column(name = "post_date")
    private LocalDateTime postDate;
}

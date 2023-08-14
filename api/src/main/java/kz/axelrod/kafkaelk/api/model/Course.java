package kz.axelrod.kafkaelk.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String title;
    private String description;

    @Column(name = "duration_minute")
    private Integer duration;

    @Column(name = "course_discount", precision = 10, scale = 2)
    private BigDecimal course_discount;

    @Column(precision = 10, scale = 2)
    private BigDecimal cost;
}

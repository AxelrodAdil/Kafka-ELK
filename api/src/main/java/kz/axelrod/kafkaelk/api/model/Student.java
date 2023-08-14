package kz.axelrod.kafkaelk.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String firstName;
    private String lastName;
    private Integer age;
    private String email;

    @Column(name = "academic_performance")
    private Integer academicPerformance;

    @Column(precision = 10, scale = 2)
    private BigDecimal rating;

    @Column(name = "personal_discount", precision = 10, scale = 2)
    private BigDecimal personalDiscount;

    @Column(name = "registration_date")
    private LocalDate registrationDate;
}

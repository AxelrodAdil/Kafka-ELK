package kz.axelrod.kafkaelk.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    private String firstName;
    private String lastName;
    private String msisdn;

    @Column(name = "contact_email")
    private String contactEmail;

    private String qualification;
}

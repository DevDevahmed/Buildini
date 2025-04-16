package esprit.microservice1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Inspections {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Identifiant de l'inspection

    @Column(nullable = false)
    private String responsable;  // Responsable de l'inspection

    @Column(nullable = false)
    private String objet;  // Objet de l'inspection (ex: sécurité, qualité, etc.)

    @Column(nullable = false)
    private LocalDate dateInspection;  // Date de l'inspection

    @Column(nullable = false)
    private String resultat;

    @Lob
    @Column(name = "signature", columnDefinition = "LONGBLOB")
    private String signature;

    @ManyToOne
    @JsonIgnore
    Incidents incidents;
}

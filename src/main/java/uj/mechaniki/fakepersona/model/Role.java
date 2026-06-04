package uj.mechaniki.fakepersona.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Action roleAction1;

    private Action roleAction2;

    private Action roleAction3;

    private Action roleAction4;

    private Action roleAction5;

}

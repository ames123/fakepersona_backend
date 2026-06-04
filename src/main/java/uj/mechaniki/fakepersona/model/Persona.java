package uj.mechaniki.fakepersona.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String displayName;

    @ManyToOne
    private Role role;

    private Action freeTime;

    private Action action1;
    private Action action2;
    private Action action3;
    private Action action4;
    private Action action5;

    private boolean freeTimeReady;
    private boolean orderingReady;
    private boolean positionReady;
    private boolean hourEnded;

}

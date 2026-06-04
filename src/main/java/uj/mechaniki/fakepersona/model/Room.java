package uj.mechaniki.fakepersona.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomCode;

    private Gamestate gamestate;

    private int playerCount;

    private String winner;

    private int dayProgression;

    private int timeProgression;

    @ManyToMany
    private Set<Persona> players;

}

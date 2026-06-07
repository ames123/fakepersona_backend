package uj.mechaniki.fakepersona.init;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uj.mechaniki.fakepersona.model.*;
import uj.mechaniki.fakepersona.repository.PersonaRepository;
import uj.mechaniki.fakepersona.repository.RoleRepository;
import uj.mechaniki.fakepersona.repository.RoomRepository;

import java.util.HashSet;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;
    private final PersonaRepository personaRepository;

    @Override
    public void run(String @NonNull ... args) {
        Role role1 = Role.builder()
                .name("Sportowiec")
                .roleAction1(Action.WASH)
                .roleAction2(Action.TRAINING)
                .roleAction3(Action.SPORT)
                .roleAction4(Action.EAT)
                .roleAction5(Action.FREE_TIME)
                .build();
        Role role2 = Role.builder()
                .name("Ogrodnik")
                .roleAction1(Action.GARDEN)
                .roleAction2(Action.SPORT)
                .roleAction3(Action.CLEAN)
                .roleAction4(Action.FREE_TIME)
                .roleAction5(Action.SLEEP)
                .build();
        Role role3 = Role.builder()
                .name("Kucharz")
                .roleAction1(Action.FREE_TIME)
                .roleAction2(Action.COOK)
                .roleAction3(Action.EAT)
                .roleAction4(Action.STOCK)
                .roleAction5(Action.SLEEP)
                .build();
        Role role4 = Role.builder()
                .name("Sprzątacz")
                .roleAction1(Action.CLEAN)
                .roleAction2(Action.CHANGE)
                .roleAction3(Action.FREE_TIME)
                .roleAction4(Action.DISINFECT)
                .roleAction5(Action.MUSIC)
                .build();
        Role role5 = Role.builder()
                .name("Aktor")
                .roleAction1(Action.STOCK)
                .roleAction2(Action.FREE_TIME)
                .roleAction3(Action.SPORT)
                .roleAction4(Action.CHANGE)
                .roleAction5(Action.ACT)
                .build();
        Role role6 = Role.builder()
                .name("Pisarz")
                .roleAction1(Action.READ)
                .roleAction2(Action.FREE_TIME)
                .roleAction3(Action.WRITE)
                .roleAction4(Action.TV)
                .roleAction5(Action.MUSIC)
                .build();
        Role role7 = Role.builder()
                .name("Złodziej")
                .roleAction1(Action.TV)
                .roleAction2(Action.CHANGE)
                .roleAction3(Action.MUSIC)
                .roleAction4(Action.STEAL)
                .roleAction5(Action.FREE_TIME)
                .build();
        Role role8 = Role.builder()
                .name("Lekarz")
                .roleAction1(Action.CHECKUP)
                .roleAction2(Action.WASH)
                .roleAction3(Action.READ)
                .roleAction4(Action.SLEEP)
                .roleAction5(Action.FREE_TIME)
                .build();
        Role role9 = Role.builder()
                .name("Gamer")
                .roleAction1(Action.FREE_TIME)
                .roleAction2(Action.STOCK)
                .roleAction3(Action.EAT)
                .roleAction4(Action.GAME)
                .roleAction5(Action.WASH)
                .build();
        Role role10 = Role.builder()
                .name("Naukowiec")
                .roleAction1(Action.READ)
                .roleAction2(Action.CLEAN)
                .roleAction3(Action.EXPERIMENT)
                .roleAction4(Action.FREE_TIME)
                .roleAction5(Action.TV)
                .build();

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);
        roleRepository.save(role5);
        roleRepository.save(role6);
        roleRepository.save(role7);
        roleRepository.save(role8);
        roleRepository.save(role9);
        roleRepository.save(role10);

        HashSet<Persona> testSet = new HashSet<>();
        Persona testPersona1 = Persona.builder()
                .id(9999L)
                .displayName("TestPersona1")
                .freeTimeReady(false)
                .orderingReady(false)
                .positionReady(false)
                .hourEnded(false)
                .build();
        Persona testPersona2 = Persona.builder()
                .id(9998L)
                .displayName("TestPersona2")
                .freeTimeReady(false)
                .orderingReady(false)
                .positionReady(false)
                .hourEnded(false)
                .build();
        personaRepository.save(testPersona1);
        personaRepository.save(testPersona2);
        testSet.add(testPersona1);
        testSet.add(testPersona2);

        Room testRoom = Room.builder()
                .id(9999L)
                .roomCode("TESTR")
                .gamestate(Gamestate.JOIN)
                .playerCount(2)
                .dayProgression(0)
                .timeProgression(0)
                .players(testSet)
                .build();
        roomRepository.save(testRoom);

        System.out.println("Data initialized");
    }

}

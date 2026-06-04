package uj.mechaniki.fakepersona.init;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uj.mechaniki.fakepersona.model.Action;
import uj.mechaniki.fakepersona.model.Role;
import uj.mechaniki.fakepersona.repository.RoleRepository;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        Role role1 = Role.builder()
                .name("Sportowiec")
                .roleAction1(Action.EAT)
                .roleAction2(Action.SPORT)
                .roleAction3(Action.WASH)
                .roleAction4(Action.FREE_TIME)
                .roleAction5(Action.SLEEP)
                .build();
        Role role2 = Role.builder()
                .name("Ogrodnik")
                .roleAction1(Action.GARDEN)
                .roleAction2(Action.WASH)
                .roleAction3(Action.EAT)
                .roleAction4(Action.FREE_TIME)
                .roleAction5(Action.READ)
                .build();
        Role role3 = Role.builder()
                .name("Kucharz")
                .roleAction1(Action.FREE_TIME)
                .roleAction2(Action.COOK)
                .roleAction3(Action.CLEAN)
                .roleAction4(Action.WASH)
                .roleAction5(Action.TV)
                .build();
        Role role4 = Role.builder()
                .name("Sprzatacz")
                .roleAction1(Action.FREE_TIME)
                .roleAction2(Action.CLEAN)
                .roleAction3(Action.WASH)
                .roleAction4(Action.CLEAN)
                .roleAction5(Action.SLEEP)
                .build();
        Role role5 = Role.builder()
                .name("Dziecko")
                .roleAction1(Action.WASH)
                .roleAction2(Action.SPORT)
                .roleAction3(Action.FREE_TIME)
                .roleAction4(Action.TV)
                .roleAction5(Action.READ)
                .build();
        Role role6 = Role.builder()
                .name("Pisarz")
                .roleAction1(Action.EAT)
                .roleAction2(Action.FREE_TIME)
                .roleAction3(Action.TV)
                .roleAction4(Action.WRITE)
                .roleAction5(Action.SLEEP)
                .build();

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);
        roleRepository.save(role5);
        roleRepository.save(role6);

        System.out.println("Data initialized");
    }

}

package uj.mechaniki.fakepersona.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uj.mechaniki.fakepersona.model.Action;
import uj.mechaniki.fakepersona.model.Role;
import uj.mechaniki.fakepersona.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @GetMapping("/roles/{role}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String role){
        try{
            long roleID = Long.parseLong(role);
            return ResponseEntity.ok(roleRepository.getRoleById(roleID));
        } catch (NumberFormatException e) {
            return ResponseEntity.ok(roleRepository.getRoleByName(role));
        }
    }

    @GetMapping("/actions")
    public ResponseEntity<List<Action>> getActions(){
        return ResponseEntity.ok(Arrays.asList(Action.values()));
    }


}

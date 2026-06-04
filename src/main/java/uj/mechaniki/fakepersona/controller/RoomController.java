package uj.mechaniki.fakepersona.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.mechaniki.fakepersona.model.*;
import uj.mechaniki.fakepersona.repository.PersonaRepository;
import uj.mechaniki.fakepersona.repository.RoleRepository;
import uj.mechaniki.fakepersona.repository.RoomRepository;

import java.util.*;

@RestController
@AllArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;
    private final PersonaRepository personaRepository;
    private final RoleRepository roleRepository;

    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestParam String displayName){
        Persona player = Persona.builder()
                .displayName(displayName)
                .build();
        personaRepository.save(player);

        HashSet<Persona> players = new HashSet<>();
        players.add(player);

        String code = RandomStringUtils.random(6,true,true ).toUpperCase();

        Room room = Room.builder()
                .roomCode(code)
                .players(players)
                .gamestate(Gamestate.JOIN)
                .playerCount(1)
                .dayProgression(0)
                .timeProgression(0)
                .build();
        Room addedRoom = roomRepository.save(room);
        return ResponseEntity.ok(addedRoom);
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getRooms(){
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @GetMapping("/rooms/{code}")
    public ResponseEntity<Room> getRoom(@PathVariable String code){
        return ResponseEntity.ok(roomRepository.getRoomByRoomCode(code));
    }

    @GetMapping("/rooms/{code}/gamestate")
    public ResponseEntity<Gamestate> getGamestate(@PathVariable String code) {
        Room room = roomRepository.getRoomByRoomCode(code);
        return ResponseEntity.ok(room.getGamestate());
    }

    @PostMapping("/rooms/{code}")
    @Transactional
    public ResponseEntity<Room> joinRoom(@PathVariable String code,
                                         @RequestParam String displayName){
        Room room = roomRepository.getRoomByRoomCode(code);

        if(room.getPlayerCount() >= 6) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header("Error","Too many players").build();
        else {
            Persona player = Persona.builder()
                    .displayName(displayName)
                    .orderingReady(false)
                    .freeTimeReady(false)
                    .positionReady(false)
                    .build();
            personaRepository.save(player);

            room.getPlayers().add(player);
            room.setPlayerCount(room.getPlayerCount()+1);
            Room changedRoom = roomRepository.save(room);
            return ResponseEntity.ok(changedRoom);
        }
    }

    @PostMapping("/rooms/{code}/action/start")
    public ResponseEntity<Room> startGame(@PathVariable String code){
        Room room = roomRepository.getRoomByRoomCode(code);

        List<Role> roles = roleRepository.findAll();

        for(Persona p: room.getPlayers()){
            Random rand = new Random();
            int roleI = rand.nextInt(roles.size());
            Role givenRole = roles.get(roleI);
            roles.remove(roleI);
            p.setRole(givenRole);
        }

        room.setGamestate(Gamestate.FREE_TIME_SELECT);
        Room changedRoom = roomRepository.save(room);
        return ResponseEntity.ok(changedRoom);
    }

    @GetMapping("/rooms/{code}/action/freetime")
    public ResponseEntity<List<Action>> freeTimeOffer(@PathVariable String code){
        List<Action> actions = Arrays.asList(Action.values());
        Collections.shuffle(actions);
        return ResponseEntity.ok(actions.subList(1,4));
    }

    @PostMapping("/rooms/{code}/action/freetime")
    @Transactional
    public ResponseEntity<Room> freeTimeSelect(@PathVariable String code,
                                               @RequestParam String displayName,
                                               @RequestParam String actionName){
        Room room = roomRepository.getRoomByRoomCode(code);
        int freeTimeReadyCount = 0;
        for(Persona p: room.getPlayers()){
            if(p.getDisplayName().equals(displayName)) {
                if(p.isFreeTimeReady()) return ResponseEntity.ok(room);
                p.setFreeTimeReady(true);
                freeTimeReadyCount+=1;
                p.setFreeTime(Action.valueOf(actionName));
            } else {
                if(p.isFreeTimeReady()) freeTimeReadyCount+=1;
            }
        }
        if(freeTimeReadyCount == room.getPlayerCount())
            room.setGamestate(Gamestate.TASK_ORDERING);
        Room changedRoom = roomRepository.save(room);
        return ResponseEntity.ok(changedRoom);
    }

    @PostMapping("/rooms/{code}/action/tasks")
    @Transactional
    public ResponseEntity<Room> orderTasks(@PathVariable String code,
                                           @RequestParam String displayName,
                                           @RequestParam String action1,
                                           @RequestParam String action2,
                                           @RequestParam String action3,
                                           @RequestParam String action4,
                                           @RequestParam String action5){
        Room room = roomRepository.getRoomByRoomCode(code);



        int orderingReadyCount = 0;
        for(Persona p: room.getPlayers()){
            if(p.getDisplayName().equals(displayName)) {
                int defaultTasklistSimilarity=0;
                if(Action.valueOf(action1) == p.getRole().getRoleAction1()) defaultTasklistSimilarity+=1;
                if(Action.valueOf(action2) == p.getRole().getRoleAction2()) defaultTasklistSimilarity+=1;
                if(Action.valueOf(action3) == p.getRole().getRoleAction3()) defaultTasklistSimilarity+=1;
                if(Action.valueOf(action4) == p.getRole().getRoleAction4()) defaultTasklistSimilarity+=1;
                if(Action.valueOf(action5) == p.getRole().getRoleAction5()) defaultTasklistSimilarity+=1;
                if(defaultTasklistSimilarity < room.getDayProgression())
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Error","Default tasklist similarity doesn't match day progression").build();

                if(p.isOrderingReady()) return ResponseEntity.ok(room);
                orderingReadyCount+=1;
                p.setOrderingReady(true);

                p.setAction1(Action.valueOf(action1));
                p.setAction2(Action.valueOf(action2));
                p.setAction3(Action.valueOf(action3));
                p.setAction4(Action.valueOf(action4));
                p.setAction5(Action.valueOf(action5));
            } else {
                if(p.isOrderingReady()) orderingReadyCount+=1;
            }
        }
        if(orderingReadyCount == room.getPlayerCount())
            room.setGamestate(Gamestate.POSITION);
        Room changedRoom = roomRepository.save(room);
        return ResponseEntity.ok(changedRoom);
    }

    @PostMapping("/rooms/{code}/action/position")
    @Transactional
    public ResponseEntity<Room> positionPiece(@PathVariable String code,
                                              @RequestParam String displayName){
        Room room = roomRepository.getRoomByRoomCode(code);
        int positionReadyCount = 0;
        for(Persona p: room.getPlayers()) {
            if (p.getDisplayName().equals(displayName)) {
                if(p.isPositionReady()) return ResponseEntity.ok(room);
                p.setPositionReady(true);
                positionReadyCount+=1;
            } else {
                if(p.isPositionReady()) positionReadyCount+=1;
            }
        }
        if(positionReadyCount == room.getPlayerCount())
            room.setGamestate(Gamestate.DEDUCTION);
        Room changedRoom = roomRepository.save(room);
        return ResponseEntity.ok(changedRoom);
    }

    @PostMapping("/rooms/{code}/action/endHour")
    @Transactional
    public ResponseEntity<Room> endHour(@PathVariable String code,
                                       @RequestParam String displayName){
        Room room = roomRepository.getRoomByRoomCode(code);
        int hourEndedCount = 0;
        for(Persona p: room.getPlayers()) {
            if (p.getDisplayName().equals(displayName)) {
                if(p.isHourEnded()) return ResponseEntity.ok(room);
                p.setHourEnded(true);
                hourEndedCount+=1;
            } else {
                if(p.isHourEnded()) hourEndedCount+=1;
            }
        }
        if(hourEndedCount == room.getPlayerCount()){
            room.setTimeProgression(room.getTimeProgression()+1);
            if(room.getTimeProgression() >= 4){
                room.setGamestate(Gamestate.TASK_ORDERING);
                for(Persona p: room.getPlayers()){
                    p.setOrderingReady(false);
                    p.setPositionReady(false);
                    p.setHourEnded(false);
                }
                room.setDayProgression(room.getDayProgression()+1);
                room.setTimeProgression(0);
            } else {
                room.setGamestate(Gamestate.POSITION);
                for(Persona p: room.getPlayers()){
                    p.setPositionReady(false);
                    p.setHourEnded(false);
                }
            }
        }
        Room changedRoom = roomRepository.save(room);
        return ResponseEntity.ok(changedRoom);
    }

    @PostMapping("/rooms/{code}/action/guess")
    public ResponseEntity<Boolean> makeGuess(@PathVariable String code,
                                             @RequestParam String displayName,
                                             @RequestBody Map<String,String> guess){
        Room room = roomRepository.getRoomByRoomCode(code);
        int correctCount=0;
        for(String playerName: guess.keySet()){
            for(Persona p: room.getPlayers()){
                if(p.getDisplayName().equals(playerName) && p.getRole().getName().equals(guess.get(playerName)))
                    correctCount+=1;
            }
        }
        if(correctCount == room.getPlayerCount()){
            room.setGamestate(Gamestate.END);
            room.setWinner(displayName);
            roomRepository.save(room);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}

package employee_details;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController

@RequestMapping("/")

public class EmployeeController {
    private EmployeeRepository userRepo;
    @Autowired
    public EmployeeController(EmployeeRepository userRepo) {
        this.userRepo = userRepo;
    }
    @GetMapping("/user/{userID}")
    public Optional<Employee> getAllUsers(@PathVariable("userID") int id) {

        return userRepo.findById((long) id);
    }
    @GetMapping("/usersList")
    public Iterable<Employee> getUsersList() {

        return userRepo.findAll();
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addOrder(@RequestBody @Valid Employee user) {

        userRepo.save(user);

        return new ResponseEntity<>(OK);

    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public ResponseEntity<?> orderEditForm(@PathVariable (required = false, name = "id") long id) {
        Optional<Employee> optionalOrder = userRepo.findById(id);
        if ( optionalOrder.isPresent() ) {
            return new ResponseEntity<>(userRepo.findById(id), OK);
        } else {
            return new ResponseEntity<>(null, NOT_FOUND);
        }

    }

    @GetMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") long id) {
        try {
            userRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }



}
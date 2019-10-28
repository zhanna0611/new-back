package employee_details;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Data
@Table(name="\"users\"")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    private int id;

    private String username;

    private String password;

    private String fullname;

    private String role;

    private String phone_number;


    public Employee(String username, String password, String fullname, String role, String phone_number) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.phone_number = phone_number;
    }

    public Employee(String not_available, String notAvailable, int i) {
    }
}
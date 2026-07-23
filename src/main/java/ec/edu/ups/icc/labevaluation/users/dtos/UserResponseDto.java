package ec.edu.ups.icc.labevaluation.users.dtos;
import java.util.List;

public record UserResponseDto(  Long id, String fullname, String email, Integer age, Boolean active, List<String> roles) {

}

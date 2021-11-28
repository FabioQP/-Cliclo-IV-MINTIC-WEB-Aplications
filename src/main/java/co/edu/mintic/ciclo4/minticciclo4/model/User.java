package co.edu.mintic.ciclo4.minticciclo4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String identification;
    private String name;
    private Date birthDay;
    private String monthBirthDay;
    private String address;
    private String cellPhone;
    private String email;
    private String password;
    private String zone;
    private String type;
    public void setEmail(String email2) {
    }
    public void setName(String string) {
    }
    public void setPassword(String password2) {
    }
    public String getId() {
        return null;
    }
}

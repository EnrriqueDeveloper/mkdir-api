package com.tecsup.endpint.model;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name = "server_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String paternal_surname;
    private String maternal_surname;
    private Integer dni;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {return  name;}

    public void setName(String name){this.name = name;}

    public String getPaternal_surname() {return  paternal_surname;}

    public void setPaternal_surname(String paternal_surname){this.paternal_surname = paternal_surname;}

    public String getMaternal_surname() {return  maternal_surname;}

    public void setMaternal_surname(String maternal_surname){this.maternal_surname = maternal_surname;}

    public Integer getDni() {return  dni;}

    public void setDni(Integer dni){this.dni = dni;}

    public String getUsername() {return username;}

    public void setUsername(String Username){this.username = Username;}




}

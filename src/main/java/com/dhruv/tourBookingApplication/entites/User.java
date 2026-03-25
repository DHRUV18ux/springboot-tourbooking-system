package com.dhruv.tourBookingApplication.entites;

import com.dhruv.tourBookingApplication.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This is required")
    @Size(min=2,max=20,message = "minimum 2 and maximum 20 characters are allowed")
    private String name;

     @NotBlank(message = "This is required")
    @Email(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9+.-]+.[a-zA-Z]{2,}$",message = "Invalid email")
    @Column(unique = true)
    private String email;

     @NotBlank(message = "This is required")
     @Size(min=5 ,message = "Password must be  at least 5 characters long! ")
    private String password;

     @Pattern(regexp = "^[0-9]{10}$",message = "Contact number must be a valid 10 digit number!!")
     @Column(unique = true)
    private String contactNo;
     
      @Enumerated(EnumType.STRING)
      @Column(nullable = false)
    private Role role;//ROLE_ADMIN OR ROLE_CUSTOMER

    private Boolean enabled=true;

    public User(){
        super();
    }
    
    public User(String name, String email, String password, String contactNo, Role role, Boolean enabled){
        super();
        this.name=name;
        this.email=email;
        this.password=password;
        this.contactNo=contactNo;
        this.role=role;
        this.enabled=enabled;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public void setContactNo(String contactNo){
        this.contactNo=contactNo;
    }
    public String getContactNo(){
        return contactNo;
    }
    public void setRole(Role role){
        this.role=role;
    }
    public Role getRole(){
        return role;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public Boolean getEnabled(){
        return enabled;
    }
     @Override
    public String toString(){
       return "User {"+
             "id :"+id+
             ", name :"+name+
             ",email :"+email+
             ", contactNo :"+contactNo+
             ",role :"+role+
               ",enabled :"+enabled+"}";
    }
}

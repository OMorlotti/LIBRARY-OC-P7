package org.mickael.librarymscustomer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.customer_customer_id_seq")
    @SequenceGenerator(name = "public.customer_customer_id_seq", sequenceName = "public.customer_customer_id_seq", allocationSize = 1)
    @Column(name = "customer_id")
    private Integer id;

    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 50)
    @NotNull(message = "First Name cannot be null")
    private String firstName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Column(name = "phone_number", nullable = false)
    @NotNull(message = "Phone number must be valid and cannot be null")
    private String phoneNumber;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Please enter a password")
    private String password;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "roles", nullable = false)
    private String roles;

    @Column(name = "permissions")
    private String permissions;

    @Column(name = "account_status", nullable = false)
    private boolean active;

    @JsonIgnore
    public List<String> getRolesList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @JsonIgnore
    public List<String> getPermissionsList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Customer{" +
                       "id=" + id +
                       ", lastName='" + lastName + '\'' +
                       ", firstName='" + firstName + '\'' +
                       ", address=" + address +
                       ", phoneNumber='" + phoneNumber + '\'' +
                       ", email='" + email + '\'' +
                       ", password='" + password + '\'' +
                       ", registrationDate=" + registrationDate +
                       ", updateDate=" + updateDate +
                       ", roles='" + roles + '\'' +
                       ", permissions='" + permissions + '\'' +
                       ", active=" + active +
                       '}';
    }
}

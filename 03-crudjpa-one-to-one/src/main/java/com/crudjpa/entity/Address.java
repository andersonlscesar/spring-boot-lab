package com.crudjpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_addresses")
public class Address {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column(name = "id_address")
    private UUID idAddress;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    @OneToOne
    @JoinColumn( name = "id_user", unique = true, referencedColumnName = "id_user")
    @JsonBackReference
    private User user;

    public Address() {
    }

    public Address(UUID idAddress, String street, String number, Instant creationTimestamp, Instant updateTimestamp, User user) {
        this.idAddress = idAddress;
        this.street = street;
        this.number = number;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;
        this.user = user;
    }

    public UUID getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(UUID idAddress) {
        this.idAddress = idAddress;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

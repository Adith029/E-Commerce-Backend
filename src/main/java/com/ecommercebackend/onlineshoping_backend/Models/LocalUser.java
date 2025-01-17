package com.ecommercebackend.onlineshoping_backend.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;





/**
 * User for authentication with our website.
 */
@Entity
@Table(name = "local_user")
public class LocalUser {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  /** The username of the user. */
  @Column(name = "username", nullable = false, unique = true)
  private String username;
  /** The encrypted password of the user. */
  @JsonIgnore
  @Column(name = "password", nullable = false, length = 1000,unique = false)
  private String password;
  /** The email of the user. */
  @Column(name = "email", nullable = false, unique = true, length = 320)
  private String email;
  /** The first name of the user. */
  @Column(name = "first_name", nullable = false,unique = false)
  private String firstName;
  /** The last name of the user. */
  @Column(name = "last_name", nullable = false,unique = false)
  private String lastName;
  /** The addresses associated with the user. */
  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<UserAddress> addresses = new ArrayList<>();
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("id desc")
  private List<VerificationToken> verificationTokens = new ArrayList<>();
  @Column(name = "email_verified",nullable = false)
  private boolean emailVerified =false;

  public List<VerificationToken> getVerificationTokens() {
    return verificationTokens;
  }

  public void setVerificationTokens(List<VerificationToken> verificationTokens) {
    this.verificationTokens = verificationTokens;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  /**
   * Gets the addresses.
   * @return The addresses.
   */
  public List<UserAddress> getAddresses() {
    return addresses;
  }


  /**
   * Sets the addresses.
   * @param addresses The addresses.
   */
  public void setAddresses(List<UserAddress> addresses) {
    this.addresses = addresses;
  }

  /**
   * Gets the last name.
   * @return The last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name.
   * @param lastName The last name.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the first name.
   * @return The first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name.
   * @param firstName The first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the email.
   * @return The email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email.
   * @param email The email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the encrypted password.
   * @return The password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password, this should be pre-encrypted.
   * @param password The password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the username.
   * @return The username.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username.
   * @param username The username.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the id.
   * @return The id.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id.
   * @param id The id.
   */
  public void setId(Long id) {
    this.id = id;
  }

}

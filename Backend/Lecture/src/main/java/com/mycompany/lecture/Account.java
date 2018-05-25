/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lecture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Salma
 */
@XmlRootElement
@Entity
@Path("/accounts")
public class Account implements Serializable {

    @Id
    private String username;
    private String password;

    private int age;
    private boolean loggedIn;
    private String email;
    private String name;

    @NotNull
    private String type;

    private String phone;

    @javax.persistence.Transient
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lecturepu");
    @javax.persistence.Transient
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @GET
    @Path("/sayhello")
    @Produces(MediaType.TEXT_PLAIN)
    public String ViewAll() {
        return "Hello im here";
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAccount(Account account) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @POST
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void test(String test) {
        System.out.print(test);
    }

    @GET
    @Path("/all")
    public List<Account> allTasks() {
        List<Account> accounts = new ArrayList<Account>();
        entityManager.getTransaction().begin();
        accounts = entityManager.createQuery("SELECT um FROM Account um").getResultList();
        entityManager.getTransaction().commit();
        return accounts;
    }

    @POST
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean authenticate(String username, String password) {
        Account acc = entityManager.find(Account.class, username);

        if (acc.getPassword().equals(password)) {
            entityManager.getTransaction().begin();
            acc.setLoggedIn(true);
            entityManager.getTransaction().commit();
            return true;
        }

        return false;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

package example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//
//@Entity
//@Table(name = "cats")
//@NoArgsConstructor
//public class Cat {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private LocalDate birthDate;
//    private String breed;
//    private String color;
//
//    @ManyToOne
//    private Owner owner;
//
//    @ManyToMany
//    @JoinTable(name = "cat_friends", joinColumns = @JoinColumn(name = "cat_id"),
//            inverseJoinColumns = @JoinColumn(name = "friend_id"))
//    private List<Cat> friends = new ArrayList<>();
//
//    public Cat(String барсик, Date date, String breed, String черный, Owner owner) { }
//
//    public Cat(String name, LocalDate birthDate, String breed, String color, Owner owner) {
//        this.name = name;
//        this.birthDate = birthDate;
//        this.breed = breed;
//        this.color = color;
//        this.owner = owner;
//    }
//
//    public Cat(String барсик, Date date, String s, String черный) {
//
//    }
//
////    public Cat(String name, LocalDate birthDate, String breed, Color color, Owner owner, List<Cat> friends) {
////        this.name = name;
////        this.birthDate = birthDate;
////        this.breed = breed;
////        this.color = color;
////        this.owner = owner;
////        this.friends = friends;
////    }
//
//    public void addFriend (Cat catFriend) {
//        this.friends.add(catFriend);
//        catFriend.getFriends().add(this);
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public String getBreed() {
//        return breed;
//    }
//
//    public void setBreed(String breed) {
//        this.breed = breed;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public Owner getOwner() {
//        return owner;
//    }
//
//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }
//
//    public List<Cat> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<Cat> friends) {
//        this.friends = friends;
//    }
//
//    public void setId(long l) {
//
//    }
//}

@Entity
@Table(name = "cates")
@NoArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthDate;
    private String breed;
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany
    @JoinTable(name = "cat_friends", joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Cat> friends = new ArrayList<>();

    public Cat(String барсик, Date date, String breed, String черный, Owner owner) { }

    public Cat(String name, LocalDate birthDate, String breed, String color, Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
    }

    public Cat(String барсик, Date date, String s, String черный) {

    }

//    public Cat(String name, LocalDate birthDate, String breed, Color color, Owner owner, List<Cat> friends) {
//        this.name = name;
//        this.birthDate = birthDate;
//        this.breed = breed;
//        this.color = color;
//        this.owner = owner;
//        this.friends = friends;
//    }

    public void addFriend (Cat catFriend) {
        this.friends.add(catFriend);
        catFriend.getFriends().add(this);
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Cat> getFriends() {
        return friends;
    }

    public void setFriends(List<Cat> friends) {
        this.friends = friends;
    }

    public void setId(long l) {

    }
}

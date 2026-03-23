package example.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//
//@NoArgsConstructor
//@Entity
//@Table(name = "owners")
//public class Owner {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private LocalDate birthDate;
//
//    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
//    private List<Cat> cats = new ArrayList<>();
//
//    public Owner (String марияИванова, Date date) { }
//
//    public Owner(String name, LocalDate birthDate) {
//        this.name = name;
//        this.birthDate = birthDate;
//    }
//
//    public Owner(String name, LocalDate birthDate, List<Cat> cats) {
//        this.name = name;
//        this.birthDate = birthDate;
//        this.cats = cats;
//    }
//
//    public void addCat(Cat cat) {
//        this.cats.add(cat);
//        cat.setOwner(this);
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
//}

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "dateofbirth", nullable = false)
    private Date birthDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> cats = new ArrayList<>();

    public Owner(String name, Date date) {
        this.name = name;
        this.birthDate = date;
    }

    public Owner () {
        this.name = " ";
        this.birthDate = new Date(01-01-1999);
    }

    public void addCat(Cat cat) {
        this.cats.add(cat);
        cat.setOwner(this);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

//package example;
//
//import example.dao.CatDao;
//import example.dao.OwnerDao;
//import example.model.Cat;
//import example.model.Owner;
//import example.interfaces.GenericDao;
//import java.util.Date;
//
//public class Main {
//    public static void main(String[] args) {
//        GenericDao<Cat> catDAO = new CatDao();
//        GenericDao<Owner> ownerDAO = new OwnerDao();
//
//        Owner owner = new Owner("Мария Иванова", new Date(3-1-1999));
//        Cat cat1 = new Cat(
//                "Барсик",
//                new Date(14-4-2024),
//                "Мейн-кун",
//                "Черный",
//                owner
//        );
//        Cat cat2 = new Cat(
//                "Мурзик",
//                new Date(23-9-2024),
//                "Британский",
//                "Карий",
//                owner
//        );
//
//        owner.addCat(cat1);
//
//        cat1.addFriend(cat2);
//
//        ownerDAO.save(owner);
//    }
//}
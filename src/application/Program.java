package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Program {

    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("20/07/1995", fmt);

        SellerDao sellerDao = DaoFactory.createSellerDao();


        System.out.println("Test 1: seller findById");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println();

        System.out.println("Teste 2: seller findByDepartment");
        List<Seller> list = sellerDao.findByDepartment(new Department(2, null));
        list.forEach(System.out::println);
        System.out.println();

        System.out.println("Teste 3: Seller findAll()");
        List<Seller> list2 = sellerDao.findAll();
        for (Seller obj : list2) {
            System.out.println(obj);
        }
        System.out.println();

        System.out.println("Teste 4: Seller insert");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", date, 4000.0, new Department(2, null));
        sellerDao.insert(newSeller);
        System.out.println(newSeller);
    }
}

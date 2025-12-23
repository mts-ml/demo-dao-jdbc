package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import util.DateUtils;

import java.util.List;

public class Program {

    public static void main(String[] args) {
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


//        Comentado para não ficar inserindo toda hora
//        System.out.println("Teste 4: Seller INSERT");
//        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", DateUtils.parseDate("20/07/1995"), 4000.0, new Department(2, null));
//        sellerDao.insert(newSeller);
//        System.out.println(newSeller);


        System.out.println("Teste 5: Seller UPDATE");
        Seller choosenSeller = sellerDao.findById(15);
        choosenSeller.setName("Melzeira");
        sellerDao.update(choosenSeller);
        System.out.println(choosenSeller);
        System.out.println();

        System.out.println("Teste 6: Seller DELETE");
        sellerDao.deleteById(15);
        System.out.println();
    }
}

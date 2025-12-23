package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class DepartmentTests {

    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("Teste 1: department findAll()");
        List<Department> departmentList = departmentDao.findAll();
        departmentList.forEach(System.out::println);
        System.out.println();
    }
}

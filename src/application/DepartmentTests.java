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

        System.out.println("Teste 2: department findById()");
        Department department = departmentDao.findById(4);
        System.out.println(department);
        System.out.println();

        System.out.println("Teste 3: department: insert()");
        Department newDepartmentObj = new Department(null, "Cars");
        departmentDao.insert(newDepartmentObj);
        System.out.println(newDepartmentObj);
        System.out.println();

        System.out.println("Teste 4: department: update()");
        Department chosenDepartment = departmentDao.findById(4);
        chosenDepartment.setName("Motorcycles");

        departmentDao.update(chosenDepartment);
        System.out.println(chosenDepartment);
        System.out.println();
    }
}


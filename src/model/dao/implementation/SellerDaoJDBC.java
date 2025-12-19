package model.dao.implementation;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class SellerDaoJDBC implements SellerDao {

    private Connection connection;


    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Seller findById(int id) {
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT seller.*,department.Name as DepName " +
                        "FROM seller INNER JOIN department " +
                        "ON seller.DepartmentId = department.Id " +
                        "WHERE seller.Id = ?"
        )) {
            ps.setInt(1, 3);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department department = new Department();

                department.setId(rs.getInt("DepartmentId"));
                department.setName(rs.getString("DepName"));

                Seller seller = new Seller();
                seller.setId(rs.getInt("Id"));
                seller.setName(rs.getString("Name"));
                seller.setEmail(rs.getString("Email"));
                seller.setBirthDate(rs.getObject("BirthDate", LocalDate.class));
                seller.setBaseSalary(rs.getDouble("BaseSalary"));
                seller.setDepartment(department);

                return seller;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}

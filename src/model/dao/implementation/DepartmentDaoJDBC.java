package model.dao.implementation;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;


    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(Department obj) {
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO department " +
                        ("Name") +
                        "VALUES " +
                        "(?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, "Cars");

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Unexpected error! No rows affected");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Department findById(int id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM department"
        )) {
            rs = ps.executeQuery();

            List<Department> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department department = map.get(rs.getInt("Id"));

                if (department == null) {
                    department = instanciateDepartment(rs);
                    map.put(rs.getInt("Id"), department);
                }

                list.add(department);
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();

        department.setId(rs.getInt("Id"));
        department.setName(rs.getString("Name"));

        return department;
    }
}

package com.javahelps.mysqlrestservice.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javahelps.mysqlrestservice.data.User;
@Repository
public interface Repositorydata extends JpaRepository<User, String> {

	List<User> findAll();
	List<User> findByEmpID(String EmpID);
}

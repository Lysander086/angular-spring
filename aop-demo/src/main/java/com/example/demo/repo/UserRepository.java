package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    /* !!! 方法名和参数名需要遵守一定的规则, Spring Data JPA 才能自动转化为 JPQL:
     * - 方法名通常包含多个实体属性用于查询,属性之间可以使用 AND 和 OR 连接,也支持Between 、LessThan 、GreaterThan 、Like;
     * - 方法名可以以 findBy 、getBy 、queryBy 开头;
     * - 查询结果可以排序, 方法名包含OrderBy＋属性＋ASC(DESC);
     * - 可以通过Top 、First 来限定查询结果集;
     * */
    public User findByName(String name);

//    @Query("select u from User u where u.name=?1 and u.department.id=?2")
//    public User findUserByDepartment(String name,Integer departmentId);

    @Query(value="select * from user where name=?1 and department_id=?2",nativeQuery=true)
    public User nativeQuery(String name,Integer departmentId);

    @Query(value="select * from user where name=:name and department_id=:departmentId",nativeQuery=true)
    public User nativeQuery2(@Param("name")  String name, @Param("departmentId")  Integer departmentId);

    @Query(value="select department_id,count(1) total from user group by department_id",nativeQuery=true)
    public List<Object[]> queryUserCount();

    @Query(value="select id from user where department_id=?1",nativeQuery=true)
    public List<Integer> queryUserIds(Integer departmentId);

//    @Query(value="select u from User u where u.department.id=?1")
//    public Page<User> queryUsers(Integer departmentId, Pageable page);


}

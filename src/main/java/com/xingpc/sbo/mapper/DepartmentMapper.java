package com.xingpc.sbo.mapper;

import com.xingpc.sbo.entities.Department;
import org.apache.ibatis.annotations.*;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/3/25 11:52
 * @Version: 1.0
 */
@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);

}

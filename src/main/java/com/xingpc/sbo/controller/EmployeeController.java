package com.xingpc.sbo.controller;

import com.xingpc.sbo.dao.DepartmentDao;
import com.xingpc.sbo.dao.EmployeeDao;
import com.xingpc.sbo.entities.Department;
import com.xingpc.sbo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/2/19 21:25
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    EmployeeService employeeService;

    /**
     * 获取所有员工信息
     * @param
     * @Return
     */
    @GetMapping(value = "/getAll")
    public String getAll(Model model) {
        Collection<Employee> emps = employeeDao.getAll();
        //将数据放在请求域中
        model.addAttribute("emps", emps);
        //返回到list.html
        return "emp/list";
    }

    /**
     * 跳转添加员工页面
     * @param
     * @Return
     */
    @GetMapping(value = "/toAdd")
    public String toAdd(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        //跳转页面之前，将部门信息放在请求域中
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    /**
     * 员工添加 SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
     * @param
     * @Return
     */
    @PostMapping
    public String addEmp(Employee employee){
        //来到员工列表页面

        System.out.println("保存的员工信息："+employee);
        //保存员工
        employeeDao.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emp/getAll";
    }

    /**
     * 来到修改页面，查出当前员工，在页面回显
     * @param
     * @Return
     */
    @GetMapping("/toEditPage/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "emp/add";
    }

    /**
     * 员工修改；需要提交员工id；
     * @param
     * @Return
     */
    @PutMapping
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据："+employee);
        employeeDao.save(employee);
        return "redirect:/emp/getAll";
    }

    /**
     * 员工删除
     * @param
     * @Return
     */
    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emp/getAll";
    }

}

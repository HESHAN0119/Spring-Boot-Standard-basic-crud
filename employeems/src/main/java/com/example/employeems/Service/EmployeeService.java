package com.example.employeems.Service;

import com.example.employeems.DTO.EmployeeDTO;
import com.example.employeems.Entity.Employee;
import com.example.employeems.Repo.EmployeeRepo;
import com.example.employeems.Utils.VarList;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;
    public String saveEmployee(EmployeeDTO employeeDTO){
        if (employeeRepo.existsById(employeeDTO.getEmpID())){
            return VarList.RSP_DUPLICATE;
        }else {
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateEmployee(EmployeeDTO employeeDTO){
        if (employeeRepo.existsById(employeeDTO.getEmpID())){
            employeeRepo.save(modelMapper.map(employeeDTO,Employee.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSp_NO_DATA_FOUND;
        }
    }

    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employeeList = employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOList= new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            employeeDTOList.add(employeeDTO);
        }
        // Or we ca use
//        employeeDTOList = employeeList.stream()
//                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
//                .collect(Collectors.toList());
        return employeeDTOList;
    }

    public EmployeeDTO searchEmployee(int empID){
        if (employeeRepo.existsById(empID)){
            return modelMapper.map(employeeRepo.findById(empID).orElse(null),EmployeeDTO.class);
        }else{
            return null;
        }
    }

    public String deleteEmployee(int empID){
        if (employeeRepo.existsById(empID)){
            employeeRepo.deleteById(empID);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSp_NO_DATA_FOUND;
        }
    }
}

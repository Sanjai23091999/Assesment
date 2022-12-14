package com.i2i.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**             
 * <p>
 * Class contains  attributes of 
 * Employee and their getters and setters
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
@MappedSuperclass
public class Employee {
 
    @Column(name = "Employee_Id", columnDefinition="VARCHAR(80)")  
    private String employeeId;
    private String employeeName;
    private LocalDate employeeDateOfBirth;
    private LocalDate employeeDateOfJoin;
    private long mobileNumber;
    private int isActiveEmployee;
        
    public String getEmployeeName() {
        return employeeName;
    }
   
    public String getEmployeeId() {
        return employeeId;
    }

    public long getEmployeeMobileNumber() {
        return mobileNumber;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
   
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public void setEmployeeDateOfBirth(LocalDate employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfJoin() {
        return employeeDateOfJoin;
    }

     public void setEmployeeDateOfJoin(LocalDate employeeDateOfJoin) {
        this.employeeDateOfJoin = employeeDateOfJoin;
    }
 
     public void setEmployeeMobileNumber(long mobileNumber ) {
        this.mobileNumber = mobileNumber;
    }

    public void setActiveEmployee(int isActiveEmployee) {
        this.isActiveEmployee = isActiveEmployee;
    }
  
    public int getActiveEmployee() {
        return isActiveEmployee;
    }

}
    
        
        
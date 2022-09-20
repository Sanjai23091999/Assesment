package com.i2i.dto;

import java.time.LocalDate;
import java.time.Period;

/**             
 * <p>
 * This is the model Dto class which contains
 * getters and setters of employee attributes
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
public class  EmployeeDto{

    private String employeeName;
    private String employeeId;
    private LocalDate employeeDateOfBirth;
    private LocalDate employeeDateOfJoin;
    private long mobileNumber;
        
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
    
    public void setEmployeeDateOfBirth(LocalDate EmployeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public LocalDate getEmployeeDateOfJoin() {
        return employeeDateOfJoin;
    }

    public void setEmployeeDateOfJoin(LocalDate EmployeeDateOfJoin) {
        this.employeeDateOfJoin = employeeDateOfJoin;
    }


    public void setEmployeeMobileNumber(long mobileNumber ) {
        this.mobileNumber = mobileNumber;
    }

    public int getAgeFromDateOfBirth() {
        LocalDate currentDate = LocalDate.now();
        if (employeeDateOfBirth != null) {
        return (Period.between(employeeDateOfBirth, currentDate).getYears());
        } else {
            return 0;
        }
    }

    public int getExperienceFromDateOfJoin() {
        LocalDate currentDate = LocalDate.now();
        if (employeeDateOfJoin != null) {
        return (Period.between(employeeDateOfJoin, currentDate).getYears());
        } else {
        return 0;
        }
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
       
        stringBuilder.append("Name :").append(this.getEmployeeName()).append("\nID :")
                     .append(this.getEmployeeId()).append("\nMobile No :")
                     .append(this.getEmployeeMobileNumber()).append("\nAge :")
                     .append(this.getAgeFromDateOfBirth()).append("\nExperience :")
                     .append(this.getExperienceFromDateOfJoin());
        
        return stringBuilder.toString(); 
    }  
}

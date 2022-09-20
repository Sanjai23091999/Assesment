package com.i2i.util;

import java.time.LocalDate;

/**             
 * <p>
 * Class which used to generate employeeId
 * for Employee
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
public class EmployeeUtil {

    private static final String ID_PREFIX = "I";
    private static  int idCounter;

    /**
     * used to generate id for employee
     * @return employeeId {@link String} id of employee
     */   
    public static String getId(){
        String employeeId = null;        
        LocalDate currentDate = LocalDate.now();  
        int year = currentDate.getYear();
        idCounter++;
        employeeId = (ID_PREFIX+year%100+idCounter); 
        return employeeId;
    }
} 

    

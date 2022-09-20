package com.i2i.dao.impl;

import com.i2i.dao.IEmployeeDao;
import com.i2i.entity.Trainee;
import com.i2i.entity.Trainer;
import com.i2i.entity.Employee;
import com.i2i.util.Connection;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**             
 * <p>
 * Employee dao class used to manipulate details
 * in database
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
public class EmployeeDaoImpl<T extends Employee> implements IEmployeeDao<T> {

    private T employee;

    public EmployeeDaoImpl(T employee) {
        this.employee = employee;
    }

    private static Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    /**
     *used to insert Employee into List
     *@param employee {@link T} the employee object
     *@return {@link void}
     */  
    @Override        
    public  void insertEmployee(T employee) {  
        try(Session session  = Connection.hibernateConnection()) { 
             Transaction transaction = session.beginTransaction(); 
             if (employee instanceof Trainee) {
                 session.save((Trainee)employee);
             } else {
                 session.save((Trainer)employee);
             }
             transaction.commit();    
        } catch(HibernateException hibernateException) {
             logger.error("",hibernateException);
        }
    }

    /**
     * Retrive list of  All Employees  
     * @return {@link List} of {@link T}
     */   
    @Override
    public List<T> retriveAllEmployees() {
            
        List<Employee> employees = null;
        try(Session session  = Connection.hibernateConnection()) { 
            if (employee instanceof Trainee) {        
                TypedQuery query = session.getNamedQuery("retriveAllTrainees");    
                query.setParameter("status",0);
                employees = query.getResultList(); 
            } else {
                TypedQuery query = session.getNamedQuery("retriveAllTrainers");    
                query.setParameter("status",0);
                employees = query.getResultList(); 
            }
        } catch(HibernateException hibernateException) {
             logger.error("",hibernateException);
        }
        return (List<T>)employees;         
    }

    /**
     * Retrive  Employee with his id  
     * @param employeeId {@link String} id of employee  
     * @return {@link void} 
     */   
    @Override
    public  T retriveEmployeeById(String employeeId) {

        Employee selectedEmployee = null;
        try(Session session  = Connection.hibernateConnection()) {  
            if (employee instanceof Trainer) {
                Query query = session.getNamedQuery("retriveTrainerById");    
                query.setParameter("employeeId",employeeId);
                query.setParameter("isActiveEmployee",0); 
                selectedEmployee = (Trainer)query.getSingleResult();
            } else { 
                Query query = session.getNamedQuery("retriveTraineeById");    
                query.setParameter("employeeId",employeeId);
                query.setParameter("isActiveEmployee",0);
               selectedEmployee = (Trainee)query.getSingleResult();
            } 
        } catch(HibernateException hibernateException) {
            logger.error("",hibernateException);
        } catch(NoResultException noResultException) {
            logger.error("",noResultException);
        }                     
        return (T)selectedEmployee;             
    }

    /**
     * used to update  Employee  
     * @param updateEmployee {@link Employee} the employee object 
     * @return {@link void} 
     */   
    @Override
    public void updateEmployee(Employee updateEmployee) {
      
        try(Session session  = Connection.hibernateConnection()) { 
             Transaction transaction = session.beginTransaction(); 
             if (employee instanceof Trainee) {
                 session.update((Trainee)updateEmployee);
             } else {
                 session.update((Trainer)updateEmployee);
             }
             transaction.commit();    
        } catch(HibernateException hibernateException) {
             logger.error("",hibernateException);
        }
    }
}
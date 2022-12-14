package com.i2i.entity;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


/**             
 * <p>
 * Class contains  attributes of 
 * Trainee and their getters and setters
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
@NamedQueries(
    {
    @NamedQuery(  
        name = "retriveTraineeById",  
        query = "from Trainee trainee  where trainee.employeeId = :employeeId AND trainee.isActiveEmployee = :isActiveEmployee" 
    ), 
    @NamedQuery(  
        name = "retriveAllTrainees",  
        query = "from Trainee trainee where trainee.isActiveEmployee = :status"  
    )
    }
)

@Entity
public class Trainee extends Employee{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int TraineeId;

    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER) 
    private List<Trainer> trainers ;
        
    public List<Trainer> getTrainers(){
	return trainers;
    }
                                        
    public void setTrainers(List<Trainer> trainers) {
	this.trainers = trainers;
    }

}
       





















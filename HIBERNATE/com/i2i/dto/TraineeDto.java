package com.i2i.dto;

import java.util.List;

/**             
 * <p>
 * This is the model Dto class which contains
 * getters and setters of Trainee attributes
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
public class TraineeDto extends EmployeeDto{

    private List<TrainerDto> trainersDto;
        
    public List<TrainerDto> getTrainers(){
	return trainersDto;
    }
                                        
    public void setTrainers(List<TrainerDto> trainersDto) {
	this.trainersDto = trainersDto;
    }

    public String toString() {
        return super.toString();
    }  
}
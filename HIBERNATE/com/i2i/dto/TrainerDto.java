package com.i2i.dto;

import java.util.List;

/**             
 * <p>
 * This is the Model Dto class which contains
 * getters and setters of Trainee attributes
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
public class TrainerDto extends EmployeeDto{

    private List<TraineeDto> traineesDto;

    public void setTrainee(List<TraineeDto> traineesDto ) {
	this.traineesDto = traineesDto;
    }

    public List<TraineeDto> getTrainee() {
	return traineesDto;
    }

    public String toString() {
        return super.toString();
    }  
}
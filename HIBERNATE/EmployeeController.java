import com.i2i.dto.EmployeeDto;
import com.i2i.dto.TraineeDto;
import com.i2i.dto.TrainerDto;

import com.i2i.exception.IdDoesNotExistException;
import com.i2i.service.impl.EmployeeServiceImpl;
import com.i2i.service.IEmployeeService;
import com.i2i.util.EmployeeUtil;
import com.i2i.util.Validation;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**             
 * <p>
 * EmployeeController class with the helps of getting inputs 
 * from user and passing to employeeservice. 
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
public class EmployeeController {

    private  Scanner scanner = new Scanner(System.in);
    
    private IEmployeeService<TraineeDto> traineeService = new EmployeeServiceImpl(new TraineeDto());
    private IEmployeeService<TrainerDto> trainerService = new EmployeeServiceImpl(new TrainerDto());

    private static Logger logger = Logger.getLogger(EmployeeController.class);    

    public static void main(String[] args) {

        String log4jPath = "C:\\Users\\LENOVO\\Documents\\HIBERNATE\\log4j\\log4j.properties";
        PropertyConfigurator.configure(log4jPath);

        EmployeeController employeeController = new EmployeeController();
        
        logger.info("Welcome to Ideas2IT Employee management portal!!");    
        employeeController.init();     
    }

    /**
     * used to interact with user
     *
     * @return {@link void}
     */
    public void init() {
        boolean isContinue = true;
        while(isContinue) {
            logger.info("Please enter one of the below options to proceed further");
            logger.info(" Enter 1 for add details \n Enter 2 for display Details \n Enter 3 for Delete details"
                               +" \n Enter 4 for update \n Enter 5 for Associate Trainer or Trainee \n Enter Any other for Exit");                               
            int userDecision = scanner.nextInt();
            switch (userDecision) {
            case 1:
                logger.info(" Enter 1 for add Trainer \n Enter 2 add Trainee");
                int userChoice = scanner.nextInt();
                if (userChoice == 1) {
                    createEmployee(userChoice);
                } else if (userChoice == 2) {
                    createEmployee(userChoice);
                }
                break;

            case 2: 
                logger.info(" Enter 1 for Display All Employee \n Enter 2 for Display specific Employee" );
                int userInput = scanner.nextInt();
                switch (userInput) {
                case 1:
                    scanner.nextLine();
                    logger.info("Enter 1 for Display  All Trainers \n Enter 2 for Display All Trainees ");
                    int userSelect = scanner.nextInt();
                    switch (userSelect) {
                    case 1:
                        displayAllTrainers();
                        break;

                    case 2: 
                        displayAllTrainees();
                        break;
                    }
                    break;
                 case 2:
                     logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                     int userPreference = scanner.nextInt();
                     scanner.nextLine();
                     if (userPreference == 1) {
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         try {
                             displayTrainerById(id);
                         } catch (IdDoesNotExistException e) {
                             System.out.println(e);
                         }
                     } else {
                         logger.info("Please Enter your Employee Id");
                         String id = scanner.nextLine();
                         try {
                             displayTraineeById(id); 
                         } catch (IdDoesNotExistException e) {
                             System.out.println(e);
                         }
                     }                      
                    break;
                 }
                 break;

            case 3:
                logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                int userOption = scanner.nextInt();
                    if (userOption == 1) {
                        scanner.nextLine();
                        logger.info("Please Enter your Employee Id");
                        String id = scanner.nextLine();
                        trainerService.removeEmployeeById(id);
                    } else {
                        scanner.nextLine();
                        logger.info("Please Enter your Employee Id");
                        String id = scanner.nextLine();
                        traineeService.removeEmployeeById(id); 
                    }
                 break;
            
            case 4:
                logger.info(" Enter 1 for  Trainer \n Enter 2  Trainee");
                int userWish = scanner.nextInt();
                    if (userWish == 1) {
                        updateEmployee(userWish);
                    } else if (userWish == 2){
                        updateEmployee(userWish);
                    }
                break;

             case 5:
                 logger.info(" Enter 1 for Associate  Trainee for Trainer \nEnter 2 for Associate Trainer for Trainee");                  
                 int userAssociate = scanner.nextInt();
                 if (userAssociate == 1) {
                     associateEmployee(userAssociate);
                 } else if (userAssociate == 2) {
                     associateEmployee(userAssociate);
                 }
                 break;

            default:
                isContinue = false;
            }
        }
    }
     
    /**
     * used to collect and set employee details
     *

     * @return {@link void}
     */        
    public void createEmployee(int userInput) {
     
        int choice = 0;      
        LocalDate employeDateOfBirth = LocalDate.now(); 
        LocalDate employeeDateOfJoin = LocalDate.now(); 
        Long mobileNumber = 0l; 
   
        scanner.nextLine(); 
        logger.info("Enter Employee Name");
        String employeeName = scanner.nextLine(); 
               
        logger.info("Enter date of birth");    
        while(choice == 0) { 
            String dateOfBirth = scanner.nextLine();
            try{
                employeDateOfBirth = Validation.convertStringToDate(dateOfBirth);
                choice++;
            } catch(DateTimeParseException dateTimeParseException) {
                logger.info(" Invalid "+"Enter the correct value");
            }  catch(ParseException parseException) {
                logger.error("", parseException);
            }               
        }
        
        logger.info("Enter date of Join ");
        while(choice == 1) { 
            String dateOfBirth = scanner.nextLine();
            try{
                employeeDateOfJoin = Validation.convertStringToDate(dateOfBirth);
                choice++;
            } catch(DateTimeParseException dateTimeParseException) {
                logger.info(" Invalid "+"Enter the correct value");
            }  catch(ParseException parseException) {
                logger.error("", parseException);
            }               
        }
        
        logger.info("Enter your mobile Number");
        while(choice == 2) { 
            Long mobile = scanner.nextLong();
            if (Validation.isValidMobileNumber(mobile)) {
                mobileNumber = mobile;
                choice++;
            } else {
                logger.info(" Invalid "+"Enter the correct value");
            }
        }
        String employeeId = EmployeeUtil.getId();
        
        if (userInput == 2) {

            TraineeDto traineeDto = new TraineeDto();

            traineeDto.setEmployeeName(employeeName);
            traineeDto.setEmployeeDateOfBirth(employeDateOfBirth);
            traineeDto.setEmployeeDateOfJoin(employeeDateOfJoin);
            traineeDto.setEmployeeId(employeeId); 
            traineeDto.setEmployeeMobileNumber(mobileNumber);
            traineeService.addEmployee(traineeDto);

        } else if (userInput == 1) {

            TrainerDto trainerDto = new TrainerDto();

            trainerDto.setEmployeeName(employeeName);
            trainerDto.setEmployeeDateOfBirth(employeDateOfBirth);
            trainerDto.setEmployeeDateOfJoin(employeeDateOfJoin);
            trainerDto.setEmployeeId(employeeId); 
            trainerDto.setEmployeeMobileNumber(mobileNumber);
            trainerService.addEmployee(trainerDto);
        }           
    }

    /**
     * used to display trainers details
     *
     * @return {@link void}
     */
    public void displayAllTrainers() {
        if (trainerService.getAllEmployees().size() > 0) {
            for (TrainerDto trainerDto : (List<TrainerDto>)trainerService.getAllEmployees()) {
                logger.info(trainerDto.toString());     
            }
        } else {
            logger.info("No Trainers Exists");
        }
    }

    /**
     * used to display trainees details
     *
     * @return {@link void}
     */
    public void displayAllTrainees() {
        if (traineeService.getAllEmployees().size() > 0) {
            for (TraineeDto traineeDto : (List<TraineeDto>)traineeService.getAllEmployees()) {
                logger.info(traineeDto.toString());
            } 
        } else {
            logger.info("No Trainees Exists");
        }
    } 

    /**
     * used to display Trainee details with employee Id
     *
     * @return {@link void}
     */  
    public void displayTrainerById(String employeeId) throws IdDoesNotExistException {

        TrainerDto trainerDto = trainerService.getEmployeeById(employeeId);      
        if (trainerDto != null) {        
            logger.info(trainerDto.toString()+"\n"+"Trainees List"+"\n"+trainerDto.getTrainee());
        } else {
            throw new IdDoesNotExistException("ID does not exists");
        }
    }

    /**
     * used to display Trainer details with employee Id
     *
     * @return {@link void}
     */
    public void displayTraineeById(String employeeId) throws IdDoesNotExistException {
    
        TraineeDto traineeDto = traineeService.getEmployeeById(employeeId);
        if (traineeDto != null) {            
            logger.info(traineeDto.toString()+"\n"+"Trainers List"+"\n"+traineeDto.getTrainers());           
        } else {
            throw new IdDoesNotExistException("ID does not exists");            
        }
    }

    /**
     * used to update employee details with employee Id
     *
     * @return {void}
     */
    public void updateEmployee(int userUpdate) {
        scanner.nextLine();
        logger.info("Please Enter your EmployeeId");
        String employeeId = scanner.nextLine();
        logger.info("Please Enter your Mobile Number");
        long mobileNumber = scanner.nextInt();

        if (userUpdate == 1) {
           if (traineeService.getEmployeeById(employeeId) != null) {
               trainerService.updateEmployeeById(employeeId, mobileNumber);
           } else {
              logger.info("No Trainee Exists"); 
           }
        } else {
           if (trainerService.getEmployeeById(employeeId) != null) {
               traineeService.updateEmployeeById(employeeId, mobileNumber);
           } else {
               logger.info("No Trainer Exists");
           } 
        }
    }

    /**
     * used to associate trainer or trainee
     *
     * @return {void}
     */
    public void associateEmployee(int userChoice) {
        if (userChoice == 2) {
            List<TrainerDto> trainersDto = new ArrayList<>();
            scanner.nextLine();             
            if (traineeService.getAllEmployees().size() > 0) {
                displayAllTrainees();
                logger.info("Enter the Trainee employeeId");
                String traineeId = scanner.nextLine();
                if (trainerService.getAllEmployees().size() > 0) {
                    displayAllTrainers(); 
                    logger.info("Enter the Trainers Id you want to add");
                    String[] trainerIds = scanner.nextLine().split(",");
                    for (int i = 0; i<trainerIds.length; i++) {
                        trainersDto.add(trainerService.getEmployeeById(trainerIds[i]));
                    } 
                    trainerService.associateEmployee(trainersDto, traineeId);
                } else {
                    logger.info("No Trainers Exists");
                }     
            } else {
                logger.info("No Trainees Exists");
            }     
        } else if (userChoice == 1) {
            List<TraineeDto> traineesDto = new ArrayList<>();
            scanner.nextLine();           
            if (trainerService.getAllEmployees().size() > 0) {
                displayAllTrainers();
                logger.info("Enter the Trainer employeeId ");
                String trainerId = scanner.nextLine();
                if (traineeService.getAllEmployees().size() > 0) {
                    displayAllTrainees();
                    logger.info("Enter the Trainees Id you want to add");                  
                    String[] traineeIds = scanner.nextLine().split(",");
                    for (int i = 0; i<traineeIds.length; i++) {
                        traineesDto.add(traineeService.getEmployeeById(traineeIds[i]));
                    }
                    traineeService.associateEmployee(traineesDto, trainerId);
                } else {
                    logger.info("No Trainees Exist");
                }
            } else {
                logger.info("No Trainers Exist");
            }
        } 
    } 
}


        
    

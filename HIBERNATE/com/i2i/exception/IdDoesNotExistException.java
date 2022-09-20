package com.i2i.exception;

/**             
 * <p>
 * This exception class will be called 
 * when user enters invalid Id
 * </p>
 *
 * @author Sanjai king
 *
 * @version 1
 *
 * @since 2022-07-18
 */
public class IdDoesNotExistException extends Exception {

    public  IdDoesNotExistException (String exception)  {

        super(exception);
    }
}
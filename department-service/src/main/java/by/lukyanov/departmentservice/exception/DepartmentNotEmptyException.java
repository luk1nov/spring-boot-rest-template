package by.lukyanov.departmentservice.exception;

public class DepartmentNotEmptyException extends RuntimeException{
    public DepartmentNotEmptyException() {
    }

    public DepartmentNotEmptyException(String message) {
        super(message);
    }

    public DepartmentNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}

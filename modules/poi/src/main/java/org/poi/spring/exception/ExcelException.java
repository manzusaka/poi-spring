package org.poi.spring.exception;

/**
 * Excel异常
 *
 * @author lisuo
 */
public class ExcelException extends RuntimeException {

    private static final long serialVersionUID = -1864366678451672886L;

    public ExcelException() {
        super();
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }


}

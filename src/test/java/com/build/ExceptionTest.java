package com.build;

import com.exception.ChildException;
import com.exception.ParentException;
import org.junit.Test;

public class ExceptionTest {

    public void method(String arg1, String arg2) throws ParentException {
        if (arg1 == null) {
            throw new ParentException();
        }
        if (arg2 == null) {
            throw new ChildException();
        }
    }


@Test
public void testCatchException() throws ParentException {
        String arg1 = null;
        String arg2 = "";
        method(arg1, arg2);
}
}

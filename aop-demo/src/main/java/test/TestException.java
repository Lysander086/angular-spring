package test;

public class TestException extends Throwable {
    String msg;

    public TestException(String msg) {
        this.msg = msg;
    }
}

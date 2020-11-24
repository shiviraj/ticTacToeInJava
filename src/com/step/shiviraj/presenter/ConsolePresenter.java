package com.step.shiviraj.presenter;

import com.step.shiviraj.io.Printer;
import com.step.shiviraj.io.ResponseReceiver;

public class ConsolePresenter implements Presenter {
    private final Printer printer;
    private final ResponseReceiver responseReceiver;

    public ConsolePresenter(Printer printer, ResponseReceiver responseReceiver) {
        this.printer = printer;
        this.responseReceiver = responseReceiver;
    }

    @Override
    public void print(String str) {
        printer.print(str);

    }

    @Override
    public int getUserMove() {
        return responseReceiver.receive();
    }

}

package com.ksp.demo.exception;

public class OutOfStokException extends Exception {
    public OutOfStokException(String errorMessage, int inventory, int amount){
        super(String.format("%s: current inventory is %d while %d items were requested", errorMessage, inventory, amount));
    }
}

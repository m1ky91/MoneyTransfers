package it.micheledichio.moneytransfers.model;

public class Empty implements Validable {
    @Override
    public boolean isValid() {
        return true;
    }
}

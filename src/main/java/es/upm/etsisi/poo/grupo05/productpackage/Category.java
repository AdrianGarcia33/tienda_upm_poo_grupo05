package es.upm.etsisi.poo.grupo05.productpackage;

public enum Category{
    MERCH(1f), STATIONERY(0.95f), CLOTHES(0.93f), BOOK(0.9f), ELECTRONICS(0.97f);
    private float afterDiscount;
    Category(float afterDiscount){
        this.afterDiscount = afterDiscount;
    }
    public float getAfterDiscount(){
        return afterDiscount;
    }
}


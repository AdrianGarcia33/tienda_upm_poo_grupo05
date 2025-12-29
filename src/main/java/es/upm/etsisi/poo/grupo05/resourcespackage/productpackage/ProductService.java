package es.upm.etsisi.poo.grupo05.resourcespackage.productpackage;

import java.time.LocalDate;

public class ProductService extends Product{

    private static int count = 1;
    private ServiceType serviceType;
    private LocalDate maxDate;

    public ProductService(ServiceType serviceType, LocalDate maxDate) {
        super(count, serviceType.toString(), 0 );
        this.serviceType = serviceType;
        this.maxDate = maxDate;
        count++;
    }

    public String getServiceId() {
        return this.getId() + "S";
    }
    public LocalDate getMaxDate() {
        return maxDate;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }


    @Override
    public float getTotalPrice(int amount) {
        return 0;
    }


    @Override
    public boolean isTemporallyValid() {
        return !LocalDate.now().isAfter(maxDate);
    }


    @Override
    public String toString() {
        return "class:ProductService, id:"+this.getId()+", category:"+serviceType.toString()+", expiration: "+this.maxDate+"}";
    }
}

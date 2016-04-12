package win.leizhang.validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Car {

@NotNull
@NotEmpty
private String manufacturer;

@NotNull
@Size(min = 2, max = 14)
private String licensePlate;

@Min(2)
private int seatCount;

   public Car(String manufacturer, String licencePlate, int seatCount) {
      this.manufacturer = manufacturer;
      this.licensePlate = licencePlate;
      this.seatCount = seatCount;
   }

   // getters and setters ...
}
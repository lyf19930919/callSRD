package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportOrder {

    public String deadline;
    public String intendedVehicle;
    public List<Destination> destinations;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Destination {
        public String locationName;
        public String operation;
        public List<Property> properties;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Property {
            String key;
            String value;
        }
    }

}
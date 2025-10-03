import java.util.*;

// 👤 Customer class
class Customer {
    private final String customerId;
    private final String name;
    private final String phone;

    public Customer(String customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Phone: " + phone;
    }
}

// 🚙 Car class
class Car {
    private String carId;
    private String model;
    private boolean isAvailable;

    public Car(String carId, String model) {
        this.carId = carId;
        this.model = model;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public String getModel() { return model; }
    public boolean isAvailable() { return isAvailable; }

    public void rentOut() { isAvailable = false; }
    public void returnCar() { isAvailable = true; }

    @Override
    public String toString() {
        return "Car ID: " + carId + ", Model: " + model + ", Available: " + isAvailable;
    }
}

// ? RentalAgency class
class RentalAgency {
    private List<Customer> customers = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public boolean rentCar(String customerId, String carId) {
        Customer customer = findCustomer(customerId);
        Car car = findCar(carId);

        if (customer != null && car != null && car.isAvailable()) {
            car.rentOut();
            System.out.println(customer.getName() + " rented " + car.getModel());
            return true;
        }
        System.out.println("Rental failed.");
        return false;
    }

    public boolean returnCar(String carId) {
        Car car = findCar(carId);
        if (car != null && !car.isAvailable()) {
            car.returnCar();
            System.out.println("Car " + car.getModel() + " returned.");
            return true;
        }
        System.out.println("Return failed.");
        return false;
    }

    private Customer findCustomer(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) return c;
        }
        return null;
    }

    private Car findCar(String id) {
        for (Car c : cars) {
            if (c.getCarId().equals(id)) return c;
        }
        return null;
    }

    public void displayInventory() {
        System.out.println("\n--- Car Inventory ---");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public void displayCustomers() {
        System.out.println("\n--- Customer List ---");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}

// Main class
public class CarRentalSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        // Add customers
        agency.addCustomer(new Customer("C001", "Alice", "0712345678"));
        agency.addCustomer(new Customer("C002", "Bob", "0798765432"));

        // Add cars
        agency.addCar(new Car("CAR001", "Toyot corolla"));
        agency.addCar(new Car("CAR002", "Honda Civic"));

        // Display initial data
        agency.displayCustomers();
        agency.displayInventory();

        // Rental operations
        System.out.println("\n--- Rental Operations ---");
        agency.rentCar("C001", "CAR001"); // Success
        agency.rentCar("C002", "CAR001"); // Fail (already rented)

        agency.returnCar("CAR001");       // Success
        agency.rentCar("C002", "CAR001"); // Success

        // Final inventory
        agency.displayInventory();
    }
}
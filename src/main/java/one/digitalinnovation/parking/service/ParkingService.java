package one.digitalinnovation.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import one.digitalinnovation.parking.exception.ModelNotFoundException;
import one.digitalinnovation.parking.exception.OwnerNameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;

import javax.naming.NameNotFoundException;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String createParkingSpot(int lenLetters, int lenNumbers) {
        String Letter_Part = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Number_Part = "0123456789";

        StringBuilder s1 = new StringBuilder(lenLetters);
        StringBuilder s2 = new StringBuilder(lenNumbers);
        for (int i=0; i<lenLetters; i++) {
            int ch = (int)(Letter_Part.length()*Math.random());
            s1.append(Letter_Part.charAt(ch));
        }

        for (int i=0; i<lenNumbers; i++){
            int no = (int)(Number_Part.length()*Math.random());
            s2.append(Number_Part.charAt(no));
        }
        String parkingSpot = (s1 + "-" + s2).toString();
        System.out.println(parkingSpot);

        return parkingSpot;
    }

    public String  generateLicensePlate(int lenLetters, int lenNumbers) {
        String licensePlate = createParkingSpot(lenLetters, lenNumbers);
        return licensePlate;
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() ->
                new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        String ownerName = parkingCreate.getOwnerName();
        String model = parkingCreate.getModel();
        parkingCreate.setEntryDate(LocalDateTime.now());
        String parkingSpot = createParkingSpot(2,2);
        parkingCreate.setParkingSpot(parkingSpot);
        String licensePlate = generateLicensePlate(3,4);
        parkingCreate.setLicensePlate(licensePlate);
        parkingRepository.save(parkingCreate);

        if ((ownerName == null || ownerName.equals("") || ownerName.equals(" ")) ) {
            if (model == null || model.equals("") || model.equals(" ")) {
                throw new ModelNotFoundException(model);
            } else {
                throw new OwnerNameNotFoundException(ownerName);
            }
        } else {
            return parkingCreate;
        }
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setOwnerName(parking.getOwnerName()); // Can't change
        parking.setColor(parkingCreate.getColor()); // Can't change
        parking.setState(parkingCreate.getState()); // Can't change
        parking.setModel(parkingCreate.getModel()); // Can't change
        parking.setLicenseType(parkingCreate.getLicenseType()); // Can't change
        parking.setParkingSpot(createParkingSpot(2,2));
        parking.setLicensePlate(parking.getLicensePlate()); // Same as above generate a new License
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parking.getParkingSpot(); //
        parking.getLicensePlate(); //
        parkingRepository.save(parking);
        return parking;
    }

}

package com.adelarica.carmanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/")
    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return carRepository.findById(id);
    }

    @PostMapping("/")
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        updatedCar.setId(id);
        return carRepository.save(updatedCar);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }
}

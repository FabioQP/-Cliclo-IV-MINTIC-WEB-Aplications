package co.edu.mintic.ciclo4.minticciclo4.service;

import co.edu.mintic.ciclo4.minticciclo4.model.Fragance;
import co.edu.mintic.ciclo4.minticciclo4.repository.FraganceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FraganceService {


    @Autowired
    private FraganceRepository repository;

    public List<Fragance> findAll() {

        var it = repository.findAll();
        var fragances = new ArrayList<Fragance>();
        it.forEach(fragance -> fragances.add(fragance));
        return fragances;
    }

    public Fragance save(Fragance fragance) {
        if(fragance.getReference() == null) {
            return repository.save(fragance);
        } else {
            Optional<Fragance> result = repository.findById(fragance.getReference());
            if(result.isEmpty()) {
                return repository.save(fragance);
            } else {
                return fragance;
            }
        }
    }

    public Fragance update(Fragance fragance) {

        if(fragance.getReference() == null) {
            return repository.save(fragance);
        } else {
            Optional<Fragance> result = repository.findById(fragance.getReference());
            if(result.isPresent()) {

                Fragance existing = result.get();
                existing.setBrand(Optional.of(fragance.getBrand()).orElse(existing.getBrand()));
                existing.setCategory(Optional.of(fragance.getCategory()).orElse(existing.getCategory()));
                existing.setPresentation(Optional.of(fragance.getPresentation()).orElse(existing.getPresentation()));
                existing.setDescription(Optional.of(fragance.getDescription()).orElse(existing.getDescription()));
                existing.setAvailability(Optional.of(fragance.isAvailability()).orElse(existing.isAvailability()));
                existing.setPrice(Optional.of(fragance.getPrice()).orElse(existing.getPrice()));
                existing.setQuantity(Optional.of(fragance.getQuantity()).orElse(existing.getQuantity()));
                existing.setPhotography(Optional.of(fragance.getPhotography()).orElse(existing.getPhotography()));

                return repository.save(existing);
            } else {
                return fragance;
            }
        }
    }

    public boolean delete(String reference) {

        repository.deleteById(reference);
        return true;
    }
}

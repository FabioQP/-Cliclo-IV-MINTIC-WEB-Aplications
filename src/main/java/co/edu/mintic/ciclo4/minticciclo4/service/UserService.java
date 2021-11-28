package co.edu.mintic.ciclo4.minticciclo4.service;

import co.edu.mintic.ciclo4.minticciclo4.model.Fragance;
import co.edu.mintic.ciclo4.minticciclo4.model.User;
import co.edu.mintic.ciclo4.minticciclo4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {

        var it = repository.findAll();
        var users = new ArrayList<User>();
        it.forEach(client -> users.add(client));
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            return repository.save(user);
        } else {
            Optional<User> result = repository.findById(user.getId());
            if(result.isEmpty()) {
                return repository.save(user);
            } else {
                return user;
            }
        }
    }

    public boolean isEmailPresent(String email) {

        if(repository.getUserByEmail(email) != null) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<User> autenticate(String email, String password) {

        Optional<User> user = repository.getUserByEmailAAndPassword(email, password);

        if (user == null) {

            Optional<User> newUser = Optional.of(new User());
            newUser.get().setEmail(email);
            newUser.get().setPassword(password);
            newUser.get().setName("NO DEFINIDO");

            return newUser;
        } else {
            return user;
        }
    }
/*
    public User update(User user) {

        if(user.getId() == null) {
            return repository.save(user);
        } else {
            Optional<User> result = repository.findById(user.getId());
            if(result.isPresent()) {

                User existing = result.get();
                existing.setIdentification(Optional.of(user.getIdentification()).orElse(existing.getIdentification()));
                existing.setCategory(Optional.of(user.getCategory()).orElse(existing.getCategory()));
                existing.setPresentation(Optional.of(user.getPresentation()).orElse(existing.getPresentation()));
                existing.setDescription(Optional.of(user.getDescription()).orElse(existing.getDescription()));
                existing.setAvailability(Optional.of(user.isAvailability()).orElse(existing.isAvailability()));
                existing.setPrice(Optional.of(user.getPrice()).orElse(existing.getPrice()));
                existing.setQuantity(Optional.of(user.getQuantity()).orElse(existing.getQuantity()));
                existing.setPhotography(Optional.of(user.getPhotography()).orElse(existing.getPhotography()));

                return repository.save(existing);
            } else {
                return fragance;
            }
        }
    }
*/
    /*
    public boolean delete(String reference) {

        repository.deleteById(reference);
        return true;
    }
     */
}

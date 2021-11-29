package co.edu.mintic.ciclo4.minticciclo4.service;

import co.edu.mintic.ciclo4.minticciclo4.model.User;
import co.edu.mintic.ciclo4.minticciclo4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

        List<User> users = repository.getUserByEmail(email);

        if( users.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {

        Optional<User> user = repository.getUserByEmailAndPassword(email, password);

        if (!user.isPresent()) {

            Optional<User> newUser = Optional.of(new User());
            return newUser;
        } else {
            return user;
        }
    }

    public User update(User user) {

        if(user.getId() == null) {
            return repository.save(user);
        } else {
            Optional<User> result = repository.findById(user.getId());
            if(result.isPresent()) {

                User existing = result.get();
                existing.setIdentification(user.getIdentification());
                existing.setName(user.getName());
                existing.setBirthDay(user.getBirthDay());
                existing.setMonthBirthDay(user.getMonthBirthDay());
                existing.setAddress(user.getAddress());
                existing.setCellPhone(user.getCellPhone());
                existing.setEmail(user.getEmail());
                existing.setPassword(user.getPassword());
                existing.setZone(user.getZone());
                existing.setType(user.getType());

                return repository.save(existing);
            } else {
                return user;
            }
        }
    }

    public boolean delete(String id) {

        repository.deleteById(id);
        return true;
    }

}

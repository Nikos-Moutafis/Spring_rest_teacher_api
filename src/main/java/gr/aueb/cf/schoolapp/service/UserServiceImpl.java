package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.repository.UserRepository;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(UserDTO userToRegister) {
        User user = userRepository.save(mapUser(userToRegister));
        return user;
    }

    @Override
    public User updateUser(UserDTO userDTO) throws EntityNotFoundException {
        return null;
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {

    }

    @Override
    public User getUserByUsername(String username) throws EntityNotFoundException {
        return null;
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        return null;
    }

    private User mapUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
    }

    @Override
    public boolean usernameAlreadyExists(String username) {
        return false;
    }
}

package via.sep3.group2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
//import via.sep3.group2.models.OrderDTO;
//import via.sep3.group2.models.OrdersDTO;

//import via.sep3.group2.models.UserDTO;
//import via.sep3.group2.models.UserDTO;
import via.sep3.group2.repository.OrderRepository;
import via.sep3.group2.repository.UserRepository;
import via.sep3.group2.shared.UserDTO;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO {

   // @Autowired
    private UserRepository userRepository;
   // private UserDTO userDTO;
    @Autowired
    public UserDAO(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public void createUser(UserDTO userDTO) {
        try {
            userRepository.save(userDTO);
        } catch (Exception e) {
        }
    }
    @Nullable
    public String getRole(String username,String password)  {
             String role=null;
             //////
       try {
            role=userRepository.findByUsernameAndPassword(username,password).getRole();
            } catch (Exception e) {
       // e.printStackTrace();
    }




        /////

        return role;
    }

    public void updateUsernameOfUser(String newUsername,String username){
        userRepository.setUsernameForUser(newUsername,username);
    }
    //public List


   /* public List<OrdersDTO> getAllOrders(UserDTO userDTO)
    {
        return userDTO.getOrders();
    }*/



}


package com.ausy_technologies.demospring.Controller;

import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/addRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {


        Role roleAdded = this.userService.saveRole(role);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","Added new role");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(roleAdded);
    }




    @PostMapping("/addUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userAdded = this.userService.saveUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","Added new user");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded);
    }

    @PostMapping("/addUser2/{idRole}")
    public ResponseEntity<User> saveUser2(@RequestBody User user, @PathVariable int idRole)
    {
        User userAdded2 = this.userService.saveUser2(user,idRole);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","Added new user with a role");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded2);

    }

    @PostMapping("/addUser3/{roleList}")
    public ResponseEntity<User> saveUser3(@RequestBody User user , @PathVariable List<Role> roleList)
    {
        User userAdded3 = this.userService.saveUser3(user,roleList);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","Added new user with a list of roles");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded3);
    }

    @GetMapping("/findRoleBy/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable int id)
    {
        Role role = this.userService.findRoleById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","Found the role with id "+id);
        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(role);

    }

    @GetMapping("/findAllRoles")
    public ResponseEntity<List<Role>>findAllRoles()
    {

        List<Role> roles = userService.findAllRoles();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","Found all roles");
        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(roles);
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> findAllUsers()
    {
       List<User> users = this.userService.findAllUsers();
       HttpHeaders httpHeaders = new HttpHeaders();
       httpHeaders.add("Responded","Found all users");
       return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(users);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id)
    {
        this.userService.deleteUserById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "Deleted the user with id " + id);
        return ResponseEntity.noContent().headers(httpHeaders).build();


    }
    @DeleteMapping("/deleteRoleById/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id){
        this.userService.deleteRoleById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "Deleted the role with id " + id);
        return ResponseEntity.noContent().headers(httpHeaders).build();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User>  updateUser(@RequestBody User user, @PathVariable int id){
        User userToUpdate = userService.findUserById(id);
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setBirthday(user.getBirthday());
        userToUpdate.setRoleList(user.getRoleList());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","User updated successfully");
        this.userService.saveUser(userToUpdate);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userToUpdate);

    }

    @PutMapping("/updateRole/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable int id){
        Role roleToUpdate = userService.findRoleById(id);
        roleToUpdate.setName(role.getName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded","Role updated successfully");
        this.userService.saveRole(roleToUpdate);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(roleToUpdate);

    }
}

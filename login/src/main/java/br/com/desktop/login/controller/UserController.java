package br.com.desktop.login.controller;

import br.com.desktop.login.dto.ResponseErrorDTO;
import br.com.desktop.login.dto.UserAuthorityDTO;
import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.User;
import br.com.desktop.login.service.UserService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService repository) {
        this.service = repository;
    }

    @ApiOperation("Recuperar todos usuários")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso", response = User.class),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @GetMapping()
    public List<User> getAllUsers(){
        return service.findAll();
    }



    @ApiOperation("Recuperar usuário por numero de identificação (id)")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso", response = User.class),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) throws BussinessException {
        return service.findById(id);
    }

    @ApiOperation("Salvar novo usuário")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody User user) throws BussinessException {
        service.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation("Alterar usuário")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) throws BussinessException {
        service.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Deletar usuário")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws BussinessException {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

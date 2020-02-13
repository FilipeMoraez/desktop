package br.com.desktop.login.controller;

import br.com.desktop.login.dto.ResponseErrorDTO;
import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.Role;
import br.com.desktop.login.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @ApiOperation("Recuperar todas as roles")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso", response = Role.class),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @GetMapping()
    public List<Role> getAllRole(){
        return roleService.findAll();
    }

    @ApiOperation("Recuperar role por numero de identificação (id)")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso", response = Role.class),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @GetMapping("/{id}")
    public Role getRole(@PathVariable("id") Long id) throws BussinessException {
        return roleService.findById(id);
    }

    @ApiOperation("Salvar nova role")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @PostMapping()
    public ResponseEntity<?> saveRole(@RequestBody Role role) throws BussinessException {
        roleService.save(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Alterar role")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @PutMapping()
    public ResponseEntity<?> updateRole(@RequestBody Role role) throws BussinessException {
        roleService.update(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Deletar role")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) throws BussinessException {
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

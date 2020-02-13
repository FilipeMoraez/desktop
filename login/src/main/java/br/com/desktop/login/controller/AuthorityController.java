package br.com.desktop.login.controller;

import br.com.desktop.login.dto.ResponseErrorDTO;
import br.com.desktop.login.exception.BussinessException;
import br.com.desktop.login.model.Authority;
import br.com.desktop.login.service.AuthorityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authority")
public class AuthorityController {
    
    private AuthorityService authorityService;
    
    public AuthorityController(AuthorityService authorityService){
        this.authorityService = authorityService;
    }

    @ApiOperation("Recuperar todas as Authoritys")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso", response = Authority.class),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @GetMapping()
    public List<Authority> getAllAuthority(){
        return authorityService.findAll();
    }

    @ApiOperation("Recuperar Authority por numero de identificação (id)")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso", response = Authority.class),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @GetMapping("/{id}")
    public Authority getAuthority(@PathVariable("id") Long id) throws BussinessException {
        return authorityService.findById(id);
    }

    @ApiOperation("Salvar nova Authority")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @PostMapping()
    public ResponseEntity<?> saveAuthority(@RequestBody Authority Authority) throws BussinessException {
        authorityService.save(Authority);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Alterar Authority")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @PutMapping()
    public ResponseEntity<?> updateAuthority(@RequestBody Authority Authority) throws BussinessException {
        authorityService.update(Authority);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("Deletar Authority")
    @ApiResponses(value=
            {@ApiResponse(code = 200, message = "Sucesso"),
                    @ApiResponse(code = 400, message = "Error", response = ResponseErrorDTO.class)}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthority(@PathVariable("id") Long id) throws BussinessException {
        authorityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}

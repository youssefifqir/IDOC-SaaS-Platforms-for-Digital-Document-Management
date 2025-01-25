package ma.zs.univ.zynerator.security.ws.facade;

import io.swagger.v3.oas.annotations.Operation;
import ma.zs.univ.zynerator.controller.AbstractController;
import ma.zs.univ.zynerator.dto.FileTempDto;
import ma.zs.univ.zynerator.security.bean.User;
import ma.zs.univ.zynerator.security.dao.criteria.core.UserCriteria;
import ma.zs.univ.zynerator.security.service.facade.UserService;
import ma.zs.univ.zynerator.security.ws.converter.UserConverter;
import ma.zs.univ.zynerator.security.ws.dto.UserDto;
import ma.zs.univ.zynerator.util.PaginatedList;
import ma.zs.univ.zynerator.security.payload.request.ChangePasswordRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserRest  extends AbstractController<User, UserDto, UserCriteria, UserService, UserConverter> {



    @Operation(summary = "upload one user")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple utilisateurs")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all utilisateurs")
    @GetMapping("")
    public ResponseEntity<List<UserDto>> findAll() throws Exception {
        return super.findAll();
    }


    @Operation(summary = "Finds an optimized list of all utilisateurs")
    @GetMapping("optimized")
    public ResponseEntity<List<UserDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @Operation(summary = "Finds a user by email")
    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        return super.findByReferenceEntity(new User(email));
    }

    @Operation(summary = "Saves the specified  user")
    @PostMapping("")
    public ResponseEntity<UserDto> save(@RequestBody UserDto dto) throws Exception {
        ResponseEntity<UserDto>save = super.save(dto);
        return save;
    }

    @Operation(summary = "Updates the specified  user")
    @PutMapping("")
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of user")
    @PostMapping("multiple")
    public ResponseEntity<List<UserDto>> delete(@RequestBody List<UserDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified user")
    @DeleteMapping("")
    public ResponseEntity<UserDto> delete(@RequestBody UserDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified user")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple utilisateurs by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }

    @Operation(summary = "Finds a user by username")
    @GetMapping("user-name/{username}")
    public ResponseEntity<UserDto> findByUserName(@PathVariable String username) {
        User user = service.findByUsername(username);
        UserDto userDto = converter.toDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @Operation(summary = "Finds a user and associated list by id")
    @GetMapping("id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return super.findWithAssociatedLists(id);
    }

    @Operation(summary = "Finds utilisateurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<UserDto>> findByCriteria(@RequestBody UserCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated utilisateurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody UserCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports utilisateurs by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody UserCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets user data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody UserCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }
    @GetMapping("/username/{username}")
    public User findByUsernameWithRoles(@PathVariable String username) {
        return service.findByUsernameWithRoles(username);
    }

    @DeleteMapping("/username/{username}")
    public int deleteByUsername(@PathVariable String username) {
        return service.deleteByUsername(username);
    }

    @PostMapping("/changePassword")
    public  boolean changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return service.changePassword(changePasswordRequest.getUsername(), changePasswordRequest.getPassword());
    }

    @GetMapping("/user/{username}")
    public User findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }


    public UserRest (UserService service, UserConverter converter) {
        super(service, converter);
    }




}

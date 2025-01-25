package  ma.zs.univ.ws.facade.utilisateur.abonne;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.abonne.Abonne;
import ma.zs.univ.dao.criteria.core.abonne.AbonneCriteria;
import ma.zs.univ.service.facade.utilisateur.abonne.AbonneUtilisateurService;
import ma.zs.univ.ws.converter.abonne.AbonneConverter;
import ma.zs.univ.ws.dto.abonne.AbonneDto;
import ma.zs.univ.zynerator.controller.AbstractController;
import ma.zs.univ.zynerator.dto.AuditEntityDto;
import ma.zs.univ.zynerator.util.PaginatedList;


import ma.zs.univ.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.univ.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.univ.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/utilisateur/abonne/")
public class AbonneRestUtilisateur {




    @Operation(summary = "Finds a list of all abonnes")
    @GetMapping("")
    public ResponseEntity<List<AbonneDto>> findAll() throws Exception {
        ResponseEntity<List<AbonneDto>> res = null;
        List<Abonne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AbonneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a abonne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AbonneDto> findById(@PathVariable Long id) {
        Abonne t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AbonneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  abonne")
    @PostMapping("")
    public ResponseEntity<AbonneDto> save(@RequestBody AbonneDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Abonne myT = converter.toItem(dto);
            Abonne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AbonneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  abonne")
    @PutMapping("")
    public ResponseEntity<AbonneDto> update(@RequestBody AbonneDto dto) throws Exception {
        ResponseEntity<AbonneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Abonne t = service.findById(dto.getId());
            converter.copy(dto,t);
            Abonne updated = service.update(t);
            AbonneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of abonne")
    @PostMapping("multiple")
    public ResponseEntity<List<AbonneDto>> delete(@RequestBody List<AbonneDto> dtos) throws Exception {
        ResponseEntity<List<AbonneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Abonne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified abonne")
    @DeleteMapping("")
    public ResponseEntity<AbonneDto> delete(@RequestBody AbonneDto dto) throws Exception {
		ResponseEntity<AbonneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Abonne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified abonne")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple abonnes by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }


    @Operation(summary = "find by entiteAdministrative id")
    @GetMapping("entiteAdministrative/id/{id}")
    public List<AbonneDto> findByEntiteAdministrativeId(@PathVariable Long id){
        return findDtos(service.findByEntiteAdministrativeId(id));
    }
    @Operation(summary = "delete by entiteAdministrative id")
    @DeleteMapping("entiteAdministrative/id/{id}")
    public int deleteByEntiteAdministrativeId(@PathVariable Long id){
        return service.deleteByEntiteAdministrativeId(id);
    }

    @Operation(summary = "Finds a abonne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AbonneDto> findWithAssociatedLists(@PathVariable Long id) {
        Abonne loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AbonneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds abonnes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AbonneDto>> findByCriteria(@RequestBody AbonneCriteria criteria) throws Exception {
        ResponseEntity<List<AbonneDto>> res = null;
        List<Abonne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AbonneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated abonnes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AbonneCriteria criteria) throws Exception {
        List<Abonne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AbonneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets abonne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AbonneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AbonneDto> findDtos(List<Abonne> list){
        converter.initObject(true);
        List<AbonneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AbonneDto> getDtoResponseEntity(AbonneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private AbonneUtilisateurService service;
    @Autowired private AbonneConverter converter;





}

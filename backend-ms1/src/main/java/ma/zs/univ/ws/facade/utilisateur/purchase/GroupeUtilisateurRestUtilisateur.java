package  ma.zs.univ.ws.facade.utilisateur.purchase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.purchase.GroupeUtilisateur;
import ma.zs.univ.dao.criteria.core.purchase.GroupeUtilisateurCriteria;
import ma.zs.univ.service.facade.utilisateur.purchase.GroupeUtilisateurUtilisateurService;
import ma.zs.univ.ws.converter.purchase.GroupeUtilisateurConverter;
import ma.zs.univ.ws.dto.purchase.GroupeUtilisateurDto;
import ma.zs.univ.zynerator.controller.AbstractController;
import ma.zs.univ.zynerator.dto.AuditEntityDto;
import ma.zs.univ.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.univ.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.univ.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/utilisateur/groupeUtilisateur/")
public class GroupeUtilisateurRestUtilisateur {




    @Operation(summary = "Finds a list of all groupeUtilisateurs")
    @GetMapping("")
    public ResponseEntity<List<GroupeUtilisateurDto>> findAll() throws Exception {
        ResponseEntity<List<GroupeUtilisateurDto>> res = null;
        List<GroupeUtilisateur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<GroupeUtilisateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a groupeUtilisateur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<GroupeUtilisateurDto> findById(@PathVariable Long id) {
        GroupeUtilisateur t = service.findById(id);
        if (t != null) {
            converter.init(true);
            GroupeUtilisateurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  groupeUtilisateur")
    @PostMapping("")
    public ResponseEntity<GroupeUtilisateurDto> save(@RequestBody GroupeUtilisateurDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            GroupeUtilisateur myT = converter.toItem(dto);
            GroupeUtilisateur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                GroupeUtilisateurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  groupeUtilisateur")
    @PutMapping("")
    public ResponseEntity<GroupeUtilisateurDto> update(@RequestBody GroupeUtilisateurDto dto) throws Exception {
        ResponseEntity<GroupeUtilisateurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            GroupeUtilisateur t = service.findById(dto.getId());
            converter.copy(dto,t);
            GroupeUtilisateur updated = service.update(t);
            GroupeUtilisateurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of groupeUtilisateur")
    @PostMapping("multiple")
    public ResponseEntity<List<GroupeUtilisateurDto>> delete(@RequestBody List<GroupeUtilisateurDto> dtos) throws Exception {
        ResponseEntity<List<GroupeUtilisateurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<GroupeUtilisateur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified groupeUtilisateur")
    @DeleteMapping("")
    public ResponseEntity<GroupeUtilisateurDto> delete(@RequestBody GroupeUtilisateurDto dto) throws Exception {
		ResponseEntity<GroupeUtilisateurDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            GroupeUtilisateur t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified groupeUtilisateur")
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
    @Operation(summary = "Delete multiple groupeUtilisateurs by ids")
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


    @Operation(summary = "find by utilisateur id")
    @GetMapping("utilisateur/id/{id}")
    public List<GroupeUtilisateurDto> findByUtilisateurId(@PathVariable Long id){
        return findDtos(service.findByUtilisateurId(id));
    }
    @Operation(summary = "delete by utilisateur id")
    @DeleteMapping("utilisateur/id/{id}")
    public int deleteByUtilisateurId(@PathVariable Long id){
        return service.deleteByUtilisateurId(id);
    }

    @Operation(summary = "Finds a groupeUtilisateur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<GroupeUtilisateurDto> findWithAssociatedLists(@PathVariable Long id) {
        GroupeUtilisateur loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        GroupeUtilisateurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds groupeUtilisateurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<GroupeUtilisateurDto>> findByCriteria(@RequestBody GroupeUtilisateurCriteria criteria) throws Exception {
        ResponseEntity<List<GroupeUtilisateurDto>> res = null;
        List<GroupeUtilisateur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<GroupeUtilisateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated groupeUtilisateurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody GroupeUtilisateurCriteria criteria) throws Exception {
        List<GroupeUtilisateur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<GroupeUtilisateurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets groupeUtilisateur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody GroupeUtilisateurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<GroupeUtilisateurDto> findDtos(List<GroupeUtilisateur> list){
        converter.initObject(true);
        List<GroupeUtilisateurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<GroupeUtilisateurDto> getDtoResponseEntity(GroupeUtilisateurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private GroupeUtilisateurUtilisateurService service;
    @Autowired private GroupeUtilisateurConverter converter;





}

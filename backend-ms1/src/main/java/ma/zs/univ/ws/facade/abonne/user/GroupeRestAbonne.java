package  ma.zs.univ.ws.facade.abonne.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.user.Groupe;
import ma.zs.univ.dao.criteria.core.user.GroupeCriteria;
import ma.zs.univ.service.facade.abonne.user.GroupeAbonneService;
import ma.zs.univ.ws.converter.user.GroupeConverter;
import ma.zs.univ.ws.dto.user.GroupeDto;
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
@RequestMapping("/api/abonne/groupe/")
public class GroupeRestAbonne {




    @Operation(summary = "Finds a list of all groupes")
    @GetMapping("")
    public ResponseEntity<List<GroupeDto>> findAll() throws Exception {
        ResponseEntity<List<GroupeDto>> res = null;
        List<Groupe> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<GroupeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all groupes")
    @GetMapping("optimized")
    public ResponseEntity<List<GroupeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<GroupeDto>> res = null;
        List<Groupe> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<GroupeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a groupe by id")
    @GetMapping("id/{id}")
    public ResponseEntity<GroupeDto> findById(@PathVariable Long id) {
        Groupe t = service.findById(id);
        if (t != null) {
            converter.init(true);
            GroupeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a groupe by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<GroupeDto> findByLibelle(@PathVariable String libelle) {
	    Groupe t = service.findByReferenceEntity(new Groupe(libelle));
        if (t != null) {
            converter.init(true);
            GroupeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  groupe")
    @PostMapping("")
    public ResponseEntity<GroupeDto> save(@RequestBody GroupeDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Groupe myT = converter.toItem(dto);
            Groupe t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                GroupeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  groupe")
    @PutMapping("")
    public ResponseEntity<GroupeDto> update(@RequestBody GroupeDto dto) throws Exception {
        ResponseEntity<GroupeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Groupe t = service.findById(dto.getId());
            converter.copy(dto,t);
            Groupe updated = service.update(t);
            GroupeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of groupe")
    @PostMapping("multiple")
    public ResponseEntity<List<GroupeDto>> delete(@RequestBody List<GroupeDto> dtos) throws Exception {
        ResponseEntity<List<GroupeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Groupe> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified groupe")
    @DeleteMapping("")
    public ResponseEntity<GroupeDto> delete(@RequestBody GroupeDto dto) throws Exception {
		ResponseEntity<GroupeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Groupe t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified groupe")
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
    @Operation(summary = "Delete multiple groupes by ids")
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
    public List<GroupeDto> findByUtilisateurId(@PathVariable Long id){
        return findDtos(service.findByUtilisateurId(id));
    }
    @Operation(summary = "delete by utilisateur id")
    @DeleteMapping("utilisateur/id/{id}")
    public int deleteByUtilisateurId(@PathVariable Long id){
        return service.deleteByUtilisateurId(id);
    }

    @Operation(summary = "Finds a groupe and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<GroupeDto> findWithAssociatedLists(@PathVariable Long id) {
        Groupe loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        GroupeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds groupes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<GroupeDto>> findByCriteria(@RequestBody GroupeCriteria criteria) throws Exception {
        ResponseEntity<List<GroupeDto>> res = null;
        List<Groupe> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<GroupeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated groupes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody GroupeCriteria criteria) throws Exception {
        List<Groupe> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<GroupeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets groupe data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody GroupeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<GroupeDto> findDtos(List<Groupe> list){
        converter.initList(false);
        converter.initObject(true);
        List<GroupeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<GroupeDto> getDtoResponseEntity(GroupeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private GroupeAbonneService service;
    @Autowired private GroupeConverter converter;





}

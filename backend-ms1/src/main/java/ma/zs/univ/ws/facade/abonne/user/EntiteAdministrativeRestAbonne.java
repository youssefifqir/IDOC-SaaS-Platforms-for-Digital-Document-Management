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

import ma.zs.univ.bean.core.user.EntiteAdministrative;
import ma.zs.univ.dao.criteria.core.user.EntiteAdministrativeCriteria;
import ma.zs.univ.service.facade.abonne.user.EntiteAdministrativeAbonneService;
import ma.zs.univ.ws.converter.user.EntiteAdministrativeConverter;
import ma.zs.univ.ws.dto.user.EntiteAdministrativeDto;
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
@RequestMapping("/api/abonne/entiteAdministrative/")
public class EntiteAdministrativeRestAbonne {




    @Operation(summary = "Finds a list of all entiteAdministratives")
    @GetMapping("")
    public ResponseEntity<List<EntiteAdministrativeDto>> findAll() throws Exception {
        ResponseEntity<List<EntiteAdministrativeDto>> res = null;
        List<EntiteAdministrative> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EntiteAdministrativeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all entiteAdministratives")
    @GetMapping("optimized")
    public ResponseEntity<List<EntiteAdministrativeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EntiteAdministrativeDto>> res = null;
        List<EntiteAdministrative> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EntiteAdministrativeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a entiteAdministrative by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EntiteAdministrativeDto> findById(@PathVariable Long id) {
        EntiteAdministrative t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EntiteAdministrativeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a entiteAdministrative by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EntiteAdministrativeDto> findByLibelle(@PathVariable String libelle) {
	    EntiteAdministrative t = service.findByReferenceEntity(new EntiteAdministrative(libelle));
        if (t != null) {
            converter.init(true);
            EntiteAdministrativeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  entiteAdministrative")
    @PostMapping("")
    public ResponseEntity<EntiteAdministrativeDto> save(@RequestBody EntiteAdministrativeDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            EntiteAdministrative myT = converter.toItem(dto);
            EntiteAdministrative t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EntiteAdministrativeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  entiteAdministrative")
    @PutMapping("")
    public ResponseEntity<EntiteAdministrativeDto> update(@RequestBody EntiteAdministrativeDto dto) throws Exception {
        ResponseEntity<EntiteAdministrativeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EntiteAdministrative t = service.findById(dto.getId());
            converter.copy(dto,t);
            EntiteAdministrative updated = service.update(t);
            EntiteAdministrativeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of entiteAdministrative")
    @PostMapping("multiple")
    public ResponseEntity<List<EntiteAdministrativeDto>> delete(@RequestBody List<EntiteAdministrativeDto> dtos) throws Exception {
        ResponseEntity<List<EntiteAdministrativeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<EntiteAdministrative> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified entiteAdministrative")
    @DeleteMapping("")
    public ResponseEntity<EntiteAdministrativeDto> delete(@RequestBody EntiteAdministrativeDto dto) throws Exception {
		ResponseEntity<EntiteAdministrativeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            EntiteAdministrative t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified entiteAdministrative")
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
    @Operation(summary = "Delete multiple entiteAdministratives by ids")
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


    @Operation(summary = "find by chef id")
    @GetMapping("chef/id/{id}")
    public List<EntiteAdministrativeDto> findByChefId(@PathVariable Long id){
        return findDtos(service.findByChefId(id));
    }
    @Operation(summary = "delete by chef id")
    @DeleteMapping("chef/id/{id}")
    public int deleteByChefId(@PathVariable Long id){
        return service.deleteByChefId(id);
    }
    @Operation(summary = "find by entiteAdministrativeType id")
    @GetMapping("entiteAdministrativeType/id/{id}")
    public List<EntiteAdministrativeDto> findByEntiteAdministrativeTypeId(@PathVariable Long id){
        return findDtos(service.findByEntiteAdministrativeTypeId(id));
    }
    @Operation(summary = "delete by entiteAdministrativeType id")
    @DeleteMapping("entiteAdministrativeType/id/{id}")
    public int deleteByEntiteAdministrativeTypeId(@PathVariable Long id){
        return service.deleteByEntiteAdministrativeTypeId(id);
    }

    @Operation(summary = "Finds a entiteAdministrative and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EntiteAdministrativeDto> findWithAssociatedLists(@PathVariable Long id) {
        EntiteAdministrative loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EntiteAdministrativeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds entiteAdministratives by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EntiteAdministrativeDto>> findByCriteria(@RequestBody EntiteAdministrativeCriteria criteria) throws Exception {
        ResponseEntity<List<EntiteAdministrativeDto>> res = null;
        List<EntiteAdministrative> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EntiteAdministrativeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated entiteAdministratives by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EntiteAdministrativeCriteria criteria) throws Exception {
        List<EntiteAdministrative> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EntiteAdministrativeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets entiteAdministrative data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EntiteAdministrativeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EntiteAdministrativeDto> findDtos(List<EntiteAdministrative> list){
        converter.initObject(true);
        List<EntiteAdministrativeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EntiteAdministrativeDto> getDtoResponseEntity(EntiteAdministrativeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EntiteAdministrativeAbonneService service;
    @Autowired private EntiteAdministrativeConverter converter;





}

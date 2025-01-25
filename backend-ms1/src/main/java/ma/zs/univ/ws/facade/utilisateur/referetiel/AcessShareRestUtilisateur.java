package  ma.zs.univ.ws.facade.utilisateur.referetiel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.referetiel.AcessShare;
import ma.zs.univ.dao.criteria.core.referetiel.AcessShareCriteria;
import ma.zs.univ.service.facade.utilisateur.referetiel.AcessShareUtilisateurService;
import ma.zs.univ.ws.converter.referetiel.AcessShareConverter;
import ma.zs.univ.ws.dto.referetiel.AcessShareDto;
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
@RequestMapping("/api/utilisateur/acessShare/")
public class AcessShareRestUtilisateur {




    @Operation(summary = "Finds a list of all acessShares")
    @GetMapping("")
    public ResponseEntity<List<AcessShareDto>> findAll() throws Exception {
        ResponseEntity<List<AcessShareDto>> res = null;
        List<AcessShare> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AcessShareDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all acessShares")
    @GetMapping("optimized")
    public ResponseEntity<List<AcessShareDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AcessShareDto>> res = null;
        List<AcessShare> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AcessShareDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a acessShare by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AcessShareDto> findById(@PathVariable Long id) {
        AcessShare t = service.findById(id);
        if (t != null) {
            AcessShareDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a acessShare by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<AcessShareDto> findByLibelle(@PathVariable String libelle) {
	    AcessShare t = service.findByReferenceEntity(new AcessShare(libelle));
        if (t != null) {
            AcessShareDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  acessShare")
    @PostMapping("")
    public ResponseEntity<AcessShareDto> save(@RequestBody AcessShareDto dto) throws Exception {
        if(dto!=null){
            AcessShare myT = converter.toItem(dto);
            AcessShare t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AcessShareDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  acessShare")
    @PutMapping("")
    public ResponseEntity<AcessShareDto> update(@RequestBody AcessShareDto dto) throws Exception {
        ResponseEntity<AcessShareDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            AcessShare t = service.findById(dto.getId());
            converter.copy(dto,t);
            AcessShare updated = service.update(t);
            AcessShareDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of acessShare")
    @PostMapping("multiple")
    public ResponseEntity<List<AcessShareDto>> delete(@RequestBody List<AcessShareDto> dtos) throws Exception {
        ResponseEntity<List<AcessShareDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<AcessShare> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified acessShare")
    @DeleteMapping("")
    public ResponseEntity<AcessShareDto> delete(@RequestBody AcessShareDto dto) throws Exception {
		ResponseEntity<AcessShareDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            AcessShare t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified acessShare")
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
    @Operation(summary = "Delete multiple acessShares by ids")
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



    @Operation(summary = "Finds a acessShare and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AcessShareDto> findWithAssociatedLists(@PathVariable Long id) {
        AcessShare loaded =  service.findWithAssociatedLists(id);
        AcessShareDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds acessShares by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AcessShareDto>> findByCriteria(@RequestBody AcessShareCriteria criteria) throws Exception {
        ResponseEntity<List<AcessShareDto>> res = null;
        List<AcessShare> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<AcessShareDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated acessShares by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AcessShareCriteria criteria) throws Exception {
        List<AcessShare> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<AcessShareDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets acessShare data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AcessShareCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AcessShareDto> findDtos(List<AcessShare> list){
        List<AcessShareDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AcessShareDto> getDtoResponseEntity(AcessShareDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AcessShareUtilisateurService service;
    @Autowired private AcessShareConverter converter;





}

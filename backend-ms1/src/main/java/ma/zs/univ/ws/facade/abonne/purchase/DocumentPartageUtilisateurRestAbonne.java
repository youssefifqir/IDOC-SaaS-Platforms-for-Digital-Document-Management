package  ma.zs.univ.ws.facade.abonne.purchase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.purchase.DocumentPartageUtilisateur;
import ma.zs.univ.dao.criteria.core.purchase.DocumentPartageUtilisateurCriteria;
import ma.zs.univ.service.facade.abonne.purchase.DocumentPartageUtilisateurAbonneService;
import ma.zs.univ.ws.converter.purchase.DocumentPartageUtilisateurConverter;
import ma.zs.univ.ws.dto.purchase.DocumentPartageUtilisateurDto;
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
@RequestMapping("/api/abonne/documentPartageUtilisateur/")
public class DocumentPartageUtilisateurRestAbonne {




    @Operation(summary = "Finds a list of all documentPartageUtilisateurs")
    @GetMapping("")
    public ResponseEntity<List<DocumentPartageUtilisateurDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentPartageUtilisateurDto>> res = null;
        List<DocumentPartageUtilisateur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DocumentPartageUtilisateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a documentPartageUtilisateur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentPartageUtilisateurDto> findById(@PathVariable Long id) {
        DocumentPartageUtilisateur t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DocumentPartageUtilisateurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  documentPartageUtilisateur")
    @PostMapping("")
    public ResponseEntity<DocumentPartageUtilisateurDto> save(@RequestBody DocumentPartageUtilisateurDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DocumentPartageUtilisateur myT = converter.toItem(dto);
            DocumentPartageUtilisateur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentPartageUtilisateurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentPartageUtilisateur")
    @PutMapping("")
    public ResponseEntity<DocumentPartageUtilisateurDto> update(@RequestBody DocumentPartageUtilisateurDto dto) throws Exception {
        ResponseEntity<DocumentPartageUtilisateurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentPartageUtilisateur t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentPartageUtilisateur updated = service.update(t);
            DocumentPartageUtilisateurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentPartageUtilisateur")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentPartageUtilisateurDto>> delete(@RequestBody List<DocumentPartageUtilisateurDto> dtos) throws Exception {
        ResponseEntity<List<DocumentPartageUtilisateurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DocumentPartageUtilisateur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentPartageUtilisateur")
    @DeleteMapping("")
    public ResponseEntity<DocumentPartageUtilisateurDto> delete(@RequestBody DocumentPartageUtilisateurDto dto) throws Exception {
		ResponseEntity<DocumentPartageUtilisateurDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DocumentPartageUtilisateur t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentPartageUtilisateur")
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
    @Operation(summary = "Delete multiple documentPartageUtilisateurs by ids")
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



    @Operation(summary = "Finds a documentPartageUtilisateur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentPartageUtilisateurDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentPartageUtilisateur loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DocumentPartageUtilisateurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentPartageUtilisateurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentPartageUtilisateurDto>> findByCriteria(@RequestBody DocumentPartageUtilisateurCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentPartageUtilisateurDto>> res = null;
        List<DocumentPartageUtilisateur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DocumentPartageUtilisateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentPartageUtilisateurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentPartageUtilisateurCriteria criteria) throws Exception {
        List<DocumentPartageUtilisateur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DocumentPartageUtilisateurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentPartageUtilisateur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentPartageUtilisateurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentPartageUtilisateurDto> findDtos(List<DocumentPartageUtilisateur> list){
        converter.initObject(true);
        List<DocumentPartageUtilisateurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentPartageUtilisateurDto> getDtoResponseEntity(DocumentPartageUtilisateurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentPartageUtilisateurAbonneService service;
    @Autowired private DocumentPartageUtilisateurConverter converter;





}

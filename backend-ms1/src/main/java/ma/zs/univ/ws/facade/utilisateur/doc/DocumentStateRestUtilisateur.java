package  ma.zs.univ.ws.facade.utilisateur.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.doc.DocumentState;
import ma.zs.univ.dao.criteria.core.doc.DocumentStateCriteria;
import ma.zs.univ.service.facade.utilisateur.doc.DocumentStateUtilisateurService;
import ma.zs.univ.ws.converter.doc.DocumentStateConverter;
import ma.zs.univ.ws.dto.doc.DocumentStateDto;
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
@RequestMapping("/api/utilisateur/documentState/")
public class DocumentStateRestUtilisateur {




    @Operation(summary = "Finds a list of all documentStates")
    @GetMapping("")
    public ResponseEntity<List<DocumentStateDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentStateDto>> res = null;
        List<DocumentState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all documentStates")
    @GetMapping("optimized")
    public ResponseEntity<List<DocumentStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DocumentStateDto>> res = null;
        List<DocumentState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a documentState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentStateDto> findById(@PathVariable Long id) {
        DocumentState t = service.findById(id);
        if (t != null) {
            DocumentStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a documentState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DocumentStateDto> findByLibelle(@PathVariable String libelle) {
	    DocumentState t = service.findByReferenceEntity(new DocumentState(libelle));
        if (t != null) {
            DocumentStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  documentState")
    @PostMapping("")
    public ResponseEntity<DocumentStateDto> save(@RequestBody DocumentStateDto dto) throws Exception {
        if(dto!=null){
            DocumentState myT = converter.toItem(dto);
            DocumentState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentState")
    @PutMapping("")
    public ResponseEntity<DocumentStateDto> update(@RequestBody DocumentStateDto dto) throws Exception {
        ResponseEntity<DocumentStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentState t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentState updated = service.update(t);
            DocumentStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentState")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentStateDto>> delete(@RequestBody List<DocumentStateDto> dtos) throws Exception {
        ResponseEntity<List<DocumentStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DocumentState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentState")
    @DeleteMapping("")
    public ResponseEntity<DocumentStateDto> delete(@RequestBody DocumentStateDto dto) throws Exception {
		ResponseEntity<DocumentStateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            DocumentState t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentState")
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
    @Operation(summary = "Delete multiple documentStates by ids")
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



    @Operation(summary = "Finds a documentState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentStateDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentState loaded =  service.findWithAssociatedLists(id);
        DocumentStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentStateDto>> findByCriteria(@RequestBody DocumentStateCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentStateDto>> res = null;
        List<DocumentState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentStateCriteria criteria) throws Exception {
        List<DocumentState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DocumentStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentStateDto> findDtos(List<DocumentState> list){
        List<DocumentStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentStateDto> getDtoResponseEntity(DocumentStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentStateUtilisateurService service;
    @Autowired private DocumentStateConverter converter;





}

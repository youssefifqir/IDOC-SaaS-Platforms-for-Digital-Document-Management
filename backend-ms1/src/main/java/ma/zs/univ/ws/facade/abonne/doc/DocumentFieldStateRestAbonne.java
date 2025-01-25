package  ma.zs.univ.ws.facade.abonne.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.doc.DocumentFieldState;
import ma.zs.univ.dao.criteria.core.doc.DocumentFieldStateCriteria;
import ma.zs.univ.service.facade.abonne.doc.DocumentFieldStateAbonneService;
import ma.zs.univ.ws.converter.doc.DocumentFieldStateConverter;
import ma.zs.univ.ws.dto.doc.DocumentFieldStateDto;
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
@RequestMapping("/api/abonne/documentFieldState/")
public class DocumentFieldStateRestAbonne {




    @Operation(summary = "Finds a list of all documentFieldStates")
    @GetMapping("")
    public ResponseEntity<List<DocumentFieldStateDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentFieldStateDto>> res = null;
        List<DocumentFieldState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentFieldStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all documentFieldStates")
    @GetMapping("optimized")
    public ResponseEntity<List<DocumentFieldStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DocumentFieldStateDto>> res = null;
        List<DocumentFieldState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentFieldStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a documentFieldState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentFieldStateDto> findById(@PathVariable Long id) {
        DocumentFieldState t = service.findById(id);
        if (t != null) {
            DocumentFieldStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a documentFieldState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DocumentFieldStateDto> findByLibelle(@PathVariable String libelle) {
	    DocumentFieldState t = service.findByReferenceEntity(new DocumentFieldState(libelle));
        if (t != null) {
            DocumentFieldStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  documentFieldState")
    @PostMapping("")
    public ResponseEntity<DocumentFieldStateDto> save(@RequestBody DocumentFieldStateDto dto) throws Exception {
        if(dto!=null){
            DocumentFieldState myT = converter.toItem(dto);
            DocumentFieldState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentFieldStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentFieldState")
    @PutMapping("")
    public ResponseEntity<DocumentFieldStateDto> update(@RequestBody DocumentFieldStateDto dto) throws Exception {
        ResponseEntity<DocumentFieldStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentFieldState t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentFieldState updated = service.update(t);
            DocumentFieldStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentFieldState")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentFieldStateDto>> delete(@RequestBody List<DocumentFieldStateDto> dtos) throws Exception {
        ResponseEntity<List<DocumentFieldStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DocumentFieldState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentFieldState")
    @DeleteMapping("")
    public ResponseEntity<DocumentFieldStateDto> delete(@RequestBody DocumentFieldStateDto dto) throws Exception {
		ResponseEntity<DocumentFieldStateDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            DocumentFieldState t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentFieldState")
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
    @Operation(summary = "Delete multiple documentFieldStates by ids")
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



    @Operation(summary = "Finds a documentFieldState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentFieldStateDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentFieldState loaded =  service.findWithAssociatedLists(id);
        DocumentFieldStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentFieldStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentFieldStateDto>> findByCriteria(@RequestBody DocumentFieldStateCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentFieldStateDto>> res = null;
        List<DocumentFieldState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentFieldStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentFieldStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentFieldStateCriteria criteria) throws Exception {
        List<DocumentFieldState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DocumentFieldStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentFieldState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentFieldStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentFieldStateDto> findDtos(List<DocumentFieldState> list){
        List<DocumentFieldStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentFieldStateDto> getDtoResponseEntity(DocumentFieldStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentFieldStateAbonneService service;
    @Autowired private DocumentFieldStateConverter converter;





}

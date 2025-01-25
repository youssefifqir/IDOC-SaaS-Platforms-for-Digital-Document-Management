package  ma.zs.univ.ws.facade.admin.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.univ.bean.core.doc.DocumentType;
import ma.zs.univ.dao.criteria.core.doc.DocumentTypeCriteria;
import ma.zs.univ.service.facade.admin.doc.DocumentTypeAdminService;
import ma.zs.univ.ws.converter.doc.DocumentTypeConverter;
import ma.zs.univ.ws.dto.doc.DocumentTypeDto;
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
@RequestMapping("/api/admin/documentType/")
public class DocumentTypeRestAdmin {




    @Operation(summary = "Finds a list of all documentTypes")
    @GetMapping("")
    public ResponseEntity<List<DocumentTypeDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentTypeDto>> res = null;
        List<DocumentType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all documentTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<DocumentTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DocumentTypeDto>> res = null;
        List<DocumentType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a documentType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentTypeDto> findById(@PathVariable Long id) {
        DocumentType t = service.findById(id);
        if (t != null) {
            DocumentTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a documentType by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DocumentTypeDto> findByLibelle(@PathVariable String libelle) {
	    DocumentType t = service.findByReferenceEntity(new DocumentType(libelle));
        if (t != null) {
            DocumentTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  documentType")
    @PostMapping("")
    public ResponseEntity<DocumentTypeDto> save(@RequestBody DocumentTypeDto dto) throws Exception {
        if(dto!=null){
            DocumentType myT = converter.toItem(dto);
            DocumentType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentType")
    @PutMapping("")
    public ResponseEntity<DocumentTypeDto> update(@RequestBody DocumentTypeDto dto) throws Exception {
        ResponseEntity<DocumentTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentType t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentType updated = service.update(t);
            DocumentTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentType")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentTypeDto>> delete(@RequestBody List<DocumentTypeDto> dtos) throws Exception {
        ResponseEntity<List<DocumentTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DocumentType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentType")
    @DeleteMapping("")
    public ResponseEntity<DocumentTypeDto> delete(@RequestBody DocumentTypeDto dto) throws Exception {
		ResponseEntity<DocumentTypeDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            DocumentType t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentType")
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
    @Operation(summary = "Delete multiple documentTypes by ids")
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



    @Operation(summary = "Finds a documentType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentType loaded =  service.findWithAssociatedLists(id);
        DocumentTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentTypeDto>> findByCriteria(@RequestBody DocumentTypeCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentTypeDto>> res = null;
        List<DocumentType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentTypeCriteria criteria) throws Exception {
        List<DocumentType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DocumentTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentTypeDto> findDtos(List<DocumentType> list){
        List<DocumentTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentTypeDto> getDtoResponseEntity(DocumentTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentTypeAdminService service;
    @Autowired private DocumentTypeConverter converter;





}

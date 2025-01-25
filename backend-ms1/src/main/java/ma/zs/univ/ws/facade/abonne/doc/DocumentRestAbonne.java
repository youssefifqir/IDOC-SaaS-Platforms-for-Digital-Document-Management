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

import ma.zs.univ.bean.core.doc.Document;
import ma.zs.univ.dao.criteria.core.doc.DocumentCriteria;
import ma.zs.univ.service.facade.abonne.doc.DocumentAbonneService;
import ma.zs.univ.ws.converter.doc.DocumentConverter;
import ma.zs.univ.ws.dto.doc.DocumentDto;
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
@RequestMapping("/api/abonne/document/")
public class DocumentRestAbonne {




    @Operation(summary = "Finds a list of all documents")
    @GetMapping("")
    public ResponseEntity<List<DocumentDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentDto>> res = null;
        List<Document> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<DocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all documents")
    @GetMapping("optimized")
    public ResponseEntity<List<DocumentDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DocumentDto>> res = null;
        List<Document> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<DocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a document by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentDto> findById(@PathVariable Long id) {
        Document t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a document by reference")
    @GetMapping("reference/{reference}")
    public ResponseEntity<DocumentDto> findByReference(@PathVariable String reference) {
	    Document t = service.findByReferenceEntity(new Document(reference));
        if (t != null) {
            converter.init(true);
            DocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  document")
    @PostMapping("")
    public ResponseEntity<DocumentDto> save(@RequestBody DocumentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Document myT = converter.toItem(dto);
            Document t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  document")
    @PutMapping("")
    public ResponseEntity<DocumentDto> update(@RequestBody DocumentDto dto) throws Exception {
        ResponseEntity<DocumentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Document t = service.findById(dto.getId());
            converter.copy(dto,t);
            Document updated = service.update(t);
            DocumentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of document")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentDto>> delete(@RequestBody List<DocumentDto> dtos) throws Exception {
        ResponseEntity<List<DocumentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Document> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified document")
    @DeleteMapping("")
    public ResponseEntity<DocumentDto> delete(@RequestBody DocumentDto dto) throws Exception {
		ResponseEntity<DocumentDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Document t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified document")
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
    @Operation(summary = "Delete multiple documents by ids")
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


    @Operation(summary = "find by documentState id")
    @GetMapping("documentState/id/{id}")
    public List<DocumentDto> findByDocumentStateId(@PathVariable Long id){
        return findDtos(service.findByDocumentStateId(id));
    }
    @Operation(summary = "delete by documentState id")
    @DeleteMapping("documentState/id/{id}")
    public int deleteByDocumentStateId(@PathVariable Long id){
        return service.deleteByDocumentStateId(id);
    }

    @Operation(summary = "Finds a document and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentDto> findWithAssociatedLists(@PathVariable Long id) {
        Document loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DocumentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documents by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentDto>> findByCriteria(@RequestBody DocumentCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentDto>> res = null;
        List<Document> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<DocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documents by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentCriteria criteria) throws Exception {
        List<Document> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<DocumentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets document data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentDto> findDtos(List<Document> list){
        converter.initList(false);
        converter.initObject(true);
        List<DocumentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentDto> getDtoResponseEntity(DocumentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentAbonneService service;
    @Autowired private DocumentConverter converter;





}

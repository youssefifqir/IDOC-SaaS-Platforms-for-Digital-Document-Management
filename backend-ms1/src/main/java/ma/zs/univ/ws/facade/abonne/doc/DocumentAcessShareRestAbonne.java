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

import ma.zs.univ.bean.core.doc.DocumentAcessShare;
import ma.zs.univ.dao.criteria.core.doc.DocumentAcessShareCriteria;
import ma.zs.univ.service.facade.abonne.doc.DocumentAcessShareAbonneService;
import ma.zs.univ.ws.converter.doc.DocumentAcessShareConverter;
import ma.zs.univ.ws.dto.doc.DocumentAcessShareDto;
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
@RequestMapping("/api/abonne/documentAcessShare/")
public class DocumentAcessShareRestAbonne {




    @Operation(summary = "Finds a list of all documentAcessShares")
    @GetMapping("")
    public ResponseEntity<List<DocumentAcessShareDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentAcessShareDto>> res = null;
        List<DocumentAcessShare> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DocumentAcessShareDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a documentAcessShare by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentAcessShareDto> findById(@PathVariable Long id) {
        DocumentAcessShare t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DocumentAcessShareDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  documentAcessShare")
    @PostMapping("")
    public ResponseEntity<DocumentAcessShareDto> save(@RequestBody DocumentAcessShareDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DocumentAcessShare myT = converter.toItem(dto);
            DocumentAcessShare t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentAcessShareDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentAcessShare")
    @PutMapping("")
    public ResponseEntity<DocumentAcessShareDto> update(@RequestBody DocumentAcessShareDto dto) throws Exception {
        ResponseEntity<DocumentAcessShareDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentAcessShare t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentAcessShare updated = service.update(t);
            DocumentAcessShareDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentAcessShare")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentAcessShareDto>> delete(@RequestBody List<DocumentAcessShareDto> dtos) throws Exception {
        ResponseEntity<List<DocumentAcessShareDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DocumentAcessShare> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentAcessShare")
    @DeleteMapping("")
    public ResponseEntity<DocumentAcessShareDto> delete(@RequestBody DocumentAcessShareDto dto) throws Exception {
		ResponseEntity<DocumentAcessShareDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DocumentAcessShare t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentAcessShare")
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
    @Operation(summary = "Delete multiple documentAcessShares by ids")
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


    @Operation(summary = "find by document id")
    @GetMapping("document/id/{id}")
    public List<DocumentAcessShareDto> findByDocumentId(@PathVariable Long id){
        return findDtos(service.findByDocumentId(id));
    }
    @Operation(summary = "delete by document id")
    @DeleteMapping("document/id/{id}")
    public int deleteByDocumentId(@PathVariable Long id){
        return service.deleteByDocumentId(id);
    }
    @Operation(summary = "find by acessShare id")
    @GetMapping("acessShare/id/{id}")
    public List<DocumentAcessShareDto> findByAcessShareId(@PathVariable Long id){
        return findDtos(service.findByAcessShareId(id));
    }
    @Operation(summary = "delete by acessShare id")
    @DeleteMapping("acessShare/id/{id}")
    public int deleteByAcessShareId(@PathVariable Long id){
        return service.deleteByAcessShareId(id);
    }

    @Operation(summary = "Finds a documentAcessShare and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentAcessShareDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentAcessShare loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DocumentAcessShareDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentAcessShares by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentAcessShareDto>> findByCriteria(@RequestBody DocumentAcessShareCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentAcessShareDto>> res = null;
        List<DocumentAcessShare> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DocumentAcessShareDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentAcessShares by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentAcessShareCriteria criteria) throws Exception {
        List<DocumentAcessShare> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DocumentAcessShareDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentAcessShare data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentAcessShareCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentAcessShareDto> findDtos(List<DocumentAcessShare> list){
        converter.initObject(true);
        List<DocumentAcessShareDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentAcessShareDto> getDtoResponseEntity(DocumentAcessShareDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentAcessShareAbonneService service;
    @Autowired private DocumentAcessShareConverter converter;





}

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

import ma.zs.univ.bean.core.doc.DocumentField;
import ma.zs.univ.dao.criteria.core.doc.DocumentFieldCriteria;
import ma.zs.univ.service.facade.utilisateur.doc.DocumentFieldUtilisateurService;
import ma.zs.univ.ws.converter.doc.DocumentFieldConverter;
import ma.zs.univ.ws.dto.doc.DocumentFieldDto;
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
@RequestMapping("/api/utilisateur/documentField/")
public class DocumentFieldRestUtilisateur {




    @Operation(summary = "Finds a list of all documentFields")
    @GetMapping("")
    public ResponseEntity<List<DocumentFieldDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentFieldDto>> res = null;
        List<DocumentField> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DocumentFieldDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a documentField by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentFieldDto> findById(@PathVariable Long id) {
        DocumentField t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DocumentFieldDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  documentField")
    @PostMapping("")
    public ResponseEntity<DocumentFieldDto> save(@RequestBody DocumentFieldDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DocumentField myT = converter.toItem(dto);
            DocumentField t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentFieldDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentField")
    @PutMapping("")
    public ResponseEntity<DocumentFieldDto> update(@RequestBody DocumentFieldDto dto) throws Exception {
        ResponseEntity<DocumentFieldDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentField t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentField updated = service.update(t);
            DocumentFieldDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentField")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentFieldDto>> delete(@RequestBody List<DocumentFieldDto> dtos) throws Exception {
        ResponseEntity<List<DocumentFieldDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DocumentField> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentField")
    @DeleteMapping("")
    public ResponseEntity<DocumentFieldDto> delete(@RequestBody DocumentFieldDto dto) throws Exception {
		ResponseEntity<DocumentFieldDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DocumentField t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentField")
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
    @Operation(summary = "Delete multiple documentFields by ids")
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



    @Operation(summary = "Finds a documentField and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentFieldDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentField loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DocumentFieldDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentFields by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentFieldDto>> findByCriteria(@RequestBody DocumentFieldCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentFieldDto>> res = null;
        List<DocumentField> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DocumentFieldDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentFields by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentFieldCriteria criteria) throws Exception {
        List<DocumentField> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DocumentFieldDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentField data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentFieldCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentFieldDto> findDtos(List<DocumentField> list){
        converter.initObject(true);
        List<DocumentFieldDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentFieldDto> getDtoResponseEntity(DocumentFieldDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentFieldUtilisateurService service;
    @Autowired private DocumentFieldConverter converter;





}

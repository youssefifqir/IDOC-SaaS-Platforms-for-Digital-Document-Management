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

import ma.zs.univ.bean.core.doc.DocumentCategorie;
import ma.zs.univ.dao.criteria.core.doc.DocumentCategorieCriteria;
import ma.zs.univ.service.facade.utilisateur.doc.DocumentCategorieUtilisateurService;
import ma.zs.univ.ws.converter.doc.DocumentCategorieConverter;
import ma.zs.univ.ws.dto.doc.DocumentCategorieDto;
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
@RequestMapping("/api/utilisateur/documentCategorie/")
public class DocumentCategorieRestUtilisateur {




    @Operation(summary = "Finds a list of all documentCategories")
    @GetMapping("")
    public ResponseEntity<List<DocumentCategorieDto>> findAll() throws Exception {
        ResponseEntity<List<DocumentCategorieDto>> res = null;
        List<DocumentCategorie> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentCategorieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all documentCategories")
    @GetMapping("optimized")
    public ResponseEntity<List<DocumentCategorieDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DocumentCategorieDto>> res = null;
        List<DocumentCategorie> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentCategorieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a documentCategorie by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DocumentCategorieDto> findById(@PathVariable Long id) {
        DocumentCategorie t = service.findById(id);
        if (t != null) {
            DocumentCategorieDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a documentCategorie by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DocumentCategorieDto> findByLibelle(@PathVariable String libelle) {
	    DocumentCategorie t = service.findByReferenceEntity(new DocumentCategorie(libelle));
        if (t != null) {
            DocumentCategorieDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  documentCategorie")
    @PostMapping("")
    public ResponseEntity<DocumentCategorieDto> save(@RequestBody DocumentCategorieDto dto) throws Exception {
        if(dto!=null){
            DocumentCategorie myT = converter.toItem(dto);
            DocumentCategorie t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DocumentCategorieDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  documentCategorie")
    @PutMapping("")
    public ResponseEntity<DocumentCategorieDto> update(@RequestBody DocumentCategorieDto dto) throws Exception {
        ResponseEntity<DocumentCategorieDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DocumentCategorie t = service.findById(dto.getId());
            converter.copy(dto,t);
            DocumentCategorie updated = service.update(t);
            DocumentCategorieDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of documentCategorie")
    @PostMapping("multiple")
    public ResponseEntity<List<DocumentCategorieDto>> delete(@RequestBody List<DocumentCategorieDto> dtos) throws Exception {
        ResponseEntity<List<DocumentCategorieDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DocumentCategorie> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified documentCategorie")
    @DeleteMapping("")
    public ResponseEntity<DocumentCategorieDto> delete(@RequestBody DocumentCategorieDto dto) throws Exception {
		ResponseEntity<DocumentCategorieDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            DocumentCategorie t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified documentCategorie")
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
    @Operation(summary = "Delete multiple documentCategories by ids")
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



    @Operation(summary = "Finds a documentCategorie and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DocumentCategorieDto> findWithAssociatedLists(@PathVariable Long id) {
        DocumentCategorie loaded =  service.findWithAssociatedLists(id);
        DocumentCategorieDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds documentCategories by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DocumentCategorieDto>> findByCriteria(@RequestBody DocumentCategorieCriteria criteria) throws Exception {
        ResponseEntity<List<DocumentCategorieDto>> res = null;
        List<DocumentCategorie> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DocumentCategorieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated documentCategories by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DocumentCategorieCriteria criteria) throws Exception {
        List<DocumentCategorie> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DocumentCategorieDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets documentCategorie data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DocumentCategorieCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DocumentCategorieDto> findDtos(List<DocumentCategorie> list){
        List<DocumentCategorieDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DocumentCategorieDto> getDtoResponseEntity(DocumentCategorieDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DocumentCategorieUtilisateurService service;
    @Autowired private DocumentCategorieConverter converter;





}

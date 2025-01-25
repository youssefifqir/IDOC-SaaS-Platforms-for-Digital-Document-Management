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

import ma.zs.univ.bean.core.doc.Tag;
import ma.zs.univ.dao.criteria.core.doc.TagCriteria;
import ma.zs.univ.service.facade.utilisateur.doc.TagUtilisateurService;
import ma.zs.univ.ws.converter.doc.TagConverter;
import ma.zs.univ.ws.dto.doc.TagDto;
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
@RequestMapping("/api/utilisateur/tag/")
public class TagRestUtilisateur {




    @Operation(summary = "Finds a list of all tags")
    @GetMapping("")
    public ResponseEntity<List<TagDto>> findAll() throws Exception {
        ResponseEntity<List<TagDto>> res = null;
        List<Tag> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TagDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all tags")
    @GetMapping("optimized")
    public ResponseEntity<List<TagDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TagDto>> res = null;
        List<Tag> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TagDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a tag by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TagDto> findById(@PathVariable Long id) {
        Tag t = service.findById(id);
        if (t != null) {
            TagDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a tag by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TagDto> findByLibelle(@PathVariable String libelle) {
	    Tag t = service.findByReferenceEntity(new Tag(libelle));
        if (t != null) {
            TagDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  tag")
    @PostMapping("")
    public ResponseEntity<TagDto> save(@RequestBody TagDto dto) throws Exception {
        if(dto!=null){
            Tag myT = converter.toItem(dto);
            Tag t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TagDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  tag")
    @PutMapping("")
    public ResponseEntity<TagDto> update(@RequestBody TagDto dto) throws Exception {
        ResponseEntity<TagDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Tag t = service.findById(dto.getId());
            converter.copy(dto,t);
            Tag updated = service.update(t);
            TagDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of tag")
    @PostMapping("multiple")
    public ResponseEntity<List<TagDto>> delete(@RequestBody List<TagDto> dtos) throws Exception {
        ResponseEntity<List<TagDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Tag> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified tag")
    @DeleteMapping("")
    public ResponseEntity<TagDto> delete(@RequestBody TagDto dto) throws Exception {
		ResponseEntity<TagDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Tag t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified tag")
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
    @Operation(summary = "Delete multiple tags by ids")
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



    @Operation(summary = "Finds a tag and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TagDto> findWithAssociatedLists(@PathVariable Long id) {
        Tag loaded =  service.findWithAssociatedLists(id);
        TagDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds tags by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TagDto>> findByCriteria(@RequestBody TagCriteria criteria) throws Exception {
        ResponseEntity<List<TagDto>> res = null;
        List<Tag> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TagDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated tags by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TagCriteria criteria) throws Exception {
        List<Tag> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TagDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets tag data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TagCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TagDto> findDtos(List<Tag> list){
        List<TagDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TagDto> getDtoResponseEntity(TagDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TagUtilisateurService service;
    @Autowired private TagConverter converter;





}

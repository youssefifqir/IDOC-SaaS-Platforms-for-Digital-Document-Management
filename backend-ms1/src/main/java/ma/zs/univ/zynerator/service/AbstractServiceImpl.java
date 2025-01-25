package ma.zs.univ.zynerator.service;

import ma.zs.univ.zynerator.exception.EntityNotFoundException;

import ma.zs.univ.zynerator.bean.BaseEntity;
import ma.zs.univ.zynerator.criteria.BaseCriteria;
import ma.zs.univ.zynerator.exception.BusinessRuleException;
import ma.zs.univ.zynerator.exception.EntityNotFoundException;
import ma.zs.univ.zynerator.repository.AbstractRepository;
import ma.zs.univ.zynerator.security.bean.User;
import ma.zs.univ.zynerator.security.service.facade.UserService;
import ma.zs.univ.zynerator.specification.AbstractSpecification;
import ma.zs.univ.zynerator.util.FileUtils;
import ma.zs.univ.zynerator.util.ListUtil;
import ma.zs.univ.zynerator.util.MD5Checksum;
import ma.zs.univ.zynerator.util.RefelexivityUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


public abstract class AbstractServiceImpl<T extends BaseEntity, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImplHelper<T> {

    protected AbstractSpecification<CRITERIA, T> specification;
    protected Class<? extends AbstractSpecification<CRITERIA, T>> specificationClass;



    protected REPO dao;
    @Autowired
    protected UserService userService;

    protected Class<T> itemClass;

    @Value("${uploads.location.directory}")
    private String UPLOADED_FOLDER;
    @Value("${uploads.location.temp}")
    private String UPLOADED_TEMP_FOLDER;

    public AbstractServiceImpl(REPO dao) {
        this.dao = dao;
        this.configure();
    }


    public void deleteAssociatedLists(Long id) {
    }

    public void deleteAssociatedListsByReferenceEntity(T t) {
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public T create(T t) {
        T loaded = findByReferenceEntity(t);
        if (loaded == null) {
            T saved = dao.save(t);
            return saved;
        } else {
            return loaded;
        }
    }
    /*

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DTO create(DTO dto) {
        T t = converter.toItem(dto);
        T saved = dao.save(t);
        dto.setId(saved.getId());
        return dto;
    }
    */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<T> create(List<T> ts) {
        List<T> result = new ArrayList<>();
        if (ts != null) {
            for (T t : ts) {
                if (t.getId() == null || findById(t.getId()) == null) {
                    dao.save(t);
                } else {
                    result.add(t);
                }
            }
        }
    return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<T> update(List<T> ts, boolean createIfNotExist) {
        List<T> result = new ArrayList<>();
        if (ts != null) {
            for (T t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    T loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }

    /*  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DTO> create(List<DTO> dtos) {
        if (dtos != null) {
            for (DTO dto : dtos) {
                create(dto);
            }
        }
        return dtos;
    }
    */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public T update(T t) {
        //saveAuditData(t, ACTION_TYPE.UPDATE);
        T loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{itemClass.getSimpleName(), t.getId().toString()});
        } else {
            //Utils.copyNonNullProperties(t, loadedItem);
            //dao.saveAndFlush(loadedItem);
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public T findById(Long id) {
    Optional<T> item = dao.findById(id);
        return item.orElse(null);
    }

    public T findByReferenceEntity(T t) {
        return t.getId() == null ? null : findById(t.getId());
    }

    public T findOrSave(T t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            T result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public void findOrSaveAssociatedObject(T t) {

    }

    public List<T> importerData(List<T> items) {
        List<T> list = new ArrayList<>();
        for (T t : items) {
            T founded = findByReferenceEntity(t);
                if (founded == null) {
                findOrSaveAssociatedObject(t);
                dao.save(t);
            } else {
                list.add(founded);
            }
        }
        return list;
    }

    public T findWithAssociatedLists(Long id) {
        return findById(id);
    }

    public void deleteWithAssociatedLists(T t) {
        deleteAssociatedLists(t.getId());
        delete(t);
    }

    public void updateWithAssociatedLists(T t) {

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void delete(T t) {
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId()); // il fait find by id apres delete !!!
            //constructAndSaveHistory(dto, ACTION_TYPE.DELETE); TO DO
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void delete(List<T> list) {
        if (list != null) {
            for (T t : list) {
                deleteAssociatedLists(t.getId());
                dao.deleteById(t.getId()); // il fait find by id apres delete !!!
            //constructAndSaveHistory(dto, ACTION_TYPE.DELETE); TO DO
            }
        }
    }


    public List<T> findByCriteria(CRITERIA criteria) {
        List<T> content = null;
        if (criteria != null) {
            AbstractSpecification<CRITERIA, T> mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private AbstractSpecification<CRITERIA, T> constructSpecification(CRITERIA criteria) {
        AbstractSpecification<CRITERIA, T> mySpecification = RefelexivityUtil.constructObjectUsingOneParam(specificationClass, criteria);
        return mySpecification;
    }


    public List<T> findPaginatedByCriteria(CRITERIA criteria, int page, int pageSize, String order, String sortField) {
        AbstractSpecification<CRITERIA, T> mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CRITERIA criteria) {
        AbstractSpecification<CRITERIA, T> mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }


    public List<T> findAll() {
        return dao.findAll();
        }

    public List<T> findAllOptimized() {
        return dao.findAll();
    }


    //****************************** HISTORY

    /*
    public void saveAuditData(DTO dto, ACTION_TYPE action){
    DTO old = abstractConverter.toDto(findById(dto.getId()));
    try {
        if (Utils.compareObjectsDiff(dto, old)) {
            constructAndSaveHistory(dto, action);
        }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void constructAndSaveHistory(DTO dto, ACTION_TYPE action) {
        User currentUser = getCurrentUser();
        H history = RefelexivityUtil.constructObjectUsingDefaultConstr(historyClass);
        history.setActionType(action.name());
        history.setObjectName(itemClass.getSimpleName());
        history.setObjectId(dto.getId());
        history.setUserId(currentUser.getId());
        history.setUsername(currentUser.getUsername());
        String dtoAsJson = null;
        try {
            dtoAsJson = new ObjectMapper().writeValueAsString(dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        history.setData(dtoAsJson);
        history.setDate(LocalDateTime.now());
        historyRepository.save(history);
    }

    */




    public void configure(Class<T> itemClass, Class<? extends AbstractSpecification<CRITERIA, T>> specificationClass) {
        this.itemClass = itemClass;
        this.specificationClass = specificationClass;
    }

    public abstract void configure();




    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof User) {
        return (User) principal;
        } else if (principal != null && principal instanceof String) {
        return userService.findByUsername(principal.toString());
        } else {
        return null;
        }
    }


    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception {
        String crName = null;
        if (FileUtils.isFileExist(UPLOADED_TEMP_FOLDER, tempUpladedFile)) {
            String filePath = destinationFilePath;
            if (!FileUtils.isFileExist(UPLOADED_TEMP_FOLDER, tempUpladedFile))
                return crName;

            String checksum = MD5Checksum.getMD5Checksum(UPLOADED_TEMP_FOLDER + tempUpladedFile);
                if (!checksum.equals(checksumOld)) {
                    throw new BusinessRuleException("errors.file.checksum", new String[]{tempUpladedFile});
                }

            crName = FileUtils.saveFile(UPLOADED_TEMP_FOLDER, UPLOADED_FOLDER, tempUpladedFile, filePath, "");

            if (FileUtils.isFileExist(UPLOADED_FOLDER, crName)) {
                checksum = MD5Checksum.getMD5Checksum(UPLOADED_FOLDER + crName);
                if (!checksum.equals(checksumOld)) {
                    throw new BusinessRuleException("errors.file.checksum", new String[]{""});
                }
            } else {
                throw new BusinessRuleException("errors.file.data.creation", new String[]{""});
            }
        }
        return crName;
    }

    public List<T> importExcel(MultipartFile file) {
        if (isValidExcelFile(file)) {
            try {
                List<T> items = read(file.getInputStream(), getAttributes());
                this.dao.saveAll(items);
                return items;
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
        return null;
    }

    protected List<Attribute> getAttributes() {
        return new ArrayList<>();
    }

    public boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    // create a methode that reade the file and take an inputStream as object and return a liste of commandes
    private List<T> read(InputStream inputStream, List<Attribute> attributes) {
        List<T> items = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowIndex;
            int lastRowIndex = sheet.getLastRowNum();
            for (rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                int cellIndex = 0;
                T item = itemClass.getDeclaredConstructor().newInstance();
                BeanWrapper beanWrapper = new BeanWrapperImpl(item);
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String attributeName = attributes.get(cellIndex).getName();
                    String type = attributes.get(cellIndex).getType();
                    Class complexType = attributes.get(cellIndex).getComplexeType();
                    Object value = null;
                    if (cell.getCellType() != CellType.BLANK) {
                        if (type.equals("String")) {
                            value = cell.getStringCellValue();
                        } else if (type.equals("BigDecimal")) {
                            value = BigDecimal.valueOf(cell.getNumericCellValue());
                        } else if (type.equals("Long")) {
                            value = Long.valueOf((long) cell.getNumericCellValue());
                        } else if (type.equals("Boolean")) {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                double numericValue = cell.getNumericCellValue();
                                value = numericValue == 1.0;
                            } else if (cell.getCellType() == CellType.STRING) {
                                value = Boolean.parseBoolean(cell.getStringCellValue());
                            }
                        } else if (type.equals("LocalDateTime")) {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                Date dateValue = cell.getDateCellValue();
                                Instant instant = dateValue.toInstant();
                                value = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                            } else if (cell.getCellType() == CellType.STRING) {
                                String dateStr = cell.getStringCellValue();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
                                value = LocalDateTime.parse(dateStr, formatter);
                            }
                        }
                    }
                    if (complexType != null && value != null) {
                        beanWrapper.setPropertyValue(attributeName.split("\\.")[0], complexType.getDeclaredConstructor().newInstance());
                        beanWrapper.setPropertyValue(attributeName, value);
                    } else if (complexType == null) {
                        beanWrapper.setPropertyValue(attributes.get(cellIndex).getName(), value);
                    }
                    cellIndex++;
                    }
                    items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    //************************************************** UPDATE ***********************************
    public List<List<T>> getToBeSavedAndToBeDeleted(List<T> oldList, List<T> newList) {
        List<List<T>> result = new ArrayList<>();
        List<T> resultDelete = new ArrayList<>();
        List<T> resultUpdateOrSave = new ArrayList<>();
        if (ListUtil.isEmpty(oldList) && ListUtil.isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (ListUtil.isEmpty(newList) && ListUtil.isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (ListUtil.isNotEmpty(newList) && ListUtil.isNotEmpty(oldList)) {
            for (int i = 0; i < oldList.size(); i++) {
                T myOld = oldList.get(i);
                T t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t);
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                T myNew = newList.get(i);
                T t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew);
                }
            }
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }
}

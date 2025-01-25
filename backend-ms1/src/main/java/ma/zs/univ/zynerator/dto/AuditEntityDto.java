package ma.zs.univ.zynerator.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import ma.zs.univ.zynerator.history.HistBusinessObject;
import ma.zs.univ.zynerator.util.DateUtil;

public class AuditEntityDto extends BaseDto {

    /**
     * Colonne
     */
    protected String objectName;

    /**
     * OldValue
     */
    @Lob
    @Column(columnDefinition="TEXT")
    protected String data;

    /**
     * UserId
     */
    protected Long userId;

    /**
     * Username
     */
    protected String username;

    /**
     * Type_action
     */
    protected String actionType;

    /**
     * ObjectId
     */
    protected Long objectId;

    protected String dateHistory;

    public AuditEntityDto() {

    }

    public AuditEntityDto(Long id) {
        super(id);
    }

    public <T extends HistBusinessObject> AuditEntityDto(T histBusinessObject) {
        super();
        convertToDto(this, histBusinessObject);
    }

    public <T extends HistBusinessObject> AuditEntityDto convertToDto(AuditEntityDto auditEntityDto, T histBusinessObject) {
        if (auditEntityDto != null) {
            auditEntityDto.setId(histBusinessObject.getId());
            auditEntityDto.setObjectName(histBusinessObject.getObjectName());
            auditEntityDto.setObjectId(histBusinessObject.getObjectId());
            auditEntityDto.setActionType(histBusinessObject.getActionType());
            auditEntityDto.setDateHistory(histBusinessObject.getData());
            auditEntityDto.setUserId(histBusinessObject.getUserId());
            auditEntityDto.setUsername(histBusinessObject.getUsername());
        }

        return auditEntityDto;

    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(String dateHistory) {
        this.dateHistory = dateHistory;
    }

}

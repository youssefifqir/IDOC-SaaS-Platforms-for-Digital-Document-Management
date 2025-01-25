package ma.zs.univ.zynerator.history;

import ma.zs.univ.zynerator.bean.BusinessObject;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 *
 * @author Zouani
 * @version 1.2
 */

@MappedSuperclass
public class HistBusinessObject extends BusinessObject {

    /**
     * ObjectName
     */
    protected String objectName;

    /**
     * data
     */
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

    /**
     * Date
     */
    protected LocalDateTime dateHistory;

    /**
     * Constructeur par défaut.
     */
    public HistBusinessObject() {
        super();
    }

    public HistBusinessObject(Long id) {
        super(id);
        this.id = id;
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

    public LocalDateTime getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(LocalDateTime dateHistory) {
        this.dateHistory = dateHistory;
    }

    public String toString() {

        return this.getId() != null ? this.getId().toString() : null;
    }

}

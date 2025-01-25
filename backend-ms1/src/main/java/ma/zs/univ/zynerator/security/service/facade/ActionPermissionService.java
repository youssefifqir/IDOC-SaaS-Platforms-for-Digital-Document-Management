package ma.zs.univ.zynerator.security.service.facade;

import ma.zs.univ.zynerator.security.bean.ActionPermission;
import ma.zs.univ.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zs.univ.zynerator.service.IService;
import java.util.List;


public interface ActionPermissionService extends  IService<ActionPermission,ActionPermissionCriteria>  {

    List<ActionPermission> findAllOptimized();

}

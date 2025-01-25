package ma.zs.univ.zynerator.security.service.facade;

import ma.zs.univ.zynerator.security.bean.ModelPermission;
import ma.zs.univ.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.zs.univ.zynerator.service.IService;
import java.util.List;



public interface ModelPermissionService extends  IService<ModelPermission,ModelPermissionCriteria>  {
    List<ModelPermission> findAllOptimized();

}

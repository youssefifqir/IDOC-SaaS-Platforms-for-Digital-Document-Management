package ma.zs.univ.zynerator.security.service.facade;

import ma.zs.univ.zynerator.security.bean.Role;
import ma.zs.univ.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.zs.univ.zynerator.service.IService;



public interface RoleService extends  IService<Role,RoleCriteria>  {
    Role findByAuthority(String authority);
    int deleteByAuthority(String authority);


    



}

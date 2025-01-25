package ma.zs.univ.zynerator.security.service.impl;


import ma.zs.univ.zynerator.security.bean.ModelPermissionUser;
import ma.zs.univ.zynerator.security.bean.RoleUser;
import ma.zs.univ.zynerator.security.bean.User;
import ma.zs.univ.zynerator.security.dao.criteria.core.UserCriteria;
import ma.zs.univ.zynerator.security.dao.facade.core.UserDao;
import ma.zs.univ.zynerator.security.dao.specification.core.UserSpecification;
import ma.zs.univ.zynerator.security.service.facade.*;
import ma.zs.univ.zynerator.service.AbstractServiceImpl;
import ma.zs.univ.zynerator.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserCriteria, UserDao> implements UserService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public User create(User t) {
        User foundedUserByUsername = findByUsername(t.getUsername());
        User foundedUserByEmail = dao.findByEmail(t.getEmail());
        if (foundedUserByUsername != null || foundedUserByEmail != null) return null;
        else {
            if (t.getPassword() == null || t.getPassword().isEmpty()) {
            t.setPassword(bCryptPasswordEncoder.encode(t.getUsername()));
            }
            else {
            t.setPassword(bCryptPasswordEncoder.encode(t.getPassword()));
            }
            //t.setPassword(bCryptPasswordEncoder.encode("123"));
            t.setAccountNonExpired(true);
            t.setAccountNonLocked(true);
            t.setCredentialsNonExpired(true);
            t.setEnabled(true);
            t.setPasswordChanged(false);
            t.setCreatedAt(LocalDateTime.now());
//            Role roleFor = roleService.findByAuthority(AuthoritiesConstants.);
//            if(t.getRoleUsers()==null)
//                t.setRoleUsers(new ArrayList<>());
//
//            t.getRoleUsers().add(roleFor);
//
//            if (t.getRoleUsers() != null) {
//                Collection<Role> roles = new ArrayList<Role>();
//                for (Role role : t.getRoles()) {
//                    roles.add(roleService.save(role));
//                }
//                t.getRoleUsers(roles);
//            }
            super.create(t);
            if (t.getModelPermissionUsers() != null) {
                t.getModelPermissionUsers().forEach(e -> {
                    e.setUser(t);
                    modelPermissionUserService.create(e);
                });
            }
            if (t.getRoleUsers() != null) {
                t.getRoleUsers().forEach(element-> {
                    element.setUser(t);
                    roleUserService.create(element);
                });
            }
            return t;
        }

    }

    public User findWithAssociatedLists(Long id){
        User result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setModelPermissionUsers(modelPermissionUserService.findByUserId(id));
            result.setRoleUsers(roleUserService.findByUserId(id));
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        modelPermissionUserService.deleteByUserId(id);
        roleUserService.deleteByUserId(id);
    }


    public void updateWithAssociatedLists(User user){
    if(user !=null && user.getId() != null){
            List<List<ModelPermissionUser>> resultModelPermissionUsers= modelPermissionUserService.getToBeSavedAndToBeDeleted(modelPermissionUserService.findByUserId(user.getId()),user.getModelPermissionUsers());
            modelPermissionUserService.delete(resultModelPermissionUsers.get(1));
            ListUtil.emptyIfNull(resultModelPermissionUsers.get(0)).forEach(e -> e.setUser(user));
            modelPermissionUserService.update(resultModelPermissionUsers.get(0),true);
            List<List<RoleUser>> resultRoleUsers= roleUserService.getToBeSavedAndToBeDeleted(roleUserService.findByUserId(user.getId()),user.getRoleUsers());
            roleUserService.delete(resultRoleUsers.get(1));
            ListUtil.emptyIfNull(resultRoleUsers.get(0)).forEach(e -> e.setUser(user));
            roleUserService.update(resultRoleUsers.get(0),true);
    }
    }



    public User findByReferenceEntity(User t){
        return  dao.findByEmail(t.getEmail());
    }
    @Override
    public User findByUsername(String username) {
        if (username == null)
            return null;
        return dao.findByUsername(username);
    }

    public List<User> findAllOptimized() {
        return dao.findAllOptimized();
    }


    @Override
    public String cryptPassword(String value) {
        return value == null ? null : bCryptPasswordEncoder.encode(value);
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        User user = dao.findByUsername(username);
        if (user != null) {
            user.setPassword(cryptPassword(newPassword));
            user.setPasswordChanged(true);
            dao.save(user);
            return true;
        }
        return false;
    }
    @Override
    public User findByUsernameWithRoles(String username) {
        if (username == null)
            return null;
        return dao.findByUsername(username);
    }
    @Override
    @Transactional
    public int deleteByUsername(String username) {
        return dao.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsernameWithRoles(username);
    }

    public void configure() {
        super.configure(User.class, UserSpecification.class);
    }


    @Autowired
    private RoleUserService roleUserService ;
    @Autowired
    private ModelPermissionService modelPermissionService ;
    @Autowired
    private ActionPermissionService actionPermissionService ;
    @Autowired
    private ModelPermissionUserService modelPermissionUserService ;
    @Autowired
    private RoleService roleService;

    @Lazy
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserDao dao) {
        super(dao);
    }

}

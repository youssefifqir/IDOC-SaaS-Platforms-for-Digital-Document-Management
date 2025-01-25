package ma.zs.univ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.zs.univ.bean.core.abonne.Abonne;
import ma.zs.univ.service.facade.admin.abonne.AbonneAdminService;
import ma.zs.univ.bean.core.user.Utilisateur;
import ma.zs.univ.service.facade.admin.user.UtilisateurAdminService;
import ma.zs.univ.zynerator.security.bean.*;
import ma.zs.univ.zynerator.security.common.AuthoritiesConstants;
import ma.zs.univ.zynerator.security.service.facade.*;

import ma.zs.univ.bean.core.doc.DocumentField;
import ma.zs.univ.service.facade.admin.doc.DocumentFieldAdminService;
import ma.zs.univ.bean.core.doc.Field;
import ma.zs.univ.service.facade.admin.doc.FieldAdminService;
import ma.zs.univ.bean.core.doc.EntiteAdministrativeType;
import ma.zs.univ.service.facade.admin.doc.EntiteAdministrativeTypeAdminService;
import ma.zs.univ.bean.core.referetiel.AcessShare;
import ma.zs.univ.service.facade.admin.referetiel.AcessShareAdminService;
import ma.zs.univ.bean.core.doc.DocumentState;
import ma.zs.univ.service.facade.admin.doc.DocumentStateAdminService;
import ma.zs.univ.bean.core.doc.DocumentCategorie;
import ma.zs.univ.service.facade.admin.doc.DocumentCategorieAdminService;
import ma.zs.univ.bean.core.user.Utilisateur;
import ma.zs.univ.service.facade.admin.user.UtilisateurAdminService;
import ma.zs.univ.bean.core.user.EntiteAdministrative;
import ma.zs.univ.service.facade.admin.user.EntiteAdministrativeAdminService;
import ma.zs.univ.bean.core.doc.Tag;
import ma.zs.univ.service.facade.admin.doc.TagAdminService;
import ma.zs.univ.bean.core.referetiel.AcessManagement;
import ma.zs.univ.service.facade.admin.referetiel.AcessManagementAdminService;
import ma.zs.univ.bean.core.doc.DocumentType;
import ma.zs.univ.service.facade.admin.doc.DocumentTypeAdminService;
import ma.zs.univ.bean.core.doc.DocumentFieldState;
import ma.zs.univ.service.facade.admin.doc.DocumentFieldStateAdminService;
import ma.zs.univ.bean.core.user.Groupe;
import ma.zs.univ.service.facade.admin.user.GroupeAdminService;

import ma.zs.univ.zynerator.security.bean.User;
import ma.zs.univ.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class UnivApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx=SpringApplication.run(UnivApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , AbonneAdminService abonneService, UtilisateurAdminService utilisateurService) {
    return (args) -> {
        if(true){

            createDocumentField();
            createField();
            createEntiteAdministrativeType();
            createAcessShare();
            createDocumentState();
            createDocumentCategorie();
            createUtilisateur();
            createEntiteAdministrative();
            createTag();
            createAcessManagement();
            createDocumentType();
            createDocumentFieldState();
            createGroupe();

        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Abonne
        Abonne userForAbonne = new Abonne("abonne");
		userForAbonne.setPassword("123");
		// Role Abonne
		Role roleForAbonne = new Role();
		roleForAbonne.setAuthority(AuthoritiesConstants.ABONNE);
		roleForAbonne.setCreatedAt(LocalDateTime.now());
		Role roleForAbonneSaved = roleService.create(roleForAbonne);
		RoleUser roleUserForAbonne = new RoleUser();
		roleUserForAbonne.setRole(roleForAbonneSaved);
		if (userForAbonne.getRoleUsers() == null)
			userForAbonne.setRoleUsers(new ArrayList<>());

		userForAbonne.getRoleUsers().add(roleUserForAbonne);
		if (userForAbonne.getModelPermissionUsers() == null)
			userForAbonne.setModelPermissionUsers(new ArrayList<>());


        userForAbonne.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        abonneService.create(userForAbonne);

		// User Utilisateur
        Utilisateur userForUtilisateur = new Utilisateur("utilisateur");
		userForUtilisateur.setPassword("123");
		// Role Utilisateur
		Role roleForUtilisateur = new Role();
		roleForUtilisateur.setAuthority(AuthoritiesConstants.UTILISATEUR);
		roleForUtilisateur.setCreatedAt(LocalDateTime.now());
		Role roleForUtilisateurSaved = roleService.create(roleForUtilisateur);
		RoleUser roleUserForUtilisateur = new RoleUser();
		roleUserForUtilisateur.setRole(roleForUtilisateurSaved);
		if (userForUtilisateur.getRoleUsers() == null)
			userForUtilisateur.setRoleUsers(new ArrayList<>());

		userForUtilisateur.getRoleUsers().add(roleUserForUtilisateur);
		if (userForUtilisateur.getModelPermissionUsers() == null)
			userForUtilisateur.setModelPermissionUsers(new ArrayList<>());


        userForUtilisateur.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        utilisateurService.create(userForUtilisateur);

            }
        };
    }



    private void createDocumentField(){
        String value = "value";
        for (int i = 1; i < 100; i++) {
            DocumentField item = new DocumentField();
            item.setValue(fakeString(value, i));
            documentFieldService.create(item);
        }
    }
    private void createField(){
        String code = "code";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            Field item = new Field();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            fieldService.create(item);
        }
    }
    private void createEntiteAdministrativeType(){
        String code = "code";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            EntiteAdministrativeType item = new EntiteAdministrativeType();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            entiteAdministrativeTypeService.create(item);
        }
    }
    private void createAcessShare(){
        String code = "code";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            AcessShare item = new AcessShare();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            acessShareService.create(item);
        }
    }
    private void createDocumentState(){
        String code = "code";
        String libelle = "libelle";
        String style = "style";
        for (int i = 1; i < 100; i++) {
            DocumentState item = new DocumentState();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            item.setStyle(fakeString(style, i));
            documentStateService.create(item);
        }
    }
    private void createDocumentCategorie(){
        String code = "code";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            DocumentCategorie item = new DocumentCategorie();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            documentCategorieService.create(item);
        }
    }
    private void createUtilisateur(){
        String description = "description";
         String credentialsNonExpired = "credentialsNonExpired";
         String enabled = "enabled";
         String accountNonExpired = "accountNonExpired";
         String accountNonLocked = "accountNonLocked";
         String passwordChanged = "passwordChanged";
        String username = "username";
        String password = "password";
        for (int i = 1; i < 100; i++) {
            Utilisateur item = new Utilisateur();
            item.setDescription(fakeString(description, i));
            item.setCredentialsNonExpired(fakeBoolean(credentialsNonExpired, i));
            item.setEnabled(fakeBoolean(enabled, i));
            item.setAccountNonExpired(fakeBoolean(accountNonExpired, i));
            item.setAccountNonLocked(fakeBoolean(accountNonLocked, i));
            item.setPasswordChanged(fakeBoolean(passwordChanged, i));
            item.setUsername(fakeString(username, i));
            item.setPassword(fakeString(password, i));
            utilisateurService.create(item);
        }
    }
    private void createEntiteAdministrative(){
        String code = "code";
        String description = "description";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            EntiteAdministrative item = new EntiteAdministrative();
            item.setCode(fakeString(code, i));
            item.setDescription(fakeString(description, i));
            item.setLibelle(fakeString(libelle, i));
            entiteAdministrativeService.create(item);
        }
    }
    private void createTag(){
        String code = "code";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            Tag item = new Tag();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            tagService.create(item);
        }
    }
    private void createAcessManagement(){
        String code = "code";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            AcessManagement item = new AcessManagement();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            acessManagementService.create(item);
        }
    }
    private void createDocumentType(){
        String code = "code";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            DocumentType item = new DocumentType();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            documentTypeService.create(item);
        }
    }
    private void createDocumentFieldState(){
        String code = "code";
        String libelle = "libelle";
        String style = "style";
        for (int i = 1; i < 100; i++) {
            DocumentFieldState item = new DocumentFieldState();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            item.setStyle(fakeString(style, i));
            documentFieldStateService.create(item);
        }
    }
    private void createGroupe(){
        String code = "code";
        String libelle = "libelle";
        for (int i = 1; i < 100; i++) {
            Groupe item = new Groupe();
            item.setCode(fakeString(code, i));
            item.setLibelle(fakeString(libelle, i));
            groupeService.create(item);
        }
    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("DocumentField"));
        modelPermissions.add(new ModelPermission("Field"));
        modelPermissions.add(new ModelPermission("DocumentPartageUtilisateur"));
        modelPermissions.add(new ModelPermission("EntiteAdministrativeType"));
        modelPermissions.add(new ModelPermission("AcessShare"));
        modelPermissions.add(new ModelPermission("DocumentTag"));
        modelPermissions.add(new ModelPermission("DocumentState"));
        modelPermissions.add(new ModelPermission("DocumentPartageGroupe"));
        modelPermissions.add(new ModelPermission("Document"));
        modelPermissions.add(new ModelPermission("DocumentCategorie"));
        modelPermissions.add(new ModelPermission("Utilisateur"));
        modelPermissions.add(new ModelPermission("EntiteAdministrative"));
        modelPermissions.add(new ModelPermission("Tag"));
        modelPermissions.add(new ModelPermission("AcessManagement"));
        modelPermissions.add(new ModelPermission("Abonne"));
        modelPermissions.add(new ModelPermission("DocumentType"));
        modelPermissions.add(new ModelPermission("DocumentFieldState"));
        modelPermissions.add(new ModelPermission("DocumentAcessShare"));
        modelPermissions.add(new ModelPermission("GroupeUtilisateur"));
        modelPermissions.add(new ModelPermission("Groupe"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    DocumentFieldAdminService documentFieldService;
    @Autowired
    FieldAdminService fieldService;
    @Autowired
    EntiteAdministrativeTypeAdminService entiteAdministrativeTypeService;
    @Autowired
    AcessShareAdminService acessShareService;
    @Autowired
    DocumentStateAdminService documentStateService;
    @Autowired
    DocumentCategorieAdminService documentCategorieService;
    @Autowired
    UtilisateurAdminService utilisateurService;
    @Autowired
    EntiteAdministrativeAdminService entiteAdministrativeService;
    @Autowired
    TagAdminService tagService;
    @Autowired
    AcessManagementAdminService acessManagementService;
    @Autowired
    DocumentTypeAdminService documentTypeService;
    @Autowired
    DocumentFieldStateAdminService documentFieldStateService;
    @Autowired
    GroupeAdminService groupeService;
}



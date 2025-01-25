package ma.zs.univ.integration.core.purchase.groupe-utilisateur;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class GroupeUtilisateurIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("GroupeUtilisateurHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

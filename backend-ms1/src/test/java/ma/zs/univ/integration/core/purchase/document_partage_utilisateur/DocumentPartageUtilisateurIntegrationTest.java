package ma.zs.univ.integration.core.purchase.document-partage-utilisateur;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentPartageUtilisateurIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentPartageUtilisateurHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

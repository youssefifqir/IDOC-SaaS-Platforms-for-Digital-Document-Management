package ma.zs.univ.integration.core.doc.entite-administrative-type;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EntiteAdministrativeTypeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EntiteAdministrativeTypeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

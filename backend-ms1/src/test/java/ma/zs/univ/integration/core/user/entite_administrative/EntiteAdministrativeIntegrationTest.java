package ma.zs.univ.integration.core.user.entite-administrative;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class EntiteAdministrativeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("EntiteAdministrativeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

package ma.zs.univ.integration.core.abonne.abonne;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AbonneIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AbonneHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

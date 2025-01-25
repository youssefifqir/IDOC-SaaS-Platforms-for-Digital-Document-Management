package ma.zs.univ.integration.core.user.groupe;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class GroupeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("GroupeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

package ma.zs.univ.integration.core.referetiel.acess-management;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AcessManagementIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AcessManagementHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

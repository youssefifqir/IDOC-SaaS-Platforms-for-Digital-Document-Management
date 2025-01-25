package ma.zs.univ.integration.core.referetiel.acess-share;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class AcessShareIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("AcessShareHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

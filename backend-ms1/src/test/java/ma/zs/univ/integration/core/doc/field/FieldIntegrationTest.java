package ma.zs.univ.integration.core.doc.field;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class FieldIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("FieldHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

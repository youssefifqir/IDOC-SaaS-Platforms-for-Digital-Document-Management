package ma.zs.univ.integration.core.doc.tag;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class TagIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("TagHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

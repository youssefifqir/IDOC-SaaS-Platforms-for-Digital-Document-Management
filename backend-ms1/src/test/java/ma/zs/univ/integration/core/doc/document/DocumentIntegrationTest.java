package ma.zs.univ.integration.core.doc.document;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

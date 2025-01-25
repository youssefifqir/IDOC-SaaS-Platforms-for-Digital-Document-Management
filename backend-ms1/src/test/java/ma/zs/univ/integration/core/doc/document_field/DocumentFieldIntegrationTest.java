package ma.zs.univ.integration.core.doc.document-field;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentFieldIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentFieldHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

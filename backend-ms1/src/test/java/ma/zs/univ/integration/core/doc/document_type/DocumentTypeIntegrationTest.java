package ma.zs.univ.integration.core.doc.document-type;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentTypeIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentTypeHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

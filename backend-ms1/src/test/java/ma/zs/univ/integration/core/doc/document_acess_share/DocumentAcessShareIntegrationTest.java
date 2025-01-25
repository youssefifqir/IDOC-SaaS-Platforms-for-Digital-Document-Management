package ma.zs.univ.integration.core.doc.document-acess-share;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentAcessShareIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentAcessShareHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

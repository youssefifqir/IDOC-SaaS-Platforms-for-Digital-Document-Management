package ma.zs.univ.integration.core.doc.document-categorie;

import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;

public class DocumentCategorieIntegrationTest {

 @Karate.Test
    Karate saveHappyTest() {
        return Karate.run("DocumentCategorieHappyTest")
                .tags("save")
                .relativeTo(getClass());
    }


}

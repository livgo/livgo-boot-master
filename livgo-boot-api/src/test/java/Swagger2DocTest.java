import com.livgo.boot.api.ApplicationApi;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.swagger.config.SwaggerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URL;
import java.nio.file.Paths;

/**
 * Description:
 * Author:     gaocl
 * Date:       2018/1/29
 * Version:    V1.0.0
 * Update:     更新说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationApi.class, SwaggerConfig.class})@AutoConfigureMockMvc
@WebAppConfiguration
public class Swagger2DocTest {

        public String url = "http://localhost:666/v2/api-docs";

        @Test
        public void generateAsciiDocs() throws Exception {
                //	输出Ascii格式
                Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                        .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                        .build();

                Swagger2MarkupConverter.from(new URL(url))
                        .withConfig(config)
                        .build()
                        .toFolder(Paths.get("src/docs/asciidoc/generated"));
        }

        @Test
        public void generateMarkdownDocs() throws Exception {
                //	输出Markdown格式
                Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                        .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                        .build();

                Swagger2MarkupConverter.from(new URL(url))
                        .withConfig(config)
                        .build()
                        .toFolder(Paths.get("src/docs/markdown/generated"));
        }


        @Test
        public void generateAsciiDocsToFile() throws Exception {
                //	输出Ascii到单文件
                Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                        .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                        .build();

                Swagger2MarkupConverter.from(new URL(url))
                        .withConfig(config)
                        .build()
                        .toFile(Paths.get("src/docs/asciidoc/generated/all"));
        }

        @Test
        public void generateMarkdownDocsToFile() throws Exception {
                //	输出Markdown到单文件
                Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                        .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                        .build();

                Swagger2MarkupConverter.from(new URL(url))
                        .withConfig(config)
                        .build()
                        .toFile(Paths.get("src/docs/markdown/generated/all"));
        }
}

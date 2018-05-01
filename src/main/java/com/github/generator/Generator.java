package com.github.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianghang on 2018/4/30.
 */
public class Generator {

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;

        InputStream inputStream = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
        ConfigurationParser parser = new ConfigurationParser(warnings);
        Configuration configuration = parser.parseConfiguration(inputStream);
        inputStream.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration,callback,warnings);

        myBatisGenerator.generate(null);

        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}

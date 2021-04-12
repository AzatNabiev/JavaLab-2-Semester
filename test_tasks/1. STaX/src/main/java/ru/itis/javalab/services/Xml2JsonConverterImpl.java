package ru.itis.javalab.services;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.FileInfo;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;

@Service
public class Xml2JsonConverterImpl implements Xml2JsonConverter {
    @Override
    public void convertXmlToJson(FileInfo fileInfo) throws IOException {

        InputStream input = null;
        OutputStream output = null;

        StringBuilder pathToInputXml = new StringBuilder();
        StringBuilder pathToOutputJson = new StringBuilder();

        pathToInputXml.append(fileInfo.getPathToFile()).append(File.separator).append(fileInfo.getId()).append(File.separator)
                .append(fileInfo.getName()).append(".").append(fileInfo.getContentType().split("/")[1]);

        pathToOutputJson.append(fileInfo.getPathToFile()).append(File.separator).append(fileInfo.getId()).append(File.separator)
                .append("output.json");
        try {
            input = new FileInputStream(pathToInputXml.toString());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        try {
            output = new FileOutputStream(pathToOutputJson.toString());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        JsonXMLConfig config = new JsonXMLConfigBuilder()
                .autoArray(true)
                .autoPrimitive(true)
                .prettyPrint(true)
                .build();
        try {
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (XMLStreamException e) {
            throw new IllegalArgumentException(e);
        } finally {
            output.close();
            input.close();
        }
    }
}

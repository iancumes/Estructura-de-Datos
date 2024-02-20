import java.io.*;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLDataSource implements IDataSource {

    @Override
    public void saveData(List<String[]> data, String path) {
        try {
            // Crear el contexto JAXB para la clase que contiene los datos
            JAXBContext context = JAXBContext.newInstance(DataWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            
            // Configurar el marshaller para formatear la salida XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            // Empaquetar los datos en una instancia de DataWrapper
            DataWrapper wrapper = new DataWrapper();
            wrapper.setData(data);
            
            // Escribir los datos en el archivo XML
            marshaller.marshal(wrapper, new File(path));
            
            System.out.println("Datos guardados en formato XML correctamente.");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String[]> loadData(String path) {
        try {
            // Crear el contexto JAXB para la clase que contiene los datos
            JAXBContext context = JAXBContext.newInstance(DataWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            // Deserializar los datos desde el archivo XML a una instancia de DataWrapper
            DataWrapper wrapper = (DataWrapper) unmarshaller.unmarshal(new File(path));
            
            // Obtener los datos de la instancia de DataWrapper
            return wrapper.getData();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
